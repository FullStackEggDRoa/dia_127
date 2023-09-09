/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.Controladores;


import com.egg.news.Entidades.Usuario;

import com.egg.news.Servicios.serviciosUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author droa
 */
@Controller
@RequestMapping("/usuario")
public class controladorUsuario {
    @Autowired
    private serviciosUsuario sU;
    
    @GetMapping("/modificacion")
    public String modificacion(ModelMap modelo){
        List<Usuario> usuarios= sU.todosUsuarios();
        modelo.addAttribute("usuarios",usuarios);
        modelo.put("modulo","modificacion");
        modelo.put("modo","usuario");
        return "index.html";
    }
    
    
}
