script sql para consulta dos pedidos:

```SELECT p.cl_cod AS pedido_cod, p.cl_name AS cliente_nome, i.cl_prodcod AS prodcod, i.cl_quantidade AS prod_quantidade FROM Pedido p LEFT JOIN Item i ON p.cl_cod = i.pedido_id```

Json para cadastro de produtos:
```
[
    {
        "desc": "Produto 1",
        "preco": 10,
        "qntMin": 5,
        "qntMax": 30,
        "qnt": 20
    },
    {
        "desc": "Produto 2",
        "preco": 15,
        "qntMin": 10,
        "qntMax": 100,
        "qnt": 30
    }
]
```
Json para criar pedido:
```
[
    {
        "name": "Nome 1",
        "items": [
            {
                "prodcod": 1,
                "quantidade": 2
            },
            {
                "prodcod": 2,
                "quantidade": 1
            } 
        ]
    },
    {
        "name": "Nome 2",
        "items": [
            {
                "prodcod": 2,
                "quantidade": 3
            },
            {
                "prodcod": 1,
                "quantidade": 1
            }
        ]
    }
]
```
