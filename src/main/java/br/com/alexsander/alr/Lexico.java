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
 */

public class Lexico {

    // Stream para leitura do arquivo
    PushbackReader r;

    // Lista de tokens
    ArrayList<Token> listaToken;

    // Código do caracter sendo analisado
    int intch;

    //Caracter sendo analisado
    char ch;
    
    private Token buscaToken() {
        
        // Lexema sendo construido
        String lexema = "";
        
        switch(ch){
            case ':': 
                // Pode ser definição de variável ou atribuição
                //ler próximo caractér para decidir
                ch = leCh();
                //Se for = significa que é uma atribuição
                if(ch == '=') 
                    return new Token(TipoToken.SATRIBUICAO, ":=", 0, 0);
                // Se não, e uma definição de variável
                else 
                    return new Token(TipoToken.SDOISPONTOS, ":", 0,0);
            //Caso for ;
            case ';':
                return new Token(TipoToken.SPONTO_E_VIRGULA, ";", 0,0);
               
    		//Caso for (
    		case '(':
    			//Retorna o token... e assim por diante
    			return new Token(TipoToken.SABRE_PARENTESIS, "(", 0, 0);
    				
        	//Caso for )
    		case ')':
    			return new Token(TipoToken.SFECHA_PARENTESIS, ")", 0, 0);
    				
        	//Caso for .		
    		case '.':
    			return new Token(TipoToken.SPONTO, ".", 0, 0);
    				
    			
    		// Se encontrar o p, virá validar se é 'programa'                
            case 'p':
                lexema = String.valueOf(ch);
                ch = leCh();
                if(ch == 'r') {
                    lexema += String.valueOf(ch);
                    ch = leCh();
                    if(ch == 'o') {
                        lexema += String.valueOf(ch);
                        ch = leCh();
                        if(ch == 'g') {
                            lexema += String.valueOf(ch);
                            ch = leCh();
                            if(ch == 'r') {
                                lexema += String.valueOf(ch);
                                ch = leCh();
                                if(ch == 'a') {
                                    lexema += String.valueOf(ch);
                                    ch = leCh();
                                    if(ch == 'm') {
                                        lexema += String.valueOf(ch);
                                        ch = leCh();
                                        if(ch == 'a') {
                                            lexema += String.valueOf(ch);
                                            return new Token(TipoToken.SPROGRAMA, lexema, 0, 0); 
                                       }                                        
                                    }
                                }
                            }
                        }
                    }
                }
                
            // Se encontrar o t, virá validar se é do programa'teste'
            case 't':
                lexema = String.valueOf(ch);
                ch = leCh();
                if(ch == 'e') {
                    lexema += String.valueOf(ch);
                    ch = leCh();
                    if(ch == 's') {
                        lexema += String.valueOf(ch);
                        ch = leCh();
                        if(ch == 't') {
                            lexema += String.valueOf(ch);
                            ch = leCh();
                            if(ch == 'e') {
                                lexema += String.valueOf(ch);
                                return new Token(TipoToken.SIDENTIFICADOR, lexema, 0, 0);
                            }
                        }
                    }
                }
                
                
             // Se encontrar o i, virá validar se é 'inicio'
            case 'i':
                lexema = String.valueOf(ch);
                ch = leCh();
                if(ch == 'n') {
                    lexema += String.valueOf(ch);
                    ch = leCh();
                    if(ch == 'i') {
                        lexema += String.valueOf(ch);
                        ch = leCh();
                        if(ch == 'c') {
                            lexema += String.valueOf(ch);
                            ch = leCh();
                            if(ch == 'i') {
                                lexema += String.valueOf(ch);
                                ch = leCh();
                                if(ch == 'o') {
                                    lexema += String.valueOf(ch);
                                    return new Token(TipoToken.SINICIO, lexema, 0, 0);
                                }
                            }
                        }
                    }
                }
            
             // Se encontrar o v, virá validar se é 'var'
            case 'v':
                lexema = String.valueOf(ch);
                ch = leCh();
                if(ch == 'a') {
                    lexema += String.valueOf(ch);
                    ch = leCh();
                    if(ch == 'r') {
                        lexema += String.valueOf(ch);
                        return new Token(TipoToken.SVAR, lexema, 0, 0);
                    }
                }
                
             // Se encontrar o f, irá validar se é 'fim'
             case 'f':
                   lexema = String.valueOf(ch);
                   ch = leCh();
                   if(ch == 'i') {
                       lexema += String.valueOf(ch);
                       ch = leCh();
                       if(ch == 'm') {
                           lexema += String.valueOf(ch);
                           return new Token(TipoToken.SVAR, lexema, 0, 0);
                       }
                   }               

                
            // Se encontrar o x, ele é o identificador neste caso
            case 'x':
                lexema = String.valueOf(ch);
                return new Token(TipoToken.SIDENTIFICADOR, lexema, 0, 0);
                
                
        		//Ver como fazer para identificar números, mais de 1 no mesmo case - OK
        		//Como retornar o próprio lexema, o número?	
        				
        		//Tentativa abaixo não reconheceu como número
        		//case '0' | '1' | '2'| '3' | '4' | '5' | '6' | '7' | '8' | '9':
        		//	return new Token(TipoToken.SNUMERO, lexema, 0, 0);
        				
        		//Segunda tentativa, case reconheceu como número, mas não retornou o lexema
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
        			return new Token(TipoToken.SNUMERO, lexema, 0, 0);				 
                

                
    		//Por padrão, irá retornar erro caso não encontrar tratamento     
            default:
                return new Token(TipoToken.SERRO, lexema, 0,0);
        }
        
        
    }
    
    private char leCh() {
    	
    	
    	//Tentativa de validar linha x coluna
		/*
		int linha = 0;
		int coluna = 0;		
		
			ch = leCh();
			coluna++;
			if (ch == '\n') {
			linha++;
			coluna =0;
			}
		*/
    	
    	
        try {
            intch = r.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        if(intch == -1)
            return '@';
        else
            return (char) intch;
 
    }

	//Método analisa recebe nome do arquivo
	public ArrayList<Token> analisa (String arquivo){
		
		//Cria a lista de tokens
		listaToken = new ArrayList<Token>();
		
		//Abre o stream para leitura
        try {
            r = new PushbackReader(
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(arquivo), "US-ASCII")));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
        
		//Laço de repetição para percorrer o Stream todo
        while((ch = leCh()) != '@'){
        	
            // Pula comentários do código Pascal/LPD
            if(ch == '{') {
                while(ch != '}') {
                    ch = leCh();
                }
            }
            
            // Pula espaços em branco, tabs e nova linha 
            while(ch == ' ' || ch == '\n' || ch == '\t') {
                ch = leCh();
            }
            
			//A cada volta do laço, enquanto não chegar no fim, determinado pelo caract @
			//Vai lendo token a token e adicionando na lista
            listaToken.add(buscaToken());
        }
        
		//Retorna a lista de token pro programa principal LexicoMain
        return listaToken;
    }
}
