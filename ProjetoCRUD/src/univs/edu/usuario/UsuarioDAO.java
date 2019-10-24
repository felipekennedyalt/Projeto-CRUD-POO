
package univs.edu.usuario;

import static com.sun.jmx.mbeanserver.DefaultMXBeanMappingFactory.propertyName;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import univs.edu.util.HibernateUtil;


public class UsuarioDAO {
    
    private Session sessao;
    private Transaction transacao;
    
    public void salvar(Usuario usuario){
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();//prepara a seção para inserir no banco
        sessao.save(usuario);//salvar no banco
        transacao.commit();//executar ação no banco
        sessao.close();//fechar conexão com o banco
    }
    
    public void excluir(Usuario usuario){
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();//prepara a seção para inserir no banco
        sessao.delete(usuario);//excluir no banco
        transacao.commit();//executar ação no banco
        sessao.close();
    }
    
    public void editar(Usuario usuario){
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();//prepara a seção para inserir no banco
        sessao.update(usuario);//atualizar no banco
        transacao.commit();//executar ação no banco
        sessao.close();//fechar conexão com o banco
    }
    
    public Usuario pesquisar(int id){
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();//prepara a seção para inserir no banco
        Usuario usuario = (Usuario) sessao.createCriteria(Usuario.class).add(Restrictions.eq("idUsuario", id)).uniqueResult();
        sessao.close();//fechar sessão
        return usuario;
    }
    
    
    
    
    
}
