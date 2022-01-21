package jdbc_basic.modelo;

import java.util.ArrayList;
import java.util.List;

//Classe responsável em criar o modelo base para as categorias, informando sua composição e fornecendo acesso a outras classes através do encapsulamento.

public class Categoria {

    private Integer id;
    private String nome;
    private List<Produto> produtos = new ArrayList<Produto>();

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
