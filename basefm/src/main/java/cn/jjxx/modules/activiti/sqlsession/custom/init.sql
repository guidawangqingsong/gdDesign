
CREATE TABLE `act_menu_org` (
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织Id',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单Id',
  `model_id` varchar(32) DEFAULT NULL COMMENT '流程模型Id',
  UNIQUE KEY `org_menu_model` (`org_id`,`menu_id`,`model_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `act_re_type` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级Id',
  `parent_ids` varchar(500) DEFAULT NULL COMMENT '路径',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程类型表';

