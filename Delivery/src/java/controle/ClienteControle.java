package controle;

import dao.ClienteDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Cliente;

@ManagedBean(name = "clienteControle")
@ViewScoped
public class ClienteControle implements Serializable {
    private Cliente cliente;
    private Cliente aux;
    private ClienteDao dao;
    private List<Cliente> clientes;
    
    public ClienteControle(){
        cliente = new Cliente();
        aux = new Cliente();
        dao = new ClienteDao();
        clientes = dao.listarTodos();
    }
    
    public void preparaAlterar(Cliente cliente){
        aux = cliente;
    }
    
    public void salvarNovoCliente(){
        dao.inserir(cliente);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente cadastrado", null));
        clientes.add(cliente);
        cliente = new Cliente();
    }
    
    public void alterar(){
        dao.alterar(aux);
    }
    
    public void excluir(Cliente c){
        dao.excluir(c);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente excluído", null));        
        clientes.remove(c);
    }
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteDao getDao() {
        return dao;
    }

    public void setDao(ClienteDao dao) {
        this.dao = dao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getAux() {
        return aux;
    }

    public void setAux(Cliente aux) {
        this.aux = aux;
    }
}