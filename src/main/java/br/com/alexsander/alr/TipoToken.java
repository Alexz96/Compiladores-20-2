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
public enum TipoToken {
    SPROGRAMA, //programa
    SVAR, //var
    SDOISPONTOS, //:
    SINICIO, //inicio
    SFIM, //fim
    SATRIBUICAO, //:=
    STIPO, //:
    SESCREVA, //escreva
    SINTEIRO, //inteiro
    SPONTO_E_VIRGULA, //;
    SPONTO, //.
    SMAIS, //+
    SMENOS, //-
    SMULTIPLICACAO, //*
    SDIVISAO, // DIVISÃO 
    SNUMERO, //5
    SIDENTIFICADOR, //x, teste
    SABRE_PARENTESIS, //(
    SFECHA_PARENTESIS, //)
    SERRO					//Usado para tokens não reconhecidos

}
