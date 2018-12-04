package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import modelo.Administrador;
import util.JpaUtil;

public class AdministradorDao implements Serializable {
    EntityManager manager;
    
    public Administrador autenticar(Administrador adm){
        Administrador temp = null; // administrador retornado na consulta ao banco
        manager = JpaUtil.getEntityManager();
        TypedQuery<Administrador> query = manager.createQuery("SELECT a FROM Administrador a WHERE a.login = :login AND a.senha = :senha",
                Administrador.class); 
        query.setParameter("login", adm.getLogin());
        query.setParameter("senha", adm.getSenha());
        try{
            temp = query.getSingleResult(); 
        }
        catch(Exception e){ }    
        finally{
            manager.close();
        }        
        return temp;
    }
     
     public boolean alterar(Administrador adm){
        manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(adm);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }
    
    public Administrador buscarPorCodigo(int cod){
        manager = JpaUtil.getEntityManager();
        Administrador adm = manager.find(Administrador.class, cod);
        manager.close();
        return adm;
    }
    
    public Administrador buscarPorNome(String nome){
        Administrador temp;
        manager = JpaUtil.getEntityManager();
        String consulta = "SELECT c FROM Administrador c WHERE c.nome = :nome"; 
        TypedQuery<Administrador> query = manager.createQuery(consulta, Administrador.class);
        query.setParameter("nome", nome);
        temp = query.getSingleResult();
        manager.close();
        return temp;
    }
    
    public boolean excluir(Administrador adm){
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction(); 
        tx.begin();
        Administrador temp = manager.find(Administrador.class, adm.getCodigo());
        manager.remove(temp);
        tx.commit();
        manager.close();
        return true;
    }
    
    public boolean inserir(Administrador adm){
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(adm);
        tx.commit();
        manager.close();
        return true;
    }
    
    public List<Administrador> listarTodos(){
        manager = JpaUtil.getEntityManager();
        CriteriaQuery<Administrador> query = manager.getCriteriaBuilder().createQuery(Administrador.class);
        query.select(query.from(Administrador.class));
        List<Administrador> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }    
}
