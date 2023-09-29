-- Tabela para as categorias
CREATE TABLE tb_categorias (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    ativo BOOLEAN
);

-- Tabela para as subcategorias
CREATE TABLE tb_subcategorias (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    ativo BOOLEAN,
    categoria_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES tb_categorias(id)
);

-- Tabela para as cores
CREATE TABLE tb_cores (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    ativo BOOLEAN
);

-- Tabela para os produtos
CREATE TABLE tb_produtos (
    id CHAR(36) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    desconto DECIMAL(5, 2) NOT NULL,
    preco_final DECIMAL(10, 2) NOT NULL,
    qtd_em_estoque INTEGER,
    genero VARCHAR(50),
    tamanho_letra VARCHAR(100),
    tamanho_nro INTEGER,
    ativo BOOLEAN,
    categoria_id BIGINT,
    subcategoria_id BIGINT,
    cor_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES tb_categorias(id),
    FOREIGN KEY (subcategoria_id) REFERENCES tb_subcategorias(id),
    FOREIGN KEY (cor_id) REFERENCES tb_cores(id)
);


