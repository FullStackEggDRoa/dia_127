/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author droa
 */

@Entity
public class Noticia implements Serializable{
    // Atributos
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "idnoticia",unique = true)
    private String idNoticia;
    @Column(name="titulo")
    private String titulo;
    @Column(name="cuerpo")
    private String cuerpo;
    @Column(name="alta")
    private Boolean alta;
    
    //Constructores

    public Noticia() {
        this.alta = true;
    }

    public Noticia(String idNoticia, String titulo, String cuerpo) {
        this.idNoticia = idNoticia;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.alta = true;
    }
    
    //Métodos

    public String getId() {
        return idNoticia;
    }

//    public void setId(String idNoticia) {
//        this.idNoticia = idNoticia;
//    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    
}
