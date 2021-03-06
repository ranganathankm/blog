CREATE TABLE `app_user`.`app_user` (
  `id` INT NOT NULL,
  `login_name` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(50),
  `last_name` VARCHAR(50),
  `email` VARCHAR(50),
  `mobile` VARCHAR(20),
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `app_user`.`user_password` (
  `id` INT NOT NULL,
  `password_hash` VARCHAR(100) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `uid_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `uid_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `app_user`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `app_user`.`role_master` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `app_user`.`user_role` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `role_fk_idx` (`role_id` ASC) VISIBLE,
  INDEX `usr_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `role_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `app_user`.`role_master` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `usr_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `app_user`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

