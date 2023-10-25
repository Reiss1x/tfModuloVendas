CREATE TABLE Produto (
    cod Long,
    desc varchar(255),
    preco int,
    qntMin int,
    qntMax int,
    qnt int
);

INSERT INTO Produto (cod,desc,preco,qntMin,qntMax,qnt) VALUES 
    (10, 'Produto 1',23,1,3,2),
    (20, 'Produto 2',4,3,6,5),
    (30, 'Produto 3',58,4,5,5),
    (40, 'Produto 4',243,2,9,58),
    (50, 'Produto 5',45,5,7,7);