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
 */

public class LexicoMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    	//Cria lista de tokens
        ArrayList<Token> lt = new ArrayList<Token>();

        //Cria o analisador léxico
        Lexico lexico = new Lexico();

        //Realiza a análise léxica
        //lexico.analisa(args[0]);
        lt = lexico.analisa("teste1.lpd");

        //Imprime número de tokens
        System.out.println("Número de tokens: " + lt.size());

        //Percorre lista de tokens imprimindo-os
        lt.forEach(token -> System.out.println(token.toString()));
    }

}
