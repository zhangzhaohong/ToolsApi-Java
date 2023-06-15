CREATE TABLE IF NOT EXISTS `api_data`
(
    `unique_id` BIGINT UNSIGNED AUTO_INCREMENT,
    `path`      VARCHAR(255) DEFAULT NULL,
    `year`      INT          DEFAULT -1,
    `month`     INT          DEFAULT -1,
    `day`       INT          DEFAULT -1,
    `hour`      INT          DEFAULT -1,
    `data`      VARCHAR(255) DEFAULT '{}',
    `created`   BIGINT       DEFAULT 0,
    PRIMARY KEY (`unique_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;