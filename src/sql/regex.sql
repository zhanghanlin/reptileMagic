CREATE TABLE regex
(
  id               VARCHAR(100) PRIMARY KEY NOT NULL,
  name             VARCHAR(255),
  url              VARCHAR(255),
  list_regex       VARCHAR(255),
  detail_regex     VARCHAR(255),
  thread           INT(10),
  ignore_key       VARCHAR(255),
  data             VARCHAR(2000),
  task_key         VARCHAR(255),
  is_data          INT(11)  DEFAULT '0',
  create_time      DATETIME DEFAULT 'CURRENT_TIMESTAMP',
  update_time      DATETIME DEFAULT 'CURRENT_TIMESTAMP',
  retry_time       INT(11)  DEFAULT '3',
  cycle_retry_time INT(11)  DEFAULT '3',
  sleep_time       INT(11)  DEFAULT '100'
);