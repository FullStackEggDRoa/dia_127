/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.Controladores;

import com.egg.news.Entidades.Noticia;
import com.egg.news.Excepciones.NoticiasExcepsion;
import com.egg.news.Servicios.serviciosNoticia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author droa
 */
@Controller
@RequestMapping("/noticia")
public class controladorNoticia {
    
    @Autowired
    private serviciosNoticia sN;
    
    @GetMapping("/creacion")
    public String creacion(ModelMap modelo){
        modelo.addAttribute("noticia",new Noticia("","",""));
        modelo.put("modulo","noticiaform");
        modelo.put("modo","crear");
        return "index.html";
    }
    @GetMapping("/modificacion")
    public String modificacionLista(ModelMap modelo){
        List<Noticia> noticias= sN.todasNoticias();
        modelo.addAttribute("noticias", noticias);
        modelo.put("modulo", "modificacion");
        modelo.put("modo", "crear");
        return "index.html";
//        return "index.html";
    }
    @GetMapping("/modificacion/{id}")
    public String modificacionForm(@PathVariable String id, ModelMap modelo){
        modelo.addAttribute("noticia",sN.obtenerNoticia(id));
        // Enviar path para action
        modelo.put("modulo","noticiaform");
        modelo.put("modo","modificar");
        return "index.html";
    }
    @PostMapping("/crear")
    public String crear(@RequestParam String titulo,@RequestParam String cuerpo, ModelMap modelo)
    {
        try {
            sN.crearNoticia(titulo, cuerpo);
            modelo.put("hecho", "Noticia Registrada");
            modelo.put("modulo","sesion");
        } catch (NoticiasExcepsion ex) {
            
            modelo.put("notificacion", "Noticia no Registrada");
            modelo.put("modulo","noticiaform");
            modelo.put("modo","crear");
//            return "redirect:/noticia/creacion";
            return "index.html";
        }
        return "index.html";
    }
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String titulo, String cuerpo, Boolean alta, ModelMap modelo)
    {
        List<Noticia> noticias= sN.todasNoticias();
        
        modelo.put("hecho", "Noticia Modificada");
        modelo.put("modulo","sesion");
        modelo.put("estado", true);
        modelo.addAttribute("noticias", noticias);
        
        try {
            sN.modificarNoticia(id, titulo, cuerpo,alta);
            modelo.put("hecho", "Noticia Modificada");
        } catch (NoticiasExcepsion ex) {
            modelo.put("notificacion", "Noticia no fue modificada");
            modelo.put("modulo","modificacion");
            modelo.put("modo","modificar");
        }
        
//        return "redirect:/";
        return "index.html";
    }
}
