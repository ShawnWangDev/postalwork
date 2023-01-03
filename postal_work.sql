DROP DATABASE IF EXISTS `postal_work`;
CREATE DATABASE `postal_work`;
USE `postal_work`;

DROP TABLE IF EXISTS `express_brand`;
CREATE TABLE `express_brand`(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(16)
)charset=UTF8MB4;

INSERT INTO `express_brand` (name) VALUES ("{{其他}}");
INSERT INTO `express_brand` (name) VALUES ("申通");
INSERT INTO `express_brand` (name) VALUES ("圆通");
INSERT INTO `express_brand` (name) VALUES ("中通");
INSERT INTO `express_brand` (name) VALUES ("韵达");
INSERT INTO `express_brand` (name) VALUES ("顺丰");
INSERT INTO `express_brand` (name) VALUES ("丰网");
INSERT INTO `express_brand` (name) VALUES ("邮政");
INSERT INTO `express_brand` (name) VALUES ("菜鸟");
INSERT INTO `express_brand` (name) VALUES ("极兔");
INSERT INTO `express_brand` (name) VALUES ("德邦");
INSERT INTO `express_brand` (name) VALUES ("丹鸟");
INSERT INTO `express_brand` (name) VALUES ("京东");
INSERT INTO `express_brand` (name) VALUES ("丰巢");
INSERT INTO `express_brand` (name) VALUES ("本地");
INSERT INTO `express_brand` (name) VALUES ("{{多家}}");

DROP TABLE IF EXISTS `issue_type`;
CREATE TABLE `issue_type`(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(32)
)charset=UTF8MB4;

INSERT INTO `issue_type` (name) VALUES ("待设定");
INSERT INTO `issue_type` (name) VALUES ("延误");
INSERT INTO `issue_type` (name) VALUES ("损毁");
INSERT INTO `issue_type` (name) VALUES ("丢失短少");
INSERT INTO `issue_type` (name) VALUES ("投递服务");
INSERT INTO `issue_type` (name) VALUES ("收寄服务");
INSERT INTO `issue_type` (name) VALUES ("疫情延误");
INSERT INTO `issue_type` (name) VALUES ("活体运输");
INSERT INTO `issue_type` (name) VALUES ("快运");
INSERT INTO `issue_type` (name) VALUES ("信报箱");
INSERT INTO `issue_type` (name) VALUES ("其他");

DROP TABLE IF EXISTS `issue_condition`;
CREATE TABLE `issue_condition`(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(16)
)charset=UTF8MB4;

INSERT INTO `issue_condition` (name) VALUES ("待设定");
INSERT INTO `issue_condition` (name) VALUES ("已通知");
INSERT INTO `issue_condition` (name) VALUES ("已催办");
INSERT INTO `issue_condition` (name) VALUES ("达成一致");
INSERT INTO `issue_condition` (name) VALUES ("未达成一致");
INSERT INTO `issue_condition` (name) VALUES ("正在处理");
INSERT INTO `issue_condition` (name) VALUES ("因疫情延误");
INSERT INTO `issue_condition` (name) VALUES ("建议联系发件方");
INSERT INTO `issue_condition` (name) VALUES ("通知企业前已妥投");
INSERT INTO `issue_condition` (name) VALUES ("到期前未答复");
