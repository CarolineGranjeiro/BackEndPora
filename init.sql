CREATE DATABASE IF NOT EXISTS inspecao_dormentes;
USE inspecao_dormentes;

CREATE TABLE IF NOT EXISTS usuarios (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(50) NOT NULL,
    token_recuperacao VARCHAR(255) DEFAULT NULL,
    expiracao_token DATETIME DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS catalogo_defeitos (
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                                 nome VARCHAR(100) NOT NULL,
    descricao TEXT
    );

CREATE TABLE IF NOT EXISTS fornecedores (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(20) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS contratos (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         fornecedor_id INT NOT NULL,
                                         data_inicio DATE NOT NULL,
                                         data_fim DATE NOT NULL,
                                         FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)
    );

CREATE TABLE IF NOT EXISTS lotes (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     contrato_id INT NOT NULL,
                                     data_recebimento DATE NOT NULL,
                                     FOREIGN KEY (contrato_id) REFERENCES contratos(id)
    );

CREATE TABLE IF NOT EXISTS dormentes (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         lote_id INT NOT NULL,
                                         numero_serie VARCHAR(50) UNIQUE NOT NULL,
    qr_code VARCHAR(255) UNIQUE NOT NULL,
    tipo_madeira VARCHAR(50),
    classe VARCHAR(50),
    comprimento DECIMAL(5,2),
    FOREIGN KEY (lote_id) REFERENCES lotes(id)
    );

CREATE TABLE IF NOT EXISTS inspeccoes (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          dormente_id INT NOT NULL,
                                          usuario_id INT NOT NULL,
                                          data_inspecao DATETIME DEFAULT CURRENT_TIMESTAMP,
                                          status VARCHAR(50) NOT NULL,
    FOREIGN KEY (dormente_id) REFERENCES dormentes(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
    );

CREATE TABLE IF NOT EXISTS detalhes_inspecao (
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                                 inspecao_id INT NOT NULL,
                                                 defeito_id INT NOT NULL,
                                                 FOREIGN KEY (inspecao_id) REFERENCES inspeccoes(id),
    FOREIGN KEY (defeito_id) REFERENCES catalogo_defeitos(id)
    );