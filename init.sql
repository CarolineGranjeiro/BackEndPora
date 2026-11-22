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
    descricao TEXT,
    codigo_defeito VARCHAR(20),
    severidade VARCHAR(20)
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

CREATE TABLE IF NOT EXISTS localizacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patio VARCHAR(100) NOT NULL,
    setor VARCHAR(100) NOT NULL,
    posicao VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_localizacao UNIQUE (patio, setor, posicao)
);

CREATE TABLE IF NOT EXISTS lotes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_lote VARCHAR(50) NOT NULL UNIQUE,
    fornecedor_id INT NOT NULL,
    contrato_id INT NOT NULL,
    data_recebimento DATE,
    criado_em DATETIME(6),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id),
    FOREIGN KEY (contrato_id) REFERENCES contratos(id)
);

CREATE TABLE IF NOT EXISTS dormentes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_serie VARCHAR(50) NOT NULL UNIQUE,
    qr_code VARCHAR(100) NOT NULL UNIQUE,
    tipo_cadastro VARCHAR(20),
    lote_id INT NOT NULL,
    contrato_id INT NOT NULL,
    localizacao_id INT NOT NULL,
    material VARCHAR(50),
    descricao TEXT,
    tipo_madeira VARCHAR(50),
    classe_madeira VARCHAR(50),
    especie VARCHAR(100),
    comprimento DECIMAL(10,2),
    largura DECIMAL(10,2),
    altura DECIMAL(10,2),
    status VARCHAR(50),
    criado_em DATETIME(6),
    atualizado_em DATETIME(6),
    FOREIGN KEY (lote_id) REFERENCES lotes(id),
    FOREIGN KEY (contrato_id) REFERENCES contratos(id),
    FOREIGN KEY (localizacao_id) REFERENCES localizacoes(id)
);

CREATE TABLE IF NOT EXISTS inspecoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dormente_id INT NOT NULL,
    usuario_id INT NOT NULL,
    data_inspecao DATETIME(6) NOT NULL,
    quantidade_observada INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    observacoes TEXT,
    criado_em DATETIME(6),
    FOREIGN KEY (dormente_id) REFERENCES dormentes(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS detalhes_inspecao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inspecao_id INT NOT NULL,
    defeito_id INT NOT NULL,
    observacao VARCHAR(255),
    CONSTRAINT uk_inspecao_defeito UNIQUE (inspecao_id, defeito_id),
    FOREIGN KEY (inspecao_id) REFERENCES inspecoes(id),
    FOREIGN KEY (defeito_id) REFERENCES catalogo_defeitos(id)
);

-- TABELAS DE RELATÓRIOS
CREATE TABLE IF NOT EXISTS relatorios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,

    -- ACOMPANHAMENTO ou INVENTARIO.
    tipo VARCHAR(30) NOT NULL,

    -- PDF, XLSX ou CSV.
    formato VARCHAR(10) NOT NULL,

    -- PROCESSANDO, CONCLUIDO ou FALHOU.
    status VARCHAR(20) NOT NULL DEFAULT 'PROCESSANDO',

    -- Filtros escolhidos na tela.
    data_inicio DATE,
    data_fim DATE,
    material VARCHAR(50),
    localizacao VARCHAR(150),

    -- Lista dos gráficos selecionados para o PDF.
    graficos_selecionados JSON,

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    concluido_em DATETIME,

    CONSTRAINT fk_relatorio_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id),

    INDEX idx_relatorio_criado_em (criado_em),
    INDEX idx_relatorio_status (status)
);

CREATE TABLE IF NOT EXISTS relatorio_inspecoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    relatorio_id INT NOT NULL,
    inspecao_id INT NOT NULL,

    CONSTRAINT fk_relatorio_inspecao_relatorio
        FOREIGN KEY (relatorio_id) REFERENCES relatorios(id),

    CONSTRAINT fk_relatorio_inspecao_inspecao
        FOREIGN KEY (inspecao_id) REFERENCES inspecoes(id),

    CONSTRAINT uk_relatorio_inspecao
        UNIQUE (relatorio_id, inspecao_id)
);