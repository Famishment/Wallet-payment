Wallet user interface:
Query the user's wallet balance
The interface where the user spends 100 yuan
User refund 20 yuan interface
Query the details of the user's wallet amount change interface

# 钱包表
CREATE TABLE `wallet` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`username` varchar(255) DEFAULT NULL,
`balance` decimal(12,0) NOT NULL,
`updateTime` datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
## 
INSERT INTO wallet(id,username,balance) VALUES ('1', 'Jack', '200');

# 订单表
CREATE TABLE `orders` (
`o_id` int(11) NOT NULL AUTO_INCREMENT,
`commodity` varchar(255) DEFAULT NULL,
`money` decimal(12,0) DEFAULT NULL,
`status` varchar(10) NOT NULL,
`createTime` datetime default CURRENT_TIMESTAMP,
`updateTime` datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
`w_id` int(11) DEFAULT NULL,
PRIMARY KEY (`o_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
## 插入订单信息
INSERT INTO orders(o_id, commodity, money, status, w_id) VALUES ('1', '苹果', '20', '1', '1');
INSERT INTO orders(o_id, commodity, money, status, w_id) VALUES ('2', '胡萝卜', '20', '2', '1');


# 交易记录表
CREATE TABLE `record` (
`r_id` int(11) NOT NULL AUTO_INCREMENT,
`money` decimal(12,0) DEFAULT NULL,
`balance` decimal(12,0) DEFAULT NULL,
`type` char(32) DEFAULT NULL,
`createTime` datetime default CURRENT_TIMESTAMP,
`w_id` int(11) DEFAULT NULL,
PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
## 插入交易记录信息
INSERT INTO record(r_id, money, balance, type, w_id) VALUES ('2', '20', '200', '1', '1');
INSERT INTO record(r_id, money, balance, type, w_id) VALUES ('3', '20', '220', '1', '1');

# 银行卡表
CREATE TABLE `bank_card` (
`b_id` int(11) NOT NULL AUTO_INCREMENT,
`card_name` varchar(32) DEFAULT NULL,
`balance` decimal(12,0) DEFAULT NULL,
`w_id` int(11) DEFAULT NULL,
`updateTime` datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
PRIMARY KEY (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
## 插入银行卡信息
INSERT INTO bank_card(b_id, card_name, balance, w_id) VALUES ('1', '6222111', '300', '1');