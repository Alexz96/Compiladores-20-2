/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alexsander.alr;

/**
 *
 * @author alexs
 * @author taywornath
 * @author TeoFalleiro
 */
public enum TipoToken {
	SPROGRAMA, 			//programa
	SVAR, 				//var
	SDOISPONTOS, 			//:
	SINICIO, 				//inicio
	SFIM, 				//fim
	SATRIBUICAO, 			//:=
	STIPO,				//:
	SVIRGULA,			//,
	SESCREVA, 				//escreva
	SINTEIRO,				//inteiro
	SSTRING,				//string
	SCHAR,					//char
	SBOOLEAN,				//boolean
	SFLOAT,					//float
	SPONTO_E_VIRGULA, 		//;
	SPONTO,				//.
	SMAIS,				//+
	SMENOS,				//-
	SMULTIPLICACAO,			//*
	SDIVISAO,               ///
	SNUMERO,				//5
	SIDENTIFICADOR,			//x, teste
	SABRE_PARENTESIS,		//(
	SFECHA_PARENTESIS,		//)
    SOPERACAOSOMA,             // Usado para identficar operacao de soma
    SOPERACAOSUBTRACAO,        // Usado para identficar operacao de subtracao
    SOPERACAOMULT,             // Usado para identficar operacao de multiplicacao
    SOPERACAODIV,              // Usado para identficar operacao de divisao
	SERRO					//Usado para tokens nao reconhecidos
}