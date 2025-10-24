CREATE OR REPLACE PACKAGE PKG_SEGURIDAD AS

  -- Requisito #11: Procedimiento para crear un usuario HÍBRIDO (BD + App)
  PROCEDURE PR_CREAR_USUARIO_HIBRIDO(
    p_nombre       IN USUARIO.nombre%TYPE,
    p_username     IN USUARIO.username%TYPE, -- ej: 'vendedor_jared'
    p_clave        IN VARCHAR2,             -- ej: 'clave123'
    p_rol_app_id   IN USUARIO.rol_app_id%TYPE, -- ej: 2 (VENDEDOR)
    p_rol_db_nombre IN VARCHAR2                -- ej: 'ROL_VENDEDOR_DB'
  );

END PKG_SEGURIDAD;
/

CREATE OR REPLACE PACKAGE BODY PKG_SEGURIDAD AS

  PROCEDURE PR_CREAR_USUARIO_HIBRIDO(
    p_nombre       IN USUARIO.nombre%TYPE,
    p_username     IN USUARIO.username%TYPE,
    p_clave        IN VARCHAR2,
    p_rol_app_id   IN USUARIO.rol_app_id%TYPE,
    p_rol_db_nombre IN VARCHAR2
  ) AS
    v_sql VARCHAR2(1000);
  BEGIN
    
    -- 1. Crear el usuario de Base de Datos
    -- (Oracle automáticamente pone el nombre en MAYÚSCULAS)
    v_sql := 'CREATE USER ' || p_username || ' IDENTIFIED BY "' || p_clave || '"';
    EXECUTE IMMEDIATE v_sql;
    
    -- 2. Darle permisos de conexión y el rol de BD
    v_sql := 'GRANT CONNECT, ' || p_rol_db_nombre || ' TO ' || p_username;
    EXECUTE IMMEDIATE v_sql;
    
    -- 3. Darle permiso de usar el tablespace (espacio de trabajo)
    v_sql := 'ALTER USER ' || p_username || ' QUOTA UNLIMITED ON USERS';
    EXECUTE IMMEDIATE v_sql;

    -- 4. Insertar el registro en nuestra tabla USUARIO
    --    Esto es VITAL para que los triggers de auditoría 
    --    y el sistema de menú sepan quién es.
    INSERT INTO USUARIO (
      nombre,
      username, -- Importante: Guardar en MAYÚSCULAS
      password_hash,
      activo,
      rol_app_id,
      last_login
    ) VALUES (
      p_nombre,
      UPPER(p_username), -- Oracle guarda los usernames en mayúsculas
      NULL, -- El hash lo maneja Oracle, no nosotros
      'S', 
      p_rol_app_id,
      NULL
    );
    
    COMMIT;
    
  EXCEPTION
    WHEN OTHERS THEN
      -- Si algo falla (ej. el usuario ya existe), deshacemos todo
      ROLLBACK;
      RAISE_APPLICATION_ERROR(-20001, 'Error creando usuario híbrido: ' || SQLERRM);
  END PR_CREAR_USUARIO_HIBRIDO;

END PKG_SEGURIDAD;
/