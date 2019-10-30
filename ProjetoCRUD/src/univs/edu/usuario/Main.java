
package univs.edu.usuario;

import javax.swing.JOptionPane;


public class Main {
    
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
              
        UsuarioDAO dao = new UsuarioDAO();
                
        usuario = dao.pesquisar(1);
        
        usuario.setLoginUsuario("admin2");
        usuario.setSenhaUsuario("admin2");
        
        dao.editar(usuario);
        
                             
    }
}
