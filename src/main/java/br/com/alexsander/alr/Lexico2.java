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

public class Lexico2 {

	// Stream para leitura do arquivo
	PushbackReader r;

	// Lista de tokens
	ArrayList<Token> listaToken;

	// Código do caracter sendo analisado
	int intch;

	// Caracter sendo analisado
	char ch;
        
        private int coluna = 1; 
        
        private int linha = 1;
        
        // Atributo da classe para se identificar os estados do AFD
        int estado = 0;
        
        // Coluna iniciar do lexema que está sendo lido
	int col = 0;
        
	public ArrayList<Token> buscaToken(String arquivo) {
            
            // Cria a lista de tokens
            listaToken = new ArrayList<Token>();

            // Abre o stream para leitura
            try {
                    r = new PushbackReader(new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "US-ASCII")));
            } catch (UnsupportedEncodingException | FileNotFoundException e) {
                    e.printStackTrace();
            }
            
            // Lexema sendo construido quando for um id ou palavra chave
		String lexema = "";
            
            // Enquanto nao chegou o fim do arquivo, segue verificando os carac.
            while((ch = leCh()) != '@') {
                // Switch para que seja feita a validacao do estado do AFD
                switch(estado) {
                    case 0:
                        col = coluna;
                        lexema = String.valueOf(ch);
                        switch(ch) {
                            case ':':
                                ch = leCh();
                                if(ch != '=')
                                    estado = 3;
                                else 
                                    estado = 2;
                                break;
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
                        estado = 14;
                                
                        } // Fim do Switch do ch
                        break;
                    case 2:
                        devolver();
                        listaToken.add(new Token(TipoToken.SATRIBUICAO, ":=", linha, col));
                        estado = 0;
                        break;
                    case 3:
                        listaToken.add(new Token(TipoToken.STIPO, ":", linha, col));
                        estado = 0;
                        
                    case 14:
                        lexema += String.valueOf(ch);
                        if(Character.isLetter(ch)) {
                            lexema += ch;
                        } else if(!Character.isLetter(ch)) {
                            listaToken.add(new Token(TipoToken.SIDENTIFICADOR, lexema, linha, col));
                        }
                } // Fim do Switch do estado
                
                
                
                
            } // Fim do While de verificacao do stream
            
            return listaToken;
	} // Fim da funcao buscaToken

	private void devolver() {
		
		try {
			r.unread(ch);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
                // Se for encontrado quebra de linha, vai diminuir a linha, senao a coluna
		if (ch == '\n') {
			linha--;
		} else {
			coluna--;
		}
	}
        
	private char leCh() {
            try {
			intch = r.read();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

                // A cada nova linha soma uma linha - com base no caractere ASCii
                if(intch == 10) { // 10 é o código ASCII do \n
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

	// Método analisa recebe nome do arquivo
	public ArrayList<Token> analisa(String arquivo) {

		// Laço de repetição para percorrer o Stream todo
		while ((ch = leCh()) != '@') { // Enquanto não chegou no fim do arquivo

			// Pula espacos em branco, tabs e nova linha
			while (ch == ' ' || ch == '\n' || ch == '\t' || ch == '\r' || ch == '}' || ch == '{') {
				// Pula comentarios do coigo Pascal/LPD
				if (ch == '{') {
					while (ch != '}') {
						ch = leCh();
					}
				} else {
					ch = leCh();
				}
			}

		}

		// Retorna a lista de token pro programa principal LexicoMain
		return listaToken;
	}
}

