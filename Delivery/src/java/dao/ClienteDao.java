package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import modelo.Cliente;
import util.JpaUtil;

public class ClienteDao implements Serializable {
    EntityManager manager;
    
    public boolean alterar(Cliente cliente){
        manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(cliente);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }
    
    public Cliente buscarPorCodigo(int cod){
        manager = JpaUtil.getEntityManager();
        Cliente cliente = manager.find(Cliente.class, cod);
        manager.close();
        return cliente;
    }
    
    public Cliente buscarPorNome(String nome){
        Cliente temp;
        manager = JpaUtil.getEntityManager();
        String consulta = "SELECT c FROM Cliente c WHERE c.nome = :nome"; 
        TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);
        query.setParameter("nome", nome);
        temp = query.getSingleResult();
        manager.close();
        return temp;
    }
    
    public boolean excluir(Cliente cliente){
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction(); 
        tx.begin();
        Cliente temp = manager.find(Cliente.class, cliente.getCodigo());
        manager.remove(temp);
        tx.commit();
        manager.close();
        return true;
    }
    
    public boolean inserir(Cliente cliente){
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(cliente);
        tx.commit();
        manager.close();
        return true;
    }
    
    public List<Cliente> listarTodos(){
        manager = JpaUtil.getEntityManager();
        CriteriaQuery<Cliente> query = manager.getCriteriaBuilder().createQuery(Cliente.class);
        query.select(query.from(Cliente.class));
        List<Cliente> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }
}