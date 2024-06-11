/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : sika-pw

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 11/06/2024 13:49:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sika_role
-- ----------------------------
DROP TABLE IF EXISTS `sika_role`;
CREATE TABLE `sika_role`  (
  `role_id` bigint NOT NULL COMMENT 'role id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'role name',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'the nickname of person who created the role',
  `create_time` datetime NULL DEFAULT NULL COMMENT 'role creation time',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'the nickname of person who update the role',
  `update_time` datetime NULL DEFAULT NULL COMMENT 'role update time',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'system role table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sika_role
-- ----------------------------

-- ----------------------------
-- Table structure for sika_user
-- ----------------------------
DROP TABLE IF EXISTS `sika_user`;
CREATE TABLE `sika_user`  (
  `user_id` bigint NOT NULL COMMENT 'user id',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'user show name',
  `password` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'user password is encrypted using sha256',
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'user mobile phone number also the login account',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'user email',
  `sex` tinyint NULL DEFAULT NULL COMMENT 'user sex, 0 is unknown, 1 is male, 2 is female',
  `craete_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'the nickname of the person who created the account name',
  `create_time` datetime NULL DEFAULT NULL COMMENT 'Account creation time',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'the nickname of the persion who update the account',
  `update_time` datetime NULL DEFAULT NULL COMMENT 'Account update time',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'system user table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sika_user
-- ----------------------------

-- ----------------------------
-- Table structure for sika_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sika_user_role`;
CREATE TABLE `sika_user_role`  (
  `user_role_id` bigint NOT NULL COMMENT 'mapping id of the user and role',
  `user_id` bigint NULL DEFAULT NULL COMMENT 'user id',
  `role_id` bigint NULL DEFAULT NULL COMMENT 'role id',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'mapping user and role table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sika_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
