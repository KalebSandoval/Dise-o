/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import Entitys.Usuario;
import interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aaron
 */
public class UsuarioDAO implements IUsuarioDAO {

    private static UsuarioDAO instance;

    private UsuarioDAO() {
    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

    @Override
    public boolean restarCreditos(Integer cantidad, Long idUsuario) {
        if (idUsuario == null) {
            return false;
        }
        for (Usuario u : obtenerUsuarios()) {
            if (u.getIdUsuario().equals(idUsuario)) {
                if (u.getCreditos() >= cantidad) {
                    u.setCreditos(u.getCreditos() - cantidad);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        // craecion de lista manual para que el sistema pueda cargar informacion
        List<Usuario> usuarios = new ArrayList<>();

        // usuario 1
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1L);
        usuario1.setApellidoMaterno("Gaspar");
        usuario1.setApellidoPaterno("Leyva");
        usuario1.setNombre("Andre");
        usuario1.setCorreo("andre1@gmail.com");
        usuario1.setContrasenia("12");
        usuario1.setCreditos(100000);

        // usuario 2
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2L);
        usuario2.setApellidoMaterno("Vega");
        usuario2.setApellidoPaterno("Luna");
        usuario2.setNombre("Sisi");
        usuario2.setCorreo("sisi1@gmail.com");
        usuario2.setContrasenia("123");
        usuario2.setCreditos(0);

        // usuario 3
        Usuario usuario3 = new Usuario();
        usuario3.setIdUsuario(1L);
        usuario3.setApellidoMaterno("Marquez");
        usuario3.setApellidoPaterno("esparza");
        usuario3.setNombre("miguel");
        usuario3.setCorreo("miguel1@gmail.com");
        usuario3.setContrasenia("1234");
        usuario3.setCreditos(0);

        // agregar
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        return usuarios;
    }

    @Override
    public Usuario obtenerUsuario(Usuario usuario) {
        List<Usuario> usuarios = obtenerUsuarios();
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(usuario.getCorreo()) && u.getContrasenia().equals(usuario.getContrasenia())) {
                return u;
            }
        }
        return null;
    }

}
