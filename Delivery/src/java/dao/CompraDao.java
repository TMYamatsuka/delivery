package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import modelo.CompraProduto;
import util.JpaUtil;

public class CompraDao implements Serializable {
    EntityManager manager;
    
    public boolean alterar(CompraProduto alProduto){
        manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(alProduto);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }
    
    public CompraProduto buscarPorNome(String nome){
        CompraProduto temp;
        manager = JpaUtil.getEntityManager();
        String consulta = "SELECT c FROM CompraProduto c WHERE c.nome = :nome"; 
        TypedQuery<CompraProduto> query = manager.createQuery(consulta, CompraProduto.class);
        query.setParameter("nome", nome);
        temp = query.getSingleResult();
        manager.close();
        return temp;
    }
    
    public boolean excluir(CompraProduto alProduto){
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction(); 
        tx.begin();
        CompraProduto temp = manager.find(CompraProduto.class, alProduto.getCodigo());
        manager.remove(temp);
        tx.commit();
        manager.close();
        return true;
    }
    
    public boolean inserir(CompraProduto alProduto){
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(alProduto);
        tx.commit();
        manager.close();
        return true;
    }
    
    public List<CompraProduto> listarTodos(){
        manager = JpaUtil.getEntityManager();
        CriteriaQuery<CompraProduto> query = manager.getCriteriaBuilder().createQuery(CompraProduto.class);
        query.select(query.from(CompraProduto.class));
        List<CompraProduto> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }
}