package com.proyectoGrupo05.service;

import com.proyectoGrupo05.domain.Roles;
import com.proyectoGrupo05.domain.Usuarios;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author jrg71
 */
public interface UsuariosService {
    
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    
    public List<Usuarios> getUsuarios();
    public Usuarios getUsuario(Long usuario);
    public void save(Usuarios usuarios, boolean crearRolUser);
    public void delete(Usuarios usuarios);
    
    public Usuarios getUsuarioPorUsername(String username);
    public List<Usuarios> metodoNativoUsuarioPorRoles(String rol);
    public Usuarios getUsuarioPorUsernameYPassword(String username, String password);
    public boolean existeUsuarioPorUsername(String username);
    
    public String getUsername();
}
