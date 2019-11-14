package univs.edu.usuario;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.scripts.JO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import univs.edu.util.HibernateUtil;

public class UsuarioDAO {

    private Session sessao;
    private Transaction transacao;

    public void salvar(Usuario usuario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        if (usuario.getIdUsuario() == 0) {
            sessao.save(usuario); //Salvar no banco
            JOptionPane.showMessageDialog(null, "Usuário cadastrado!");
        } else {
            editar(usuario);
            JOptionPane.showMessageDialog(null, "Usuário editado!");
        }
        transacao.commit(); //Executar a ação no banco
        sessao.close(); //Fechar a conexão com o banco
    }

    public void excluir(Usuario usuario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        sessao.delete(usuario); //Exclui do banco
        transacao.commit(); //Executar a ação no banco
        sessao.close(); //Fechar a conexão com o banco
    }

    public void editar(Usuario usuario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        sessao.update(usuario); //Atualiza do banco
        transacao.commit(); //Executar a ação no banco
        sessao.close(); //Fechar a conexão com o banco
    }

    public Usuario pesquisar(int idUsuario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        Usuario usuario = (Usuario) sessao.createCriteria(Usuario.class).add(Restrictions.eq("idUsuario", idUsuario)).uniqueResult();
        sessao.close();
        return usuario;
    }

    public Usuario autenticarUsuario(String login, String senha) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        Usuario usuario = (Usuario) sessao.createCriteria(Usuario.class).add(Restrictions.eq("loginUsuario", login)).add(Restrictions.eq("senhaUsuario", senha)).uniqueResult();
        sessao.close();

        return usuario != null ? usuario : null;
    }

    public List<Usuario> listarUsuarios() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        List<Usuario> usuarios = sessao.createCriteria(Usuario.class).list();
        sessao.close();
        return usuarios;
    }

}
