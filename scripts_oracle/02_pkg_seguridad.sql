CREATE OR REPLACE PACKAGE PKG_SEGURIDAD AS

  -- Requisito #10 (Función): Encripta un texto
  FUNCTION FN_ENCRIPTAR_CLAVE(
    p_clave IN VARCHAR2
  ) RETURN VARCHAR2;
  
  -- Requisito #11 (Procedimiento): Crea un usuario de forma segura
  PROCEDURE PR_CREAR_USUARIO(
    p_nombre       IN USUARIO.nombre%TYPE,
    p_username     IN USUARIO.username%TYPE,
    p_clave_sin_hash IN VARCHAR2,
    p_rol_app_id   IN USUARIO.rol_app_id%TYPE
  );

  -- Requisito #10 (Función): Valida el login
  FUNCTION FN_VALIDAR_LOGIN(
    p_username IN VARCHAR2,
    p_clave    IN VARCHAR2
  ) RETURN NUMBER; -- Retorna el ID del usuario si es exitoso, 0 si falla

END PKG_SEGURIDAD;
/


CREATE OR REPLACE PACKAGE BODY PKG_SEGURIDAD AS

  -- Constante para la sal (¡Cambia esto por tu propia frase secreta!)
  g_salt_key VARCHAR2(200) := 'ProyectoFerreteria2025!';

  -- =========================================================
  -- Requisito #6 (Encriptación) y #10 (Función)
  -- =========================================================
  FUNCTION FN_ENCRIPTAR_CLAVE(
    p_clave IN VARCHAR2
  ) RETURN VARCHAR2 AS
    v_hash_bytes RAW(256);
  BEGIN
    -- Usamos el algoritmo SHA-256 (256 bits)
    -- Se combina la clave con la "sal" para mayor seguridad
    v_hash_bytes := DBMS_CRYPTO.HASH(
      src => UTL_I18N.STRING_TO_RAW(p_clave || g_salt_key, 'AL32UTF8'),
      typ => DBMS_CRYPTO.HASH_SH256
    );
    RETURN RAWTOHEX(v_hash_bytes); -- Retorna el hash como un texto hexadecimal
  END FN_ENCRIPTAR_CLAVE;

  -- =========================================================
  -- Requisito #11 (Procedimiento)
  -- =========================================================
  PROCEDURE PR_CREAR_USUARIO(
    p_nombre       IN USUARIO.nombre%TYPE,
    p_username     IN USUARIO.username%TYPE,
    p_clave_sin_hash IN VARCHAR2,
    p_rol_app_id   IN USUARIO.rol_app_id%TYPE
  ) AS
    v_password_hash USUARIO.password_hash%TYPE;
  BEGIN
    -- 1. Encriptar la clave
    v_password_hash := FN_ENCRIPTAR_CLAVE(p_clave_sin_hash);
    
    -- 2. Insertar el usuario en la tabla
    INSERT INTO USUARIO (
      nombre,
      username,
      password_hash,
      activo,
      rol_app_id,
      last_login
    ) VALUES (
      p_nombre,
      p_username,
      v_password_hash,
      'S', -- Activo por defecto
      p_rol_app_id,
      NULL
    );
    
    COMMIT;
    
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      RAISE_APPLICATION_ERROR(-20001, 'El nombre de usuario ' || p_username || ' ya existe.');
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20002, 'Error al crear usuario: ' || SQLERRM);
  END PR_CREAR_USUARIO;

  -- =========================================================
  -- Requisito #10 (Función)
  -- =========================================================
  FUNCTION FN_VALIDAR_LOGIN(
    p_username IN VARCHAR2,
    p_clave    IN VARCHAR2
  ) RETURN NUMBER AS
    v_usuario_id USUARIO.id_usuario%TYPE := 0; -- 0 significa login fallido
    v_hash_guardado USUARIO.password_hash%TYPE;
    v_hash_intento  USUARIO.password_hash%TYPE;
    v_activo        USUARIO.activo%TYPE;
  BEGIN
    -- 1. Obtener el hash guardado y el estado del usuario
    BEGIN
      SELECT id_usuario, password_hash, activo
      INTO v_usuario_id, v_hash_guardado, v_activo
      FROM USUARIO
      WHERE username = p_username;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETURN 0; -- Usuario no existe
    END;

    -- 2. Verificar si el usuario está activo
    IF v_activo = 'N' THEN
      RETURN 0; -- Usuario inactivo
    END IF;

    -- 3. Calcular el hash de la clave que se intenta
    v_hash_intento := FN_ENCRIPTAR_CLAVE(p_clave);
    
    -- 4. Comparar los hashes
    IF v_hash_guardado = v_hash_intento THEN
      -- ¡Éxito!
      -- Actualizamos la fecha de último login
      UPDATE USUARIO
      SET last_login = SYSTIMESTAMP
      WHERE id_usuario = v_usuario_id;
      COMMIT;
      
      RETURN v_usuario_id; -- Retorna el ID del usuario
    ELSE
      -- Fracaso
      RETURN 0; -- Clave incorrecta
    END IF;
    
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0; -- Error general
  END FN_VALIDAR_LOGIN;

END PKG_SEGURIDAD;
/