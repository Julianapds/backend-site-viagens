create database db_rotasdosol;

use db_rotasdosol;

CREATE TABLE Cliente (
    id_cliente INT PRIMARY KEY,
    email VARCHAR(255),
    cpf VARCHAR(11),
    telefone VARCHAR(15),
    endereco VARCHAR(255),
    UNIQUE (email, cpf)
);

CREATE TABLE Destino (
    id_destino INT PRIMARY KEY,
    nome VARCHAR(255),
    pais VARCHAR(255),
    cidade VARCHAR(255)
);

CREATE TABLE Hospedagem (
    id_hospedagem INT PRIMARY KEY,
    nome_hotel VARCHAR(255),
    tipo_quarto VARCHAR(255),
    data_checkin DATE,
    data_checkout DATE,
    valor_pernoite DOUBLE,
    id_destino INT,
    FOREIGN KEY (id_destino) REFERENCES Destino(id_destino)
);

CREATE TABLE Voo (
    id_voo INT PRIMARY KEY,
    companhia_aerea VARCHAR(255),
    data_partida DATE,
    data_chegada DATE,
    valor_preco DOUBLE,
    id_destino INT,
    FOREIGN KEY (id_destino) REFERENCES Destino(id_destino)
);

CREATE TABLE Pacote (
    id_pacote INT PRIMARY KEY,
    valor_preco DOUBLE,
    id_hospedagem INT,
    id_voo INT,
    FOREIGN KEY (id_hospedagem) REFERENCES Hospedagem(id_hospedagem),
    FOREIGN KEY (id_voo) REFERENCES Voo(id_voo)
);

CREATE TABLE StatusReserva (
    id_status INT PRIMARY KEY,
    nome VARCHAR(255),
    data_criacao DATE
);

CREATE TABLE Reserva (
    id_reserva INT PRIMARY KEY,
    id_cliente INT,
    id_pacote INT,
    id_hospedagem INT,
    id_voo INT,
    data_reserva DATE,
    id_status INT,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_pacote) REFERENCES Pacote(id_pacote),
    FOREIGN KEY (id_hospedagem) REFERENCES Hospedagem(id_hospedagem),
    FOREIGN KEY (id_voo) REFERENCES Voo(id_voo),
    FOREIGN KEY (id_status) REFERENCES StatusReserva(id_status)
);

