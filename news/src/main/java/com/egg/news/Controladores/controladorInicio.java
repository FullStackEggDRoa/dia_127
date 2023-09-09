/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.Controladores;

import com.egg.news.Entidades.Noticia;
import com.egg.news.Entidades.Usuario;
import com.egg.news.Excepciones.NoticiasExcepsion;
import com.egg.news.Servicios.serviciosNoticia;
import com.egg.news.Servicios.serviciosUsuario;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author droa
 */
@Controller
@RequestMapping("/")
public class controladorInicio {
    
    @Autowired
    private serviciosNoticia sN;
    @Autowired
    private serviciosUsuario sU;
    
    @GetMapping("/")
    public String index(ModelMap modelo){

        modelo.put("modulo","inicio");
        
        return "index.html";
    }
    
    @GetMapping("/ingreso")
    public String login(@RequestParam(required = false) String error, ModelMap modelo ) {

        if (error != null) {
            modelo.put("notificacion", "Usuario o Contraseña invalidos");
            modelo.put("modulo","signin");
            modelo.put("modo","ingreso");
        }
        modelo.put("modulo","signin");
        modelo.put("modo","ingreso");
        return "index.html";
    }
    
//    @GetMapping("/salir")
//    public String logout(@RequestParam(required = false) String error, ModelMap modelo ) {
//
//        modelo.put("notificacion", "Sesión Cerrada Existosamente");
//        modelo.put("modulo","signin");
//        modelo.put("modo","ingreso");
//            
//        return "redirect:/logout";
//    }
    
    @GetMapping("/registro")
    public String registro(@RequestParam(required = false) String error, ModelMap modelo ) {

        if (error != null) {
            modelo.put("notificacion", "Usuario o Contraseña invalidos!");
            modelo.put("modulo","signup");
            modelo.put("modo","registro");
        }
        modelo.put("modulo","signup");
        modelo.put("modo","registro");
        return "index.html";
    }
    @PreAuthorize("hasAnyRole('ROLE_USUARIO', 'ROLE_ADMINISTRADOR')")
    @GetMapping("/sesion")
    public String sesion(HttpSession session,ModelMap modelo) {
        
        Usuario sesionUsuario = (Usuario) session.getAttribute("usuariosession");
        
        if (sesionUsuario.getPerfilUsusario().toString().equals("ADMINISTRADOR")) {
            modelo.put("modulo","sesion");
            modelo.put("modo","admin");
            List<Noticia> noticias = sN.todasNoticias();
            modelo.addAttribute("noticias", noticias);
            
        }
            modelo.put("modulo","sesion");
            List<Noticia> noticias = sN.todasNoticias();
            modelo.addAttribute("noticias", noticias);
            return "index.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre, @RequestParam String correo, @RequestParam String contrasenia,
            @RequestParam String contraseniaChk, ModelMap modelo) {

        try {
            sU.crear(nombre,contrasenia,correo,contraseniaChk);

            modelo.put("notificacion", "Usuario registrado correctamente!");
            modelo.put("modulo","signin");
            modelo.put("modo", "ingreso");
            modelo.put("correo",correo);
            modelo.put("contrasenia", contrasenia);
            
            return "index.html";
            
        } catch (NoticiasExcepsion ex) {

            modelo.put("notificacion", ex.getMessage());
            modelo.put("modulo", "signup");
            modelo.put("modo", "registro");

            return "index.html";
        }

    }

}
