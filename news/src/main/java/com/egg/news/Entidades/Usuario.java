/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.Entidades;

import com.egg.news.Enumeradores.perfilUsuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author droa
 */

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Para Controlar creación de tablas independientes..
        
public class Usuario implements Serializable {
    //Atributos    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "idusuario",unique = true)
    protected String id;
    @Column(name="nombre")
    protected String nombre;
    @Column(name="contrasenia")
    protected String contrasenia;
    @Column(name="correo")
    protected String correo;
    @Column(name="fechaAlta")
    protected Date fechaAlta;
    @Column(name="Estado")
    protected Boolean estado;
    @Enumerated(EnumType.STRING)
    @Column(name="perfilUsuario")
    protected perfilUsuario perfilUsusario;   
    
}
