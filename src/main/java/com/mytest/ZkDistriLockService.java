package com.mytest;

/**
 * Created by caijing on 2017/6/16.
 */
public interface ZkDistriLockService {

    boolean tryLock(Callback callback,long timeout) throws  Exception;



    interface Callback{
        void execute();

        void expired();
    }
}
