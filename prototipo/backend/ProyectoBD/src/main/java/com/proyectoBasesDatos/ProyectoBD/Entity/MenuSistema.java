package com.proyectoBasesDatos.ProyectoBD.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "MENU_SISTEMA")
public class MenuSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "nombre_opcion", nullable = false)
    private String nombreOpcion;

    @Column(name = "url_aplicacion")
    private String urlAplicacion;

    @Column(name = "icono")
    private String icono;

    @Column(name = "orden")
    private Integer orden;

    // --- Relación (para sub-menús) ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_padre_id") // La FK en la misma tabla
    private MenuSistema menuPadre;

    // --- Relación Inversa ---
    @OneToMany(mappedBy = "menuPadre")
    private Set<MenuSistema> subMenus;
}