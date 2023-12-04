DROP SCHEMA tp_integrador;

CREATE DATABASE tp_integrador;
USE tp_integrador;

CREATE TABLE `cliente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255),
  `telefono` VARCHAR(255),
  PRIMARY KEY (`id`)
);

CREATE TABLE `tecnico` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `estado` BOOLEAN,
  PRIMARY KEY (`id`)
);

CREATE TABLE `reporte` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);

CREATE TABLE `incidente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `estado` ENUM('ABIERTO', 'EN_PROCESO', 'RESUELTO', 'CERRADO'),
  `reporte_id` BIGINT,
  `tecnico_id` BIGINT,
  `cliente_id` BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`reporte_id`) REFERENCES `reporte`(`id`),
  FOREIGN KEY (`tecnico_id`) REFERENCES `tecnico`(`id`),
  FOREIGN KEY (`cliente_id`) REFERENCES `cliente`(`id`)
);

CREATE TABLE `problema` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(255),
  `tipo` ENUM('LEVE', 'MEDIANO', 'COMPLEJO'),
  `fechaTentativa` TIME,
  `codigo` VARCHAR(255),
  `tiempoEstimado` TIME,
  `incidente_id` BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`incidente_id`) REFERENCES `incidente`(`id`)
);

CREATE TABLE `tecnico_especialidadSistema` (
  `tecnico_id` BIGINT NOT NULL,
  `especialidadSistema` ENUM('WINDOWS', 'MACOS', 'LINUX_UBUNTU'),
  FOREIGN KEY (`tecnico_id`) REFERENCES `tecnico`(`id`)
);

CREATE TABLE `tecnico_especialidadSoporte` (
  `tecnico_id` BIGINT NOT NULL,
  `especialidadSoporte` ENUM('SAP', 'TANGO'),
  FOREIGN KEY (`tecnico_id`) REFERENCES `tecnico`(`id`)
);