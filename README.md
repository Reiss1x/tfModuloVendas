script sql para consulta dos pedidos:

SELECT p.cl_cod AS pedido_cod, p.cl_name AS cliente_nome, i.cl_prodcod AS prodcod, i.cl_quantidade AS prod_quantidade FROM Pedido p LEFT JOIN Item i ON p.cl_cod = i.pedido_id
