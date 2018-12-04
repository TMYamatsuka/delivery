package controle;

import dao.ProdutoDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Produto;

@ManagedBean(name = "produtoControle")
@ViewScoped
public class ProdutoControle implements Serializable {
    private Produto produto;
    private Produto aux;
    private ProdutoDao dao;
    private List<Produto> produtos;
    
    public ProdutoControle(){
        produto = new Produto();
        dao = new ProdutoDao();
        produtos = dao.listarTodos();
    }
    
    public void salvarNovoProduto(){
        dao.inserir(produto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto cadastrado", null));
        produtos.add(produto);
        limpar();
        listar();
    }
    
    public void preparaAlterar(Produto produto){
        aux = produto;
    }
    
    public void alterar(){
        dao.alterar(aux);
    }
    
    public void excluir(Produto produto){
        dao.excluir(produto);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto excluido", null));
        produtos.remove(produto);
      //  listar(); //retirar
    }

    public void limpar() {
	produto = new Produto();
    } 
    public void listar() {
        produtos = dao.listarTodos();
    }     

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoDao getDao() {
        return dao;
    }

    public void setDao(ProdutoDao dao) {
        this.dao = dao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getAux() {
        return aux;
    }

    public void setAux(Produto aux) {
        this.aux = aux;
    }
    
}