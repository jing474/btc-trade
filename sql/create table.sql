CREATE TABLE `btc38_depth_btc` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `catch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '抓取时间',
  `price_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类型 0：买盘，1：卖盘',
  `top_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '最高价',
  `middle_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '中间价',
  `low_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '最低价',
  `avg_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '平均价',
  `total_number` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '总数量',
  `total_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '总价值',
  PRIMARY KEY (`id`),
  KEY `idx_catch_time` (`catch_time`,`price_type`)
) ENGINE=MyISAM AUTO_INCREMENT=12657 DEFAULT CHARSET=utf8;

CREATE TABLE `btc38_depth_doge` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `catch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '抓取时间',
  `price_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类型 0：买盘，1：卖盘',
  `top_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '最高价',
  `middle_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '中间价',
  `low_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '最低价',
  `avg_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '平均价',
  `total_number` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '总数量',
  `total_price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '总价值',
  PRIMARY KEY (`id`),
  KEY `idx_catch_time` (`catch_time`,`price_type`)
) ENGINE=MyISAM AUTO_INCREMENT=1777 DEFAULT CHARSET=utf8;

CREATE TABLE `btc38_trade_btc` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `catch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '抓取时间',
  `tid` varchar(16) NOT NULL DEFAULT '' COMMENT '交易ID',
  `trade_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '成交时间',
  `price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '成交价',
  `amount` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '成交数量',
  `trade_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类型 0：买盘，1：卖盘',
  PRIMARY KEY (`id`),
  KEY `idx_catch_time` (`catch_time`,`trade_type`),
  KEY `idx_tid` (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=10458 DEFAULT CHARSET=utf8;

CREATE TABLE `btc38_trade_doge` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `catch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '抓取时间',
  `tid` varchar(16) NOT NULL DEFAULT '' COMMENT '交易ID',
  `trade_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '成交时间',
  `price` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '成交价',
  `amount` decimal(16,8) unsigned NOT NULL DEFAULT '0.00000000' COMMENT '成交数量',
  `trade_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类型 0：买盘，1：卖盘',
  PRIMARY KEY (`id`),
  KEY `idx_catch_time` (`catch_time`,`trade_type`),
  KEY `idx_tid` (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=8375 DEFAULT CHARSET=utf8;