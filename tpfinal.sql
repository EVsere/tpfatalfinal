-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tp_finaljeg
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tp_finaljeg` ;

-- -----------------------------------------------------
-- Schema tp_finaljeg
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tp_finaljeg` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `tp_finaljeg` ;

-- -----------------------------------------------------
-- Table `tp_finaljeg`.`comuna`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tp_finaljeg`.`comuna` ;

CREATE TABLE IF NOT EXISTS `tp_finaljeg`.`comuna` (
  `nombreSede` VARCHAR(50) NULL DEFAULT NULL,
  `NCom` INT NOT NULL,
  `JuntaComunal` VARCHAR(45) NULL DEFAULT NULL,
  `id_com` INT NOT NULL,
  PRIMARY KEY (`id_com`));


-- -----------------------------------------------------
-- Table `tp_finaljeg`.`barrio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tp_finaljeg`.`barrio` ;

CREATE TABLE IF NOT EXISTS `tp_finaljeg`.`barrio` (
  `ID_Barrio` INT NOT NULL AUTO_INCREMENT,
  `Superficie` DOUBLE NULL DEFAULT NULL,
  `FK_com` INT NULL DEFAULT NULL,
  `PAsfalto` INT NULL DEFAULT NULL,
  `NLey` INT NULL DEFAULT NULL,
  `CMono` BIT(1) NULL DEFAULT NULL,
  `Consorcio` BIT(1) NULL DEFAULT NULL,
  `TipoDeBarrio` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_Barrio`),
  INDEX `id_comuna_idx` (`FK_com` ASC) VISIBLE,
  CONSTRAINT `id_comuna`
    FOREIGN KEY (`FK_com`)
    REFERENCES `tp_finaljeg`.`comuna` (`id_com`));



-- -----------------------------------------------------
-- Table `tp_finaljeg`.`situacion_laboral`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tp_finaljeg`.`situacion_laboral` ;

CREATE TABLE IF NOT EXISTS `tp_finaljeg`.`situacion_laboral` (
  `Ocupado` BIT(1) NULL DEFAULT NULL,
  `Desocupado` BIT(1) NULL DEFAULT NULL,
  `semiOcupado` BIT(1) NULL DEFAULT NULL,
  `ID_SituacionLaboral` INT NOT NULL,
  PRIMARY KEY (`ID_SituacionLaboral`));

-- -----------------------------------------------------
-- Table `tp_finaljeg`.`residente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tp_finaljeg`.`residente` ;

CREATE TABLE IF NOT EXISTS `tp_finaljeg`.`residente` (
  `Dni` VARCHAR(50) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `FNacimiento` DATE NULL DEFAULT NULL,
  `id_res` INT NOT NULL,
  `FK_barrio` INT NULL DEFAULT NULL,
  `fk_SituacionLaboral` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_res`),
  INDEX `fk_barrio_idx` (`FK_barrio` ASC) VISIBLE,
  INDEX `fk_SituacionLaboral_idx` (`fk_SituacionLaboral` ASC) VISIBLE,
  CONSTRAINT `fk_barrio`
    FOREIGN KEY (`FK_barrio`)
    REFERENCES `tp_finaljeg`.`barrio` (`ID_Barrio`),
  CONSTRAINT `fk_SituacionLaboral`
    FOREIGN KEY (`fk_SituacionLaboral`)
    REFERENCES `tp_finaljeg`.`situacion_laboral` (`ID_SituacionLaboral`))
