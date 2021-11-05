/* ********* TABELA DE USUÁRIOS ********************** */

CREATE TABLE usuario (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome_completo VARCHAR(60) NOT NULL,
  apelido VARCHAR(30),
  email VARCHAR(60) NOT NULL,	
  senha VARCHAR(255) NOT NULL,
  PRIMARY KEY (id));

/* ******** TABELA DE EVENTOS ************************ */

CREATE TABLE evento (
  id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(255) NOT NULL,
  data DATE NOT NULL,
  hora TIME NOT NULL,
  endereco VARCHAR(255) NOT NULL,
  PRIMARY KEY (id));

/* ****** TABELA DE PARTICIPANTES DO EVENTO ********** */

CREATE TABLE participante_evento (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	convidado TINYINT NOT NULL,
	id_usuario BIGINT(20) NOT NULL,
	id_evento BIGINT(20) NOT NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_evento) REFERENCES evento(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

