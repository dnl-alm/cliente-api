CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    idade INT NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE
);

INSERT INTO cliente (nome, idade, cpf) VALUES
('João Silva', 28, '123.456.789-01'),
('Maria Oliveira', 34, '987.654.321-00'),
('Carlos Souza', 22, '111.222.333-44'),
('Ana Pereira', 45, '555.665.777-88'),
('Lucas Ferreira', 30, '999.888.777-66');