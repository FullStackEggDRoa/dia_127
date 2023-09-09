/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.Repositorios;

import com.egg.news.Entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author droa
 */
@Repository
public interface repositorioNoticia extends JpaRepository<Noticia, String>{
    
//    @Query("SELECT n FROM Noticia n WHERE n.titulo = :palabraClave AND n.alta = true")
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public List<Noticia> buscarNoticia(@Param("titulo") String palabraClave);
    
//    @Query("SELECT n FROM Noticia n WHERE n.idNoticia = :idnoticia")
//    public Noticia buscarNoticiaPorId(@Param("idNoticia") String idnoticia);
}
