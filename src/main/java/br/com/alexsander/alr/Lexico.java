/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alexsander.alr;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author alexs
 * @author taywornath
 * @author TeoFalleiro
 */

public class Lexico {
	// Stream para leitura do arquivo
	PushbackReader r;
	// Lista de tokens com array
	ArrayList<Token> listaToken;
	// Codigo do caracter sendo analisado
	int intch;
	// Caracter sendo analisado
	char ch;
	//Criação e inicialização da linha e da coluna na primeira posição
	private int coluna = 1;
	private int linha = 1;

	private Token buscaToken() {

		// Lexema sendo construido
		String lexema = "";

		//Switch case para valiar o ch
		switch (ch) {

		// Caso for ;
		case ';':
			// Ja posso retornar o token se for ; pois nao tem outra composicao
			return new Token(TipoToken.SPONTO_E_VIRGULA, ";", linha, coluna - 1);

		// Caso for :
		case ':':
			// Pode ser definicao de variavel ou atribuicaoo, leª proximo carac para decidir
			ch = leCh();
			// Se for = significa que Ã© uma atribuiÃ§Ã£o
			if (ch == '=')
				return new Token(TipoToken.SATRIBUICAO, ":=", linha, coluna - 1);
			// Se nao, ele e uma definiÃ§Ã£o de variavel
			else
				return new Token(TipoToken.SDOISPONTOS, ":", linha, coluna - 1);


		// Validacao de entrada de numeros
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			//Enquanto nao localizar algum destes caracteres
			while(ch != ' ' && ch != '\r' && ch != '\n' && ch != ';' && ch != ',' && ch != '.' && ch != '(' && ch != ')'){
				//Lexema ira receber o valor do ch lido
				lexema += String.valueOf(ch);
				ch = leCh();
			}
			//Senao vai devolver o ch lido
			devolver();
			//Devolvendo o lexema, sua linha e coluna descontando o tamanho do lexema
			return new Token(TipoToken.SNUMERO, lexema, linha, coluna - lexema.length());

		// Caso for (
		case '(':
			// Retorna o token... e assim por diante
			return new Token(TipoToken.SABRE_PARENTESIS, "(", linha, coluna - 1);
		// Caso for )
		case ')':
			return new Token(TipoToken.SFECHA_PARENTESIS, ")", linha, coluna - 1);
		case '.':
			return new Token(TipoToken.SPONTO, ".", linha, coluna - 1);
		case ',':
			return new Token(TipoToken.SVIRGULA, ",", linha, coluna - 1);
		// Casos para Operacoes
		case '+':
			return new Token(TipoToken.SMAIS, "+", linha, coluna - 1);
		case '*':
			return new Token(TipoToken.SMULTIPLICACAO, "*", linha, coluna - 1);
		case '-':
			return new Token(TipoToken.SMENOS, "-", linha, coluna - 1);
		case '/':
			return new Token(TipoToken.SDIVISAO, "/", linha, coluna - 1);

		// Casos para palavras e letras	
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			//While para formar e reconhecer as palavras , le ate que encontre algum ch abaixo
			while(ch != ' ' && ch != '\r' && ch != ';' && ch != '\n' && ch != ',' && ch != '.' && ch != '(' && ch != ')' && ch != ':'){
				lexema += String.valueOf(ch);
				ch = leCh();
			}
			//Devolve o que foi encontrado
			devolver();
			
			//if para reconhecer as palavras identificadoras, sempre retornando o
			if(lexema.equals("programa")){
				return new Token(TipoToken.SPROGRAMA, lexema, linha, coluna - lexema.length());
			}else if(lexema.equals("inicio")){
				return new Token(TipoToken.SINICIO, lexema, linha, coluna - lexema.length());
			}else if(lexema.equals("fim")){
				return new Token(TipoToken.SFIM, lexema, linha, coluna - lexema.length());
			}else if(lexema.equals("var")){
				return new Token(TipoToken.SVAR, lexema, linha, coluna - lexema.length());
			}else if(lexema.equals("escreva")){
				return new Token(TipoToken.SESCREVA, lexema, linha, coluna - lexema.length());
			}else if(lexema.equals("inteiro")){
				return new Token(TipoToken.SINTEIRO, lexema, linha, coluna - lexema.length());
			}else{
				return new Token(TipoToken.SIDENTIFICADOR, lexema, linha, coluna - lexema.length());
			}
		// Por padrao, ira retornar erro caso nao encontrar o tipo lexema cadastrado
		default:
			return new Token(TipoToken.SERRO, String.valueOf(ch), linha, coluna - 1);
		}

	}

/*	private int escreveResultado() {
		int operacao;

		ch = leCh();
		coluna++;
		if(ch == '1' || ch == '2' || ch == '3' || ch == '4' 
     			|| ch == '5' || ch == '6' || ch == '7' || ch == '8'
				|| ch == '9' || ch == '0') {
				operacao = (int) ch;
				ch = leCh();
				coluna++;
				switch (ch) {
					case '+':
						ch = leCh();
						operacao += (int) ch;
						lexema = String.valueOf(operacao);
						return new Token(TipoToken.SOPERACAOSOMA, lexema, linha, coluna);
					case '-':
						ch = leCh();
						operacao += (int) ch;
						lexema = String.valueOf(operacao);
						return new Token(TipoToken.SOPERACAOSUBTRACAO, lexema, linha, coluna);
					case '*':
						ch = leCh();
						operacao += (int) ch;
						lexema = String.valueOf(operacao);
						return new Token(TipoToken.SOPERACAOMULT, lexema, linha, coluna);
					default:
						ch = leCh();
						operacao += (int) ch;
						lexema = String.valueOf(operacao);
						return new Token(TipoToken.SOPERACAODIV, lexema, linha, coluna);
					}
			}

		return operacao
	}

 	*/
	
	//Criacao do metodo devolver
	private void devolver() {
		//Se for encontrado
		if(ch == '\n'){
			linha--;
		}else{
			coluna--;
		}
		try {
			r.unread(ch);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private char leCh() {

		// Tentativa de validar linha x coluna
		/*
		 * int linha = 0; int coluna = 0;
		 * 
		 * ch = leCh(); coluna++; if (ch == '\n') { linha++; coluna =0; }
		 */
                
		try {
			intch = r.read();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

                // A cada nova linha soma uma linha - com base no caractere ASCii
                if(intch == 10) { // 10 e o codigo ASCII do \n
                    linha++;
                    coluna = 1;
                } else {
                    coluna++;
                }
                
		if (intch == -1)
			return '@';
		else
			return (char) intch;

	}

	// Metodo analisa recebe nome do arquivo
	public ArrayList<Token> analisa(String arquivo) {

		// Cria a lista de tokens
		listaToken = new ArrayList<Token>();

		// Abre o stream para leitura
		try {
			r = new PushbackReader(new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "US-ASCII")));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}

		// Laco de repeticao para percorrer o Stream todo
		while ((ch = leCh()) != '@') {

			// Pula espacos em branco, tabs e nova linha
			while (ch == ' ' || ch == '\n' || ch == '\t' || ch == '\r' || ch == '}' || ch == '{') {
				// Pula comentarios do coigo Pascal/LPD
				if (ch == '{') {
					while (ch != '}') {
						ch = leCh();
					}
				}else{
				ch = leCh();
				}				
			}

			// A cada volta do laco, enquanto nao chegar no fim, determinado pelo caract @
			// Vai lendo token a token e adicionando na lista
			listaToken.add(buscaToken());
		}

		// Retorna a lista de token pro programa principal LexicoMain
		return listaToken;
	}
}