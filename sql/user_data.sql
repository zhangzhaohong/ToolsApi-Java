CREATE TABLE IF NOT EXISTS `user_data`
(
    `unique_id`        BIGINT UNSIGNED AUTO_INCREMENT,
    `user_id`          BIGINT       NOT NULL DEFAULT 0,
    `nick_name`        VARCHAR(255) NOT NULL DEFAULT 'USERNAME',
    `password`         VARCHAR(255) NOT NULL,
    `user_token`       VARCHAR(255)          DEFAULT NULL,
    `update_timestamp` TIMESTAMP             DEFAULT NULL,
    PRIMARY KEY (`unique_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;