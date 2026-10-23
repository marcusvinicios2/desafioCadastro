CREATE DATABASE cadastro_pets;
GO
USE cadastro_pets;
GO
CREATE TABLE pet (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome NVARCHAR(100) NOT NULL,
    tipo NVARCHAR(20) NOT NULL,
    sexo NVARCHAR(20),
    endereco NVARCHAR(200),
    idade INT,
    peso DECIMAL(5,2),
    raca NVARCHAR(100)
);