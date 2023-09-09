/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.Servicios;

import com.egg.news.Entidades.Usuario;
import com.egg.news.Enumeradores.perfilUsuario;
import com.egg.news.Excepciones.NoticiasExcepsion;
import com.egg.news.Repositorios.repositorioUsuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author droa
 */
@Service
public class serviciosUsuario implements UserDetailsService {
    @Autowired
    private repositorioUsuario rU;
    
    @Transactional
     public void crear(String nombre, String contrasenia, String correo, String contraseniaChk) throws NoticiasExcepsion {

        validar(nombre, contrasenia, correo, contraseniaChk);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setContrasenia(new BCryptPasswordEncoder().encode(contrasenia));
        usuario.setCorreo(correo);
        // Agregar fecha
        usuario.setFechaAlta(new Date());
        usuario.setEstado(true);
        usuario.setPerfilUsusario(perfilUsuario.USUARIO);
     
        rU.save(usuario);
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> todosUsuarios(){
        List<Usuario> usuarios = new ArrayList();
        usuarios = rU.findAll();
        return usuarios;
    } 
    
    private void validar(String nombre, String contrasenia, String correo, String contraseniaChk) throws NoticiasExcepsion{

        if (nombre.isEmpty() || nombre == null) {
            throw new NoticiasExcepsion("El nombre no puede ser nulo o estar vacío");
        }
        if (correo.isEmpty() || correo == null) {
            throw new NoticiasExcepsion("El email no puede ser nulo o estar vacio");
        }
        if (contrasenia.isEmpty() || contrasenia == null || contrasenia.length() <= 5) {
            throw new NoticiasExcepsion("La contraseña no puede estar vacía, y tener más de 5 caracteres");
        }

        if (!contrasenia.equals(contraseniaChk)) {
            throw new NoticiasExcepsion("Las contraseñas ingresadas deben ser iguales");
        }

    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = rU.buscarPorEmail(username);
        
        if (usuario != null) {
            
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ usuario.getPerfilUsusario().toString());
            
            permisos.add(p);
   
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession session = attr.getRequest().getSession(true);
            
            session.setAttribute("usuariosession", usuario);
            
            return new User(usuario.getCorreo(), usuario.getContrasenia(),permisos);
        }else{
            return null;
        }
    }
    
    
}
