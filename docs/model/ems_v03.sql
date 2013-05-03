SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `ems` ;
CREATE SCHEMA IF NOT EXISTS `ems` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `ems` ;

-- -----------------------------------------------------
-- Table `ems`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ems`.`user` ;

CREATE  TABLE IF NOT EXISTS `ems`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fname` VARCHAR(45) NOT NULL ,
  `lname` VARCHAR(45) NOT NULL ,
  `date_of_birth` DATETIME NULL ,
  `password` VARCHAR(64) NOT NULL ,
  `email` VARCHAR(60) NOT NULL ,
  `role` ENUM('admin','event_mng','group_mng') NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ems`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ems`.`event` ;

CREATE  TABLE IF NOT EXISTS `ems`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_manager` INT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  `start` DATE NULL ,
  `end` DATE NULL ,
  `enrollment_start` DATE NULL ,
  `enrollment_end` DATE NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_EVENT_USER` (`id_manager` ASC) ,
  CONSTRAINT `fk_EVENT_USER`
    FOREIGN KEY (`id_manager` )
    REFERENCES `ems`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ems`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ems`.`group` ;

CREATE  TABLE IF NOT EXISTS `ems`.`group` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_event` INT NOT NULL ,
  `id_group_referent` INT NOT NULL ,
  `max_group_number` INT NULL ,
  `blocked` TINYINT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_GROUP_EVENT` (`id_event` ASC) ,
  INDEX `fk_GROUP_USER` (`id_group_referent` ASC) ,
  CONSTRAINT `fk_GROUP_EVENT`
    FOREIGN KEY (`id_event` )
    REFERENCES `ems`.`event` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GROUP_USER`
    FOREIGN KEY (`id_group_referent` )
    REFERENCES `ems`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ems`.`participant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ems`.`participant` ;

CREATE  TABLE IF NOT EXISTS `ems`.`participant` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_group` INT NOT NULL ,
  `fname` VARCHAR(45) NOT NULL ,
  `lname` VARCHAR(45) NOT NULL ,
  `date_of_birth` DATE NOT NULL ,
  `registration_date` DATETIME NULL ,
  `approved` TINYINT NULL DEFAULT 0 ,
  `blocked` TINYINT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_PARTICIPANT_GROUP` (`id_group` ASC) ,
  CONSTRAINT `fk_PARTICIPANT_GROUP`
    FOREIGN KEY (`id_group` )
    REFERENCES `ems`.`group` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ems`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ems`.`user_role` ;

CREATE  TABLE IF NOT EXISTS `ems`.`user_role` (
  `ROLE_NAME` VARCHAR(30) NULL ,
  `email` VARCHAR(30) NULL )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
