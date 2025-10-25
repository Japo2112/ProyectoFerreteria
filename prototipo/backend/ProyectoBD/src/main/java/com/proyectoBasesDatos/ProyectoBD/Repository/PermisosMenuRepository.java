package com.proyectoBasesDatos.ProyectoBD.Repository;

import com.proyectoBasesDatos.ProyectoBD.Entity.PermisosMenu;
import com.proyectoBasesDatos.ProyectoBD.Entity.PermisosMenuId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository

public interface PermisosMenuRepository extends JpaRepository<PermisosMenu, PermisosMenuId> {
List<PermisosMenu> findByRol_RolAppId(Long rolAppId);
}
