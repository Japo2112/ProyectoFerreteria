-- 1. ESPECIFICACIÓN
CREATE OR REPLACE PACKAGE PKG_REPORTES AS
  FUNCTION FN_TOTAL_VENDIDO_VENDEDOR(
    p_vendedor_id IN VENDEDOR.id_vendedor%TYPE,
    p_fecha_inicio IN DATE,
    p_fecha_fin IN DATE
  ) RETURN NUMBER;
END PKG_REPORTES;
/

-- 2. CUERPO
CREATE OR REPLACE PACKAGE BODY PKG_REPORTES AS
  FUNCTION FN_TOTAL_VENDIDO_VENDEDOR(
    p_vendedor_id IN VENDEDOR.id_vendedor%TYPE,
    p_fecha_inicio IN DATE,
    p_fecha_fin IN DATE
  ) RETURN NUMBER AS
    v_total NUMBER := 0;
  BEGIN
    SELECT NVL(SUM(total), 0)
    INTO v_total
    FROM FACTURA
    WHERE vendedor_id = p_vendedor_id
      AND fecha BETWEEN p_fecha_inicio AND p_fecha_fin
      AND estado != 'Credito';
      
    RETURN v_total;
  END FN_TOTAL_VENDIDO_VENDEDOR;
END PKG_REPORTES;
/