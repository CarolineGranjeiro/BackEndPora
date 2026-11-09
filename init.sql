CREATE DATABASE IF NOT EXISTS inspecao_dormentes;
USE inspecao_dormentes;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(50) NOT NULL,
    token_recuperacao VARCHAR(255),
    expiracao_token DATETIME
);

CREATE TABLE IF NOT EXISTS catalogo_defeitos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    codigo_defeito VARCHAR(20) NOT NULL UNIQUE,
    severidade VARCHAR(20) NOT NULL
);

INSERT INTO catalogo_defeitos (
    nome,
    descricao,
    codigo_defeito,
    severidade
)
VALUES
    (
        'Trinca / Rachadura',
        'Rachadura longitudinal ou transversal excedendo o limite tolerável na peça',
        'DEF-01',
        'Alta'
    ),
    (
        'Presença de Fungos',
        'Presença de sinais visíveis de apodrecimento ou micélios de fungos xilófagos',
        'DEF-02',
        'Média'
    ),
    (
        'Ataque de Brocas',
        'Orifícios, galerias ou sinais de infestação causada por insetos xilófagos',
        'DEF-03',
        'Alta'
    ),
    (
        'Empenamento',
        'Distorção geométrica acentuada que compromete o assentamento adequado na via',
        'DEF-04',
        'Baixa'
    ),
    (
        'Problemas de Umidade',
        'Teor de umidade inadequado ou encharcamento excessivo da madeira',
        'DEF-05',
        'Média'
    )
ON DUPLICATE KEY UPDATE codigo_defeito = codigo_defeito;

CREATE TABLE IF NOT EXISTS fornecedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) NOT NULL UNIQUE,
    contato VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS contratos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_contrato VARCHAR(50) NOT NULL UNIQUE,
    fornecedor_id INT NOT NULL,
    data_inicio DATE,
    data_fim DATE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_contrato_fornecedor
        FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedores(id)
);

CREATE TABLE IF NOT EXISTS lotes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_lote VARCHAR(50) NOT NULL UNIQUE,
    fornecedor_id INT NOT NULL,
    data_recebimento DATE NOT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_lote_fornecedor
        FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedores(id)
);

CREATE TABLE IF NOT EXISTS localizacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patio VARCHAR(100) NOT NULL,
    setor VARCHAR(100) NOT NULL,
    posicao VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_localizacao
        UNIQUE (patio, setor, posicao)
);

CREATE TABLE IF NOT EXISTS dormentes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_serie VARCHAR(50) NOT NULL UNIQUE,
    qr_code VARCHAR(100) UNIQUE,
    tipo_cadastro VARCHAR(20) NOT NULL,
    lote_id INT NOT NULL,
    contrato_id INT NOT NULL,
    localizacao_id INT NOT NULL,
    material VARCHAR(50) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    tipo_madeira VARCHAR(50),
    classe_madeira VARCHAR(50),
    especie VARCHAR(100),
    comprimento DOUBLE NOT NULL,
    largura DOUBLE NOT NULL,
    altura DOUBLE NOT NULL,
    quantidade_entrada INT NOT NULL,
    estoque_minimo INT NOT NULL DEFAULT 0,
    status VARCHAR(30),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_dormente_lote
        FOREIGN KEY (lote_id)
        REFERENCES lotes(id),

    CONSTRAINT fk_dormente_contrato
        FOREIGN KEY (contrato_id)
        REFERENCES contratos(id),

    CONSTRAINT fk_dormente_localizacao
        FOREIGN KEY (localizacao_id)
        REFERENCES localizacoes(id),

    CONSTRAINT chk_dormente_tipo_cadastro
        CHECK (tipo_cadastro IN ('LOTE', 'INDIVIDUAL')),

    CONSTRAINT chk_dormente_dimensoes
        CHECK (
            comprimento > 0
            AND largura > 0
            AND altura > 0
        ),

    CONSTRAINT chk_dormente_quantidade_entrada
        CHECK (quantidade_entrada > 0),

    CONSTRAINT chk_dormente_individual
        CHECK (
            tipo_cadastro <> 'INDIVIDUAL'
            OR quantidade_entrada = 1
        ),

    CONSTRAINT chk_dormente_estoque_minimo
        CHECK (estoque_minimo >= 0)
);

CREATE TABLE IF NOT EXISTS inspecoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dormente_id INT NOT NULL,
    usuario_id INT NOT NULL,
    data_inspecao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantidade_observada INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    observacoes TEXT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_inspecao_dormente
        FOREIGN KEY (dormente_id)
        REFERENCES dormentes(id),

    CONSTRAINT fk_inspecao_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id),

    CONSTRAINT chk_inspecao_quantidade
        CHECK (quantidade_observada > 0),

    INDEX idx_inspecao_dormente_data (
        dormente_id,
        data_inspecao
    ),

    INDEX idx_inspecao_data (data_inspecao)
);

CREATE TABLE IF NOT EXISTS detalhes_inspecao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inspecao_id INT NOT NULL,
    defeito_id INT NOT NULL,

    CONSTRAINT fk_detalhe_inspecao
        FOREIGN KEY (inspecao_id)
        REFERENCES inspecoes(id),

    CONSTRAINT fk_detalhe_defeito
        FOREIGN KEY (defeito_id)
        REFERENCES catalogo_defeitos(id),

    CONSTRAINT uk_inspecao_defeito
        UNIQUE (inspecao_id, defeito_id)
);

CREATE TABLE IF NOT EXISTS conferencias_estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dormente_id INT NOT NULL,
    usuario_id INT NOT NULL,
    saldo_sistema INT NOT NULL,
    quantidade_contada INT NOT NULL,
    observacoes TEXT,
    data_conferencia DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_conferencia_dormente
        FOREIGN KEY (dormente_id)
        REFERENCES dormentes(id),

    CONSTRAINT fk_conferencia_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id),

    CONSTRAINT chk_conferencia_saldo
        CHECK (saldo_sistema >= 0),

    CONSTRAINT chk_conferencia_quantidade
        CHECK (quantidade_contada >= 0),

    INDEX idx_conferencia_dormente_data (
        dormente_id,
        data_conferencia
    ),

    INDEX idx_conferencia_data (data_conferencia)
);

CREATE TABLE IF NOT EXISTS movimentacoes_estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dormente_id INT NOT NULL,
    usuario_id INT NOT NULL,
    conferencia_id INT UNIQUE,
    tipo VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    data_movimentacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_movimentacao_dormente
        FOREIGN KEY (dormente_id)
        REFERENCES dormentes(id),

    CONSTRAINT fk_movimentacao_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id),

    CONSTRAINT fk_movimentacao_conferencia
        FOREIGN KEY (conferencia_id)
        REFERENCES conferencias_estoque(id),

    CONSTRAINT chk_movimentacao_tipo_quantidade
        CHECK (
            (tipo = 'ENTRADA' AND quantidade > 0)
            OR (tipo = 'SAIDA' AND quantidade < 0)
            OR (tipo = 'AJUSTE' AND quantidade <> 0)
        ),

    INDEX idx_movimentacao_dormente_data (
        dormente_id,
        data_movimentacao
    ),

    INDEX idx_movimentacao_data (data_movimentacao)
);

CREATE TABLE IF NOT EXISTS historico_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_atividade VARCHAR(50) NOT NULL UNIQUE,
    usuario_id INT NOT NULL,
    acao VARCHAR(50) NOT NULL,
    entidade VARCHAR(50) NOT NULL,
    entidade_id INT NOT NULL,
    descricao TEXT NOT NULL,
    dados_anteriores JSON,
    dados_novos JSON,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_historico_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS relatorios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    formato VARCHAR(10) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PROCESSANDO',
    data_inicio DATE,
    data_fim DATE,
    material VARCHAR(50),
    localizacao_id INT,
    graficos_selecionados JSON,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    concluido_em DATETIME,

    CONSTRAINT fk_relatorio_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id),

    CONSTRAINT fk_relatorio_localizacao
        FOREIGN KEY (localizacao_id)
        REFERENCES localizacoes(id),

    CONSTRAINT chk_relatorio_tipo
        CHECK (tipo IN ('ACOMPANHAMENTO', 'INVENTARIO')),

    CONSTRAINT chk_relatorio_formato
        CHECK (formato IN ('PDF', 'XLSX', 'CSV')),

    CONSTRAINT chk_relatorio_status
        CHECK (
            status IN ('PROCESSANDO', 'CONCLUIDO', 'FALHOU')
        ),

    CONSTRAINT chk_relatorio_periodo
        CHECK (
            data_inicio IS NULL
            OR data_fim IS NULL
            OR data_fim >= data_inicio
        )
);

CREATE TABLE IF NOT EXISTS relatorio_inspecoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    relatorio_id INT NOT NULL,
    inspecao_id INT NOT NULL,

    CONSTRAINT fk_relatorio_inspecao_relatorio
        FOREIGN KEY (relatorio_id)
        REFERENCES relatorios(id),

    CONSTRAINT fk_relatorio_inspecao_inspecao
        FOREIGN KEY (inspecao_id)
        REFERENCES inspecoes(id),

    CONSTRAINT uk_relatorio_inspecao
        UNIQUE (relatorio_id, inspecao_id)
);