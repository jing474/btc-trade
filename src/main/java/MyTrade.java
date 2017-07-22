import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.utils.LoadDataUtil;

/**
 * Created by caijing on 2017/7/20.
 */
public class MyTrade {

    private static final String[] CONFIG_RESOUCES = new String[] { "application.xml" };

    private static ClassPathXmlApplicationContext springContext = null;

    private static volatile boolean running = true;

    public static void main(String[] args) {

        startSpringContainer();

        LoadDataUtil.scheduled();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (springContext != null) {
                    springContext.stop();
                    springContext.close();
                    springContext = null;
                }
                synchronized (MyTrade.class) {
                    running = false;
                    MyTrade.class.notify();
                }
            }
        });

        synchronized (MyTrade.class) {
            while (running) {
                try {
                    MyTrade.class.wait();
                } catch (Throwable e) {
                }
            }
        }

    }

    private static void startSpringContainer() {
        springContext = new ClassPathXmlApplicationContext(CONFIG_RESOUCES);
        springContext.start();
        System.out.println("application start success......");

    }
}
