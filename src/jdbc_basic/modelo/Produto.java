package jdbc_basic.modelo;

//Classe responsável em criar o modelo base para os produtos, informando sua composição e fornecendo acesso a outras classes através do encapsulamento.

public class Produto {

    private Integer id;
    private String nome;
    private String descricao;
    private Integer categoriaId;

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Produto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("O produto é: %d, %s, %s", this.id, this.nome, this.descricao);
    }

}
