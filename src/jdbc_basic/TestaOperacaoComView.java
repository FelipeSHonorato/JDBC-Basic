package jdbc_basic;

import jdbc_basic.view.ProdutoCategoriaFrame;

import javax.swing.*;

//Classe responsável pela execução do front em Swing.

public class TestaOperacaoComView {

    public static void main(String[] args) {
        try {
            ProdutoCategoriaFrame produtoCategoriaFrame = new ProdutoCategoriaFrame();
            produtoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}