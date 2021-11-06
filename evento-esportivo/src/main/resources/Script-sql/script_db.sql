-- Table: usuario
-- -----------------------------------------------------
CREATE TABLE usuario (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome_completo VARCHAR(60) NOT NULL,
  apelido VARCHAR(30),
  email VARCHAR(60) NOT NULL,	
  senha VARCHAR(255) NOT NULL,
  PRIMARY KEY (id));

-- Table: evento
-- -----------------------------------------------------
CREATE TABLE evento (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(255) NOT NULL,
  data DATE NOT NULL,
  hora TIME NOT NULL,
  endereco VARCHAR(255) NOT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table: convidado
-- -----------------------------------------------------
CREATE TABLE convidado (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  documento VARCHAR(255) NULL DEFAULT NULL,
  nome VARCHAR(255) NULL DEFAULT NULL,
  evento_id BIGINT NULL DEFAULT NULL,
  FOREIGN KEY (id_evento) REFERENCES evento(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table: hibernate_sequence
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventosesportivos`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
