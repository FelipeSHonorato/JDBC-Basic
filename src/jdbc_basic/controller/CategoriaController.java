package jdbc_basic.controller;

import jdbc_basic.modelo.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

    public List<Categoria> listar() {
        List<Categoria> categorias =
                new ArrayList<Categoria>();
        categorias.add(new Categoria(1, "Categoria de teste"));
        return categorias;
    }
}
