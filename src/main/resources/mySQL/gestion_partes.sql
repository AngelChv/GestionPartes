-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gestionpartes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestionpartes` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestionpartes`;

-- -----------------------------------------------------
-- Table `gestionpartes`.`grupos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`grupos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`grupos`
(
    `id_grupo`     INT          NOT NULL AUTO_INCREMENT,
    `nombre_grupo` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id_grupo`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `gestionpartes`.`alumnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`alumnos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`alumnos`
(
    `id_alum`           INT          NOT NULL AUTO_INCREMENT,
    `id_grupo`          INT          NOT NULL,
    `puntos_acumulados` INT          NOT NULL,
    `nombre_alum`       VARCHAR(255) NULL DEFAULT NULL,
    `numero_expediente` INT          NULL DEFAULT NULL,
    PRIMARY KEY (`id_alum`),
    INDEX `FKoif6noujgnb1q4hfucdj3by8q` (`id_grupo` ASC)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci;

ALTER TABLE `gestionpartes`.`alumnos`
    ADD CONSTRAINT `FKoif6noujgnb1q4hfucdj3by8q`
        FOREIGN KEY (`id_grupo`)
            REFERENCES `gestionpartes`.`grupos` (`id_grupo`);


-- -----------------------------------------------------
-- Table `gestionpartes`.`profesores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`profesores`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`profesores`
(
    `id_profesor`     INT                                   NOT NULL AUTO_INCREMENT,
    `contrasena`      VARCHAR(255)                          NULL DEFAULT NULL,
    `nombre`          VARCHAR(255)                          NULL DEFAULT NULL,
    `numero_asignado` VARCHAR(4)                            NULL DEFAULT NULL,
    `tipo`            ENUM ('PROFESOR', 'JEFE_DE_ESTUDIOS') NOT NULL,
    PRIMARY KEY (`id_profesor`),
    UNIQUE INDEX `UK_p6ltb4s5eu3ymeanq6rdw944v` (`numero_asignado` ASC)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci;


-- -----------------------------------------------------
-- Table `gestionpartes`.`tipos_parte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`tipos_parte`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`tipos_parte`
(
    `id_tipo` INT PRIMARY KEY AUTO_INCREMENT,
    `color`   ENUM ('VERDE', 'NARANJA', 'ROJO') UNIQUE,
    `puntos`  INT NOT NULL
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `gestionpartes`.`partes_incidencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`partes_incidencia`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`partes_incidencia`
(
    `id_alum`       INT          NULL DEFAULT NULL,
    `id_parte`      INT          NOT NULL AUTO_INCREMENT,
    `id_profesor`   INT          NULL DEFAULT NULL,
    `descripcion`   VARCHAR(255) NULL DEFAULT NULL,
    `fecha`         DATE         NULL DEFAULT NULL,
    `hora`          TIME         NULL DEFAULT NULL,
    `sancion`       VARCHAR(255) NULL DEFAULT NULL,
    `id_tipo_parte` INT          NULL DEFAULT NULL,
    PRIMARY KEY (`id_parte`),
    INDEX `FKqrx661g5lij25bl2plx6cb2pl` (`id_alum` ASC),
    INDEX `FKniytl2x2lvm632ic1904a1bhb` (`id_profesor` ASC),
    CONSTRAINT FK_PART_TIPO FOREIGN KEY (`id_tipo_parte`) REFERENCES `gestionpartes`.`tipos_parte` (`id_tipo`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci;

ALTER TABLE `gestionpartes`.`partes_incidencia`
    ADD CONSTRAINT `FKniytl2x2lvm632ic1904a1bhb`
        FOREIGN KEY (`id_profesor`)
            REFERENCES `gestionpartes`.`profesores` (`id_profesor`);

ALTER TABLE `gestionpartes`.`partes_incidencia`
    ADD CONSTRAINT `FKqrx661g5lij25bl2plx6cb2pl`
        FOREIGN KEY (`id_alum`)
            REFERENCES `gestionpartes`.`alumnos` (`id_alum`);

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- Inserción de tipos_parte:
INSERT INTO tipos_parte (color, puntos)
VALUES ('VERDE', 4),
       ('NARANJA', 6),
       ('ROJO', 12);

-- Inserción de profesores en la tabla 'profesores'
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena)
VALUES ('Juan Perez', 'JEFE_DE_ESTUDIOS', '1001', 'ce5ca673d13b36118d54a7cf13aeb0ca012383bf771e713421b4d1fd841f539a');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena)
VALUES ('Alberto Perez', 'profesor', '1002', 'ce5ca673d13b36118d54a7cf13aeb0ca012383bf771e713421b4d1fd841f539a');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena)
VALUES ('Maria Lopez', 'profesor', '1003', '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena)
VALUES ('Carlos Sanchez', 'profesor', '1004', '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena)
VALUES ('Laura Gomez', 'profesor', '1005', '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena)
VALUES ('Fernando Ruiz', 'profesor', '1006', '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena)
VALUES ('Saul Profesor', 'PROFESOR', '6666', 'b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena)
VALUES ('Saul Jefe', 'JEFE_DE_ESTUDIOS', '1111', 'b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79');
-- Inserción de grupos en la tabla 'grupos'
INSERT INTO grupos (nombre_grupo)
VALUES ('1º ESO A');
INSERT INTO grupos (nombre_grupo)
VALUES ('1º ESO B');
INSERT INTO grupos (nombre_grupo)
VALUES ('2º ESO A');
INSERT INTO grupos (nombre_grupo)
VALUES ('2º ESO B');
INSERT INTO grupos (nombre_grupo)
VALUES ('3º ESO A');
INSERT INTO grupos (nombre_grupo)
VALUES ('3º ESO B');
INSERT INTO grupos (nombre_grupo)
VALUES ('4º ESO A');
INSERT INTO grupos (nombre_grupo)
VALUES ('4º ESO B');

-- Inserción de alumnos en la tabla 'alumnos'
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (1, 4, 'Alejandro García', 1001);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (1, 6, 'María Fernández', 1002);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (2, 4, 'Juan López', 1003);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (2, 12, 'Laura Martínez', 1004);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (3, 9, 'Pablo Sánchez', 1005);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (3, 1, 'Sara González', 1006);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (4, 4, 'David Rodríguez', 1007);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (4, 8, 'Lucía Pérez', 1008);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (5, 7, 'Daniel Gómez', 1009);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (5, 20, 'Elena Díaz', 1010);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (6, 33, 'Javier Ruiz', 1011);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (6, 4, 'Marta Morales', 1012);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (7, 4, 'Carlos Álvarez', 1013);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (7, 9, 'Ana Ortega', 1014);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (8, 16, 'Luis Navarro', 1015);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (8, 10, 'Carmen Ramírez', 1016);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (1, 4, 'Alberto Torres', 1017);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (2, 1, 'Paula Ibáñez', 1018);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (3, 8, 'Miguel Romero', 1019);
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente)
VALUES (4, 3, 'Isabel Hernández', 1020);

INSERT INTO partes_incidencia (id_alum, id_profesor, descripcion, fecha, hora, sancion, id_tipo_parte)
VALUES (1, 1, 'Incidente menor', '2024-01-01', '09:00', 'Advertencia', 1),
       (2, 2, 'Incidente moderado', '2024-01-02', '10:00', 'Suspensión', 2),
       (3, 3, 'Incidente grave', '2024-01-03', '11:00', 'Expulsión', 3),
       (4, 4, 'Incidente menor', '2024-01-04', '12:00', 'Advertencia', 1),
       (5, 5, 'Incidente moderado', '2024-01-05', '13:00', 'Suspensión', 2),
       (6, 6, 'Incidente grave', '2024-01-06', '14:00', 'Expulsión', 3),
       (7, 1, 'Incidente menor', '2024-01-07', '15:00', 'Advertencia', 1),
       (8, 2, 'Incidente moderado', '2024-01-08', '16:00', 'Suspensión', 2),
       (9, 3, 'Incidente grave', '2024-01-09', '17:00', 'Expulsión', 3),
       (10, 4, 'Incidente menor', '2024-01-10', '18:00', 'Advertencia', 1),
       (11, 5, 'Incidente moderado', '2024-01-11', '09:00', 'Suspensión', 2),
       (12, 6, 'Incidente grave', '2024-01-12', '10:00', 'Expulsión', 3),
       (13, 1, 'Incidente menor', '2024-01-13', '11:00', 'Advertencia', 1),
       (14, 2, 'Incidente moderado', '2024-01-14', '12:00', 'Suspensión', 2),
       (15, 3, 'Incidente grave', '2024-01-15', '13:00', 'Expulsión', 3),
       (16, 4, 'Incidente menor', '2024-01-16', '14:00', 'Advertencia', 1),
       (17, 5, 'Incidente moderado', '2024-01-17', '15:00', 'Suspensión', 2),
       (18, 6, 'Incidente grave', '2024-01-18', '16:00', 'Expulsión', 3),
       (19, 1, 'Incidente menor', '2024-01-19', '17:00', 'Advertencia', 1),
       (20, 2, 'Incidente moderado', '2024-01-20', '18:00', 'Suspensión', 2);


