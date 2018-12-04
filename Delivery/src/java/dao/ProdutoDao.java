package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import modelo.Produto;
import util.JpaUtil;

@Entity
public class ProdutoDao implements Serializable {
    EntityManager manager;
   
    public boolean alterar(Produto d){
        manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(d);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }
    
    public Produto buscarPorCodigo(int cod){
        manager = JpaUtil.getEntityManager();
        Produto produto = manager.find(Produto.class, cod);
        manager.close();
        return produto;
    }
    
    public Produto buscarPorNome(String nome){
        Produto temp;
        manager = JpaUtil.getEntityManager();
        String consulta = "SELECT c FROM Produto c WHERE c.nome = :nome"; 
        TypedQuery<Produto> query = manager.createQuery(consulta, Produto.class);
        query.setParameter("nome", nome);
        temp = query.getSingleResult();
        manager.close();
        return temp;
    }
    
        public boolean excluir(Produto produto){
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction(); 
        tx.begin();
        // recupera a referência ao objeto
        Produto temp = manager.find(Produto.class, produto.getCodigo());
        manager.remove(temp);
        tx.commit();
        manager.close();
        return true;
    }
    
    public boolean inserir(Produto produto){
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(produto);
        tx.commit();
        manager.close();
        return true;
    }
    
    public List<Produto> listarTodos(){
        manager = JpaUtil.getEntityManager();
        CriteriaQuery<Produto> query = manager.getCriteriaBuilder().createQuery(Produto.class);
        query.select(query.from(Produto.class));
        List<Produto> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }
        
    
}