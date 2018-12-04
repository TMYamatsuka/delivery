package controle;

import dao.AdministradorDao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Administrador;

@ManagedBean(name = "admControle")
@ViewScoped
public class AdministradorControle {
    private Administrador adm;
    private Administrador aux;
    private AdministradorDao admDao;
    private List<Administrador> adms;
    
    public AdministradorControle(){
        adm = new Administrador();
        admDao = new AdministradorDao();
        adms = admDao.listarTodos();
    }
    
    public void preparaAlterar(Administrador adm){
        setAux(adm);
    }
    
    public void salvarNovoAdm(){
        getAdmDao().inserir(getAdm());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador cadastrado", null));
        getAdms().add(getAdm());
        setAdm(new Administrador());
    }
    
    public void alterar(){
        getAdmDao().alterar(getAux());
    }
    
    public void excluir(Administrador adm){
        getAdmDao().excluir(adm);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador excluído", null));
        getAdms().remove(adm);
    }

    public Administrador getAdm() {
        return adm;
    }

    public void setAdm(Administrador adm) {
        this.adm = adm;
    }

    public Administrador getAux() {
        return aux;
    }

    public void setAux(Administrador aux) {
        this.aux = aux;
    }

    public AdministradorDao getAdmDao() {
        return admDao;
    }

    public void setAdmDao(AdministradorDao admDao) {
        this.admDao = admDao;
    }

    public List<Administrador> getAdms() {
        return adms;
    }
    
    public void setAdms(List<Administrador> adms) {
        this.adms = adms;
    }
}