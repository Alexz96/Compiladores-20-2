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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexs
 */
public class Lexico {

    // Stream para leitura do arquivo
    PushbackReader r;

    // Lista de tokens
    ArrayList<Token> listaToken;

    // Código do carácter sendo analisado
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
                if(ch == '=') // É uma atribuição
                    return new Token(TipoToken.SATRIBUICAO, ":=", 0, 0);
                else // É uma definição de variável
                    return new Token(TipoToken.SDOISPONTOS, ":", 0,0);
            case ';':
                return new Token(TipoToken.SPONTO_E_VIRGULA, ";", 0,0);
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
            case 'x':
                lexema = String.valueOf(ch);
                return new Token(TipoToken.SIDENTIFICADOR, lexema, 0, 0);
            default:
                return new Token(TipoToken.SERRO, lexema, 0,0);
        }
        
    }
    
    private char leCh() {
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

    public ArrayList<Token> analisa(String arquivo) {

        listaToken = new ArrayList<Token>();

        try {
            r = new PushbackReader(
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(arquivo), "US-ASCII")));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
        
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
            
            listaToken.add(buscaToken());
        }

        return listaToken;
    }
}
