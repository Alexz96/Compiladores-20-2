package br.com.alexsander.alr;

/**
 *
 * @author alexs
 */
public enum TipoToken {
	SPROGRAMA, 			//programa
	SVAR, 				//var
	SDOISPONTOS, 			//:
	SINICIO, 			//inicio
	SFIM, 				//fim
	SATRIBUICAO, 			//:=
	STIPO,				//:
	SVIRGULA,			//,
	SESCREVA, 			//escreva
	SINTEIRO,			//inteiro
	SBOOLEANO,			//boolean
	SPONTO_E_VIRGULA, 		//;
	SPONTO,				//.
	SMAIS,				//+
	SMENOS,				//-
	SMULTIPLICACAO,			//*
	SDIVISAO,                       // /
	SNUMERO,			//5
	SIDENTIFICADOR,			//x, teste
	SABRE_PARENTESIS,		//(
	SFECHA_PARENTESIS,		//)
        SOPERACAO,                      // Usado para identificar uma operacao
        SCODIGO,
	SERRO				//Usado para tokens nao reconhecidos

}

/*
Anotacao --> foram removidos string, char, float e afins, pois cfe apostila, estes
tipos nao sao implementados na linguagem LPD
*/