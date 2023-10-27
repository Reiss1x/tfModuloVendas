script sql para consulta dos pedidos:

SELECT p.cl_cod AS pedido_cod, p.cl_name AS pedido_name,
       i.cl_cod AS item_cod, i.cl_prod_Id AS item_prodId, i.cl_quantidade AS item_quantidade
FROM Pedido p
LEFT JOIN Item i ON p.cl_cod = i.pedido_id;
