package com.proyectoGrupo05.service.impl;

import com.proyectoGrupo05.dao.RolesDao;
import com.proyectoGrupo05.service.UsuariosService;
import com.proyectoGrupo05.dao.UsuariosDao;
import com.proyectoGrupo05.domain.Usuarios;
import com.proyectoGrupo05.domain.Roles;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author jrg71
 */

@Service("userDetailsService")
public class UsuariosServiceImpl implements UsuariosService, UserDetailsService{

    @Autowired
    private UsuariosDao usuariosDao;
    @Autowired
    private RolesDao rolesDao;
    
    private Usuarios usuario;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuariosDao.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        
        var roles = new ArrayList<GrantedAuthority>();

        for (Roles rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles); 
    }

    @Override
    public List<Usuarios> getUsuarios() {
        return usuariosDao.findAll();
    }
    @Override
    public Usuarios getUsuario(Long usuario){
        return usuariosDao.findById(usuario).orElse(null);
    }
    @Override
    @Transactional
    public void save(Usuarios usuarios, boolean crearRolUser) {
        if (usuarios.getPassword()==null){
            usuarios.setPassword(getUsuario(usuarios.getIdUsuario()).getPassword());
        }
        else {
            var codigo = new BCryptPasswordEncoder();
            usuarios.setPassword(codigo.encode(usuarios.getPassword()));
        }
        usuariosDao.save(usuarios);
        
        if (crearRolUser && !(usuarios.getUsername().endsWith("@fide.cr"))) {
            Roles rol = new Roles();
            rol.setNombre("ROLE_CUS");
            rol.setIdUsuario(usuarios.getIdUsuario());
            rolesDao.save(rol);
        }
        else if (crearRolUser && usuarios.getUsername().endsWith("@fide.cr")) {
            Roles rol = new Roles();
            rol.setNombre("ROLE_ING");
            rol.setIdUsuario(usuarios.getIdUsuario());
            rolesDao.save(rol);
        }
    }
    @Override
    public void delete(Usuarios usuarios) {
        usuariosDao.delete(usuarios);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> metodoNativoUsuarioPorRoles(String rol) {
        return usuariosDao.metodoNativoUsuarioPorRoles(rol);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuarios getUsuarioPorUsername(String username) {
        return usuariosDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios getUsuarioPorUsernameYPassword(String username, String password) {
        return usuariosDao.findByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsername(String username) {
        return usuariosDao.existsByUsername(username);
    }
    
    @Override
    public String getUsername(){
        return usuario.getUsername();
    }
    
}
