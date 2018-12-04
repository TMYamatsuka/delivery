package controle;

import dao.CompraDao;
import dao.ClienteDao;
import dao.ProdutoDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.CompraProduto;
import modelo.Cliente;
import modelo.Produto;

@ManagedBean(name = "aluguelFilmeControle")
@ViewScoped
public class CompraControle implements Serializable {
    private CompraProduto alProduto;
    private CompraProduto aux;
    private CompraDao alProdutoDao;
    private List<CompraProduto> alProdutos;
    private ClienteDao clienteDao;
    private Cliente clienteSelecionado;
    private List<Cliente> clientes;
    private ProdutoDao produtoDao;
    private Produto produtoSelecionado;
    private List<Produto> produtos;
    
    public CompraControle(){
        alProduto = new CompraProduto();
        alProdutoDao = new CompraDao();
        alProdutos = alProdutoDao.listarTodos();
        clienteSelecionado = new Cliente();
        clienteDao = new ClienteDao();
        clientes = clienteDao.listarTodos();
        produtoSelecionado = new Produto();
        produtoDao = new ProdutoDao();
        produtos = produtoDao.listarTodos();
    }
    
    public void preparaAlterar(CompraProduto alProduto){
        setAux(alProduto);
    }
    
    public void comprar() {
        alProduto.setCliente(getClienteSelecionado());
	alProduto.setProduto(getProdutoSelecionado());
	alProdutoDao.inserir(alProduto);
        produtoSelecionado.setComprado(Boolean.TRUE);
        produtoDao.alterar(produtoSelecionado);
	FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A compra foi realizada.", null));
        alProdutos.add(alProduto);
        limpar();
        listar();
    }
    
    public void alterar(){
        getAlProdutoDao().alterar(getAux());
    }
    
    public void excluir(CompraProduto alProduto){
        produtoSelecionado=alProduto.getProduto(); //não significa que é o mesmo objeto que o anterior.
        produtoSelecionado.setComprado(Boolean.FALSE);
        produtoDao.alterar(produtoSelecionado);
	FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A compra foi desfeita.", null));
        getAlProdutoDao().excluir(alProduto);
        getAlProdutos().remove(alProduto);
        listar();
    }
    
    public void limpar() {
	alProduto = new CompraProduto();
    }
    public void listar() {
        alProdutos = alProdutoDao.listarTodos();
    }

    public CompraProduto getAlProduto() {
        return alProduto;
    }

    public void setAlProduto(CompraProduto alProduto) {
        this.alProduto = alProduto;
    }

    public CompraProduto getAux() {
        return aux;
    }

    public void setAux(CompraProduto aux) {
        this.aux = aux;
    }

    public CompraDao getAlProdutoDao() {
        return alProdutoDao;
    }

    public void setAlProdutoDao(CompraDao alProdutoDao) {
        this.alProdutoDao = alProdutoDao;
    }

    public List<CompraProduto> getAlProdutos() {
        return alProdutos;
    }

    public void setAlProduto(List<CompraProduto> alProdutos) {
        this.alProdutos = alProdutos;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setPodutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}

