/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author droa
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Periodista extends Usuario implements Serializable{
    //Atributos
    //...
    @Column(name="mis_noticias")
    @OneToMany
    private List<Noticia> misNoticias;
    @Column(name="sueldo_mensual")
    private Integer sueldoMensual;
}
