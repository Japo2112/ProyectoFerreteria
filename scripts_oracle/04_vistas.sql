CREATE OR REPLACE VIEW V_TOP_VENDEDORES_REGION AS
SELECT
  v.id_vendedor,
  v.nombre AS nombre_vendedor,
  s.region,
  
  -- Funciones de agregación: Sumamos el total y contamos las facturas
  SUM(f.total) AS total_vendido,
  COUNT(f.id_factura) AS numero_ventas,
  
  -- Función analítica (RANK):
  -- Calcula un ranking #1, #2, #3... para cada vendedor
  -- PARTITION BY s.region: Reinicia el conteo en cada región (ej. Norte, Sur)
  -- ORDER BY SUM(f.total) DESC: Ordena de mayor a menor venta
  RANK() OVER (PARTITION BY s.region ORDER BY SUM(f.total) DESC) AS ranking_regional
FROM
  FACTURA f
-- Unimos las tablas necesarias para obtener los nombres y la región
JOIN
  VENDEDOR v ON f.vendedor_id = v.id_vendedor
JOIN
  SUCURSAL s ON f.sucursal_id = s.id_sucursal
WHERE
  f.estado != 'Credito' -- Ignoramos facturas que fueron anuladas con Nota de Crédito
GROUP BY
  -- Agrupamos por la región y los datos del vendedor
  s.region, v.id_vendedor, v.nombre;
  
  --SELECT * FROM V_TOP_VENDEDORES_REGION WHERE region = 'Norte' AND ranking_regional <= 10;
  
  
  CREATE OR REPLACE VIEW V_TOP_CLIENTES_SUCURSAL AS
SELECT
  c.id_cliente,
  c.nombre AS nombre_cliente,
  s.id_sucursal,
  s.nombre AS nombre_sucursal,
  SUM(f.total) AS total_comprado,
  -- Ranking de clientes DENTRO de cada sucursal
  RANK() OVER (PARTITION BY s.id_sucursal ORDER BY SUM(f.total) DESC) AS ranking_sucursal
FROM
  FACTURA f
JOIN
  CLIENTE c ON f.cliente_id = c.id_cliente
JOIN
  SUCURSAL s ON f.sucursal_id = s.id_sucursal
WHERE
  f.estado != 'Credito'
GROUP BY
  s.id_sucursal, s.nombre, c.id_cliente, c.nombre;
  
  
  --SELECT * FROM V_TOP_CLIENTES_SUCURSAL WHERE nombre_sucursal = 'Sucursal Central' AND ranking_sucursal <= 10;
  
  CREATE OR REPLACE VIEW V_RESUMEN_INVENTARIO AS
SELECT
  p.id_producto,
  p.nombre AS nombre_producto,
  c.nombre AS categoria,
  s.nombre AS sucursal,
  ex.cantidad_stock,
  ex.stock_minimo,
  -- Alerta simple si el stock es bajo
  CASE
    WHEN ex.cantidad_stock <= ex.stock_minimo THEN 'BAJO STOCK'
    ELSE 'OK'
  END AS estado_stock
FROM
  EXISTENCIAS_SUCURSAL ex
JOIN
  PRODUCTO p ON ex.producto_id = p.id_producto
JOIN
  SUCURSAL s ON ex.sucursal_id = s.id_sucursal
LEFT JOIN
  CATEGORIA c ON p.categoria_id = c.id_categoria;
  
  --SELECT * FROM V_RESUMEN_INVENTARIO WHERE estado_stock = 'BAJO STOCK';