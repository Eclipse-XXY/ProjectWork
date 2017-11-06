DROP TABLE IF EXISTS t_account_type_cert;

DROP TABLE IF EXISTS t_advertisement;

DROP TABLE IF EXISTS t_cert;

DROP TABLE IF EXISTS t_dictionary;

DROP TABLE IF EXISTS t_member;

DROP TABLE IF EXISTS t_member_address;

DROP TABLE IF EXISTS t_member_cert;

DROP TABLE IF EXISTS t_member_project_follow;

DROP TABLE IF EXISTS t_message;

DROP TABLE IF EXISTS t_order;

DROP TABLE IF EXISTS t_param;

DROP TABLE IF EXISTS t_permission;

DROP TABLE IF EXISTS t_project;

DROP TABLE IF EXISTS t_project_tag;

DROP TABLE IF EXISTS t_project_type;

DROP TABLE IF EXISTS t_return;

DROP TABLE IF EXISTS t_role;

DROP TABLE IF EXISTS t_role_permission;

DROP TABLE IF EXISTS t_tag;

DROP TABLE IF EXISTS t_type;

DROP TABLE IF EXISTS t_user;

DROP TABLE IF EXISTS t_user_role;

/*==============================================================*/
/* Table: t_account_type_cert                                   */
/*==============================================================*/
CREATE TABLE t_account_type_cert
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   accttype             CHAR(1),
   certid               INT(11),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_advertisement                                       */
/*==============================================================*/
CREATE TABLE t_advertisement
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(255),
   iconpath             VARCHAR(255),
   STATUS               CHAR(1) COMMENT '0 - 草稿， 1 - 未审核， 2 - 审核完成， 3 - 发布',
   url                  VARCHAR(255),
   userid               INT(11),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_cert                                                */
/*==============================================================*/
CREATE TABLE t_cert
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_dictionary                                          */
/*==============================================================*/
CREATE TABLE t_dictionary
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(255),
   CODE                 VARCHAR(255),
   subcode              VARCHAR(255),
   val                  VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_member                                              */
/*==============================================================*/
CREATE TABLE t_member
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   loginacct            VARCHAR(255) NOT NULL,
   userpswd             CHAR(32) NOT NULL,
   username             VARCHAR(255) NOT NULL,
   email                VARCHAR(255) NOT NULL,
   authstatus           CHAR(1) NOT NULL COMMENT '实名认证状态 0 - 未实名认证， 1 - 实名认证申请中， 2 - 已实名认证',
   usertype             CHAR(1) NOT NULL COMMENT ' 0 - 个人， 1 - 企业',
   realname             VARCHAR(255),
   cardnum              VARCHAR(255),
   accttype             CHAR(1) COMMENT '0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府',
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_member_address                                      */
/*==============================================================*/
CREATE TABLE t_member_address
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   memberid             INT(11),
   address              VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_member_cert                                         */
/*==============================================================*/
CREATE TABLE t_member_cert
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   memberid             INT(11),
   certid               INT(11),
   iconpath             VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_member_project_follow                               */
/*==============================================================*/
CREATE TABLE t_member_project_follow
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   projectid            INT(11),
   memberid             INT(11),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_message                                             */
/*==============================================================*/
CREATE TABLE t_message
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   memberid             INT(11),
   content              VARCHAR(255),
   senddate             CHAR(19),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_order                                               */
/*==============================================================*/
CREATE TABLE t_order
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   memberid             INT(11),
   projectid            INT(11),
   returnid             INT(11),
   ordernum             VARCHAR(255),
   createdate           CHAR(19),
   money                INT(11),
   rtncount             INT(11),
   STATUS               CHAR(1) COMMENT '0 - 待付款， 1 - 交易完成， 9 - 交易关闭',
   address              VARCHAR(255),
   invoice              CHAR(1) COMMENT '0 - 不开发票， 1 - 开发票',
   invoictitle          VARCHAR(255),
   remark               VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_param                                               */
/*==============================================================*/
CREATE TABLE t_param
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(255),
   CODE                 VARCHAR(255),
   val                  VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_permission                                          */
/*==============================================================*/
CREATE TABLE t_permission
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   pid                  INT(11),
   NAME                 VARCHAR(255),
   icon                 VARCHAR(255),
   url                  VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_project                                             */
/*==============================================================*/
CREATE TABLE t_project
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(255),
   remark               VARCHAR(255),
   money                BIGINT (11),
   DAY                  INT(11),
   STATUS               CHAR(1) COMMENT '0 - 即将开始， 1 - 众筹中， 2 - 众筹成功， 3 - 众筹失败',
   deploydate           CHAR(10),
   supportmoney         BIGINT(11),
   supporter            INT(11),
   COMPLETION           INT(3),
   memberid             INT(11),
   createdate           CHAR(19),
   follower             INT(11),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_project_tag                                         */
/*==============================================================*/
CREATE TABLE t_project_tag
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   projectid            INT(11),
   tagid                INT(11),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_project_type                                        */
/*==============================================================*/
CREATE TABLE t_project_type
(
   id                   INT NOT NULL AUTO_INCREMENT,
   projectid            INT(11),
   typeid               INT(11),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_return                                              */
/*==============================================================*/
CREATE TABLE t_return
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   projectid            INT(11),
   TYPE                 CHAR(1) COMMENT '0 - 实物回报， 1 虚拟物品回报',
   supportmoney         INT(11),
   content              VARCHAR(255),
   COUNT                INT(11) COMMENT '“0”为不限回报数量',
   signalpurchase       INT(11),
   purchase             INT(11),
   freight              INT(11),
   invoice              CHAR(1) COMMENT '0 - 不开发票， 1 - 开发票',
   rtndate              INT(11),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
CREATE TABLE t_role
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_role_permission                                     */
/*==============================================================*/
CREATE TABLE t_role_permission
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   roleid               INT(11),
   permissionid         INT(11),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_tag                                                 */
/*==============================================================*/
CREATE TABLE t_tag
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   pid                  INT(11),
   NAME                 VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_type                                                */
/*==============================================================*/
CREATE TABLE t_type
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   NAME                 VARCHAR(255),
   remark               VARCHAR(255),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
CREATE TABLE t_user
(
   id                   INT NOT NULL AUTO_INCREMENT,
   loginacct            VARCHAR(255) NOT NULL,
   userpswd             CHAR(32) NOT NULL,
   username             VARCHAR(255) NOT NULL,
   email                VARCHAR(255) NOT NULL,
   createtime           CHAR(19),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_user_role                                           */
/*==============================================================*/
CREATE TABLE t_user_role
(
   id                   INT(11) NOT NULL AUTO_INCREMENT,
   userid               INT(11),
   roleid               INT(11),
   PRIMARY KEY (id)
);

ALTER TABLE t_project_tag ADD CONSTRAINT FK_Reference_7 FOREIGN KEY (projectid)
      REFERENCES t_project (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_project_tag ADD CONSTRAINT FK_Reference_8 FOREIGN KEY (tagid)
      REFERENCES t_tag (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_project_type ADD CONSTRAINT FK_Reference_5 FOREIGN KEY (projectid)
      REFERENCES t_project (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_project_type ADD CONSTRAINT FK_Reference_6 FOREIGN KEY (typeid)
      REFERENCES t_type (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_role_permission ADD CONSTRAINT FK_Reference_3 FOREIGN KEY (roleid)
      REFERENCES t_role (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_role_permission ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (permissionid)
      REFERENCES t_permission (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_user_role ADD CONSTRAINT FK_Reference_1 FOREIGN KEY (userid)
      REFERENCES t_user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_user_role ADD CONSTRAINT FK_Reference_2 FOREIGN KEY (roleid)
      REFERENCES t_role (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
