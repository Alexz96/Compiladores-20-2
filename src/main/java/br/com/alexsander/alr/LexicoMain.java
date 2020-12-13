/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alexsander.alr;
import java.util.ArrayList;

/**
 *
 * @author alexs
 * @author taywornath
 * @author TeoFalleiro
 */

public class LexicoMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    	//Cria lista de tokens
        ArrayList<Token> lt = new ArrayList<Token>();

        //Cria o analisador léxico
        Lexico lexico = new Lexico("teste1.lpd");

        //Realiza a análise léxica do arquivo com o respectivo nome
        lt = lexico.analisa("teste1.lpd");

        //Imprime número de tokens
        System.out.println("Número de tokens: " + lt.size());

        //Percorre lista de tokens imprimindo-os
        lt.forEach(token -> System.out.println(token.toString()));
        
        // Instanciacao do Parser Preditivo Recursivo que herda de Parser
        PPR parser = new PPR("teste1.lpd");
        parser.parse();
        
        //Instanciacao do ParseCodigo, onde inicia-se a geracao do codigo intermediario
        ParseCodigo parseCodigo = new ParseCodigo(lexico);
        // analise do codigo efetuada e salvamento do codigo intermediario em uma String
        String codigo = parseCodigo.parse();
        System.out.println("Codigo: \n" + codigo);
    }

}
