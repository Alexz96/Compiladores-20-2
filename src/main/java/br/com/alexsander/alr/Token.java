/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alexsander.alr;

/**
 *
 * @author alexs
 */
public class Token {
    
    TipoToken tipo;
    String lexema;
    int linha;
    int coluna;
    String nome;
    String codigo;

    public Token(TipoToken tipo, String lexema, int linha, int coluna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.linha = linha;
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return "Token{" + "tipo=" + tipo + ", lexema=" + lexema + ", linha=" + linha + ", coluna=" + coluna + '}';
    }

    
}
