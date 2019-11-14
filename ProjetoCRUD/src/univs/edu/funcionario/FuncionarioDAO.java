package univs.edu.funcionario;

import univs.edu.funcionario.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.scripts.JO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import univs.edu.util.HibernateUtil;

public class FuncionarioDAO {

    private Session sessao;
    private Transaction transacao;

    public void salvar(Funcionario funcionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        if (funcionario.getIdFuncionario() == 0) {
            sessao.save(funcionario); //Salvar no banco
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado");
        } else {
            editar(funcionario);
            JOptionPane.showMessageDialog(null, "Funcionário editado!");
        }
        transacao.commit(); //Executar a ação no banco
        sessao.close(); //Fechar a conexão com o banco
    }

    public void excluir(Funcionario funcionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        sessao.delete(funcionario); //Exclui do banco
        transacao.commit(); //Executar a ação no banco
        sessao.close(); //Fechar a conexão com o banco
    }

    public void editar(Funcionario funcionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        sessao.update(funcionario); //Atualiza do banco
        transacao.commit(); //Executar a ação no banco
        sessao.close(); //Fechar a conexão com o banco
    }

    public Funcionario pesquisar(int idFuncionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        Funcionario funcionario = (Funcionario) sessao.createCriteria(Funcionario.class).add(Restrictions.eq("idFuncionario", idFuncionario)).uniqueResult();
        sessao.close();
        return funcionario;
    }

    public List<Funcionario> listarFuncionarios() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction(); //Preparar a sessão para inserir no banco
        List<Funcionario> funcionarios = sessao.createCriteria(Funcionario.class).list();
        sessao.close();
        return funcionarios;
    }

}
