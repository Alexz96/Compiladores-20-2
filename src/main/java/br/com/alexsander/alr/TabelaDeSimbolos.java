/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alexsander.alr;

import java.util.HashMap;

/**
 *
 * @author alexs
 */
public class TabelaDeSimbolos {
    
    HashMap<String, Token> ts = new HashMap<>();
    
    // Metodo que realiza o salvamento do Token no HashMap
    public void addTokenNaTS(String escopo, TipoToken tipo, String textoLexema, int linhaP, int colunaP) {
        Token token = new Token(tipo, textoLexema, linhaP, colunaP); 
        ts.put(textoLexema, token);
    }
    
    public Token getToken(String chave) {
        return ts.get(chave);
    }
    
    // Metodo para atualizacao do atributo de um token, note que se for necessario atualizar 
    // atributos inteiro (linha e coluna por exemplo, deve-se implementar um
    // reconhecimento
    public void setAttributeOfToken(String chave, String atributo, String valor) {
        
    }
    
    public String getAttributeOfToken(String chave, String atributo) {
        // neste metodo deve-se pegar a chave e buscar o token da TS, para entao
        // "descontruir" os atributos do token em variáveis para retornar unicamente uma string
        return "";
    }
    
}
