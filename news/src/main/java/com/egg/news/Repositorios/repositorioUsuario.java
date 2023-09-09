/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.Repositorios;

import com.egg.news.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author droa
 */
@Repository
public interface repositorioUsuario extends JpaRepository<Usuario, String> {
    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
    public Usuario buscarPorEmail(@Param("correo") String correoUsuario);
}
