
package dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.Administrador;
import util.JpaUtil;

public class AdministradorDao implements Serializable {
    
    public boolean inserir(Administrador adm){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(adm);
        tx.commit();
        manager.close();
        return true;
    }
}
 