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
 * @author Alexsander
 * @author taywornath
 * @author TeoFalleiro
 */
public class Lexico {

    // Stream para leitura do arquivo
    PushbackReader r;

    // Lista de tokens
    ArrayList<Token> listaToken;

    // Código do caracter sendo analisado
    int intch;

    // Caracter sendo analisado
    char ch;
    // Criacao e inicializacao da linha e da coluna na primeira posicao
    private int coluna = 0;
    private int linha = 1;
    private int parenteses = 0; // contador para verificar se parenteses estao balanceados
    
    // Instanciação da tabela de simbolos
    TabelaDeSimbolos ts = new TabelaDeSimbolos();

    public Token buscaToken() {

        // Lexema sendo construido quando for um id ou palavra chave
        String lexema = "";

        // Estado corrente do AFD
        int estado = 0;

        // Coluna iniciar do lexema que est� sendo lido
        int col = 0;

        // Enquanto nao encontrar o final identificado por @ vai ler e identificar ch
        while (ch != '@') {
            // Switch case para validar o estado 			
            switch (estado) {

                case 0:
                    col = coluna;
                    if (ch == '{') {
                        estado = 16;
                        break;
                    } else if (ch == ':') {
                        estado = 1;
                        break;
                    } //else if (ch == '(') estado = 2;
                    else if (ch == '+') {
                        return new Token(TipoToken.SMAIS, "+", linha, col);
                    } else if (ch == '-') {
                        return new Token(TipoToken.SMENOS, "-", linha, col);
                    } else if (ch == '*') {
                        return new Token(TipoToken.SMULTIPLICACAO, "*", linha, col);
                    } else if (ch == '/') {
                        return new Token(TipoToken.SDIVISAO, "/", linha, col);
                    } else if (ch == ';') {
                        if (parenteses != 0) {
                            System.out.printf("Parenteses nao estao balanceados na linha: %d \n", linha);
                        }
                        parenteses = 0;
                        return new Token(TipoToken.SPONTO_E_VIRGULA, ";", linha, col);
                    } else if (ch == ',') {
                        return new Token(TipoToken.SVIRGULA, ",", linha, col);
                    } else if (ch == '.') {
                        return new Token(TipoToken.SPONTO, ".", linha, col);
                    } else if (ch == '(') {
                        parenteses++;
                        return new Token(TipoToken.SABRE_PARENTESIS, "(", linha, col);
                    } else if (ch == ')') {
                        parenteses--;
                        return new Token(TipoToken.SFECHA_PARENTESIS, ")", linha, col);
                    } else if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
                        estado = 2;
                        break;
                    } else if (ch == 'a' || ch == 'b' || ch == 'c' || ch == 'd' || ch == 'e' || ch == 'f' || ch == 'g' || ch == 'h' || ch == 'i' || ch == 'j' || ch == 'k' || ch == 'l' || ch == 'm' || ch == 'n' || ch == 'o' || ch == 'p' || ch == 'q' || ch == 'r' || ch == 's' || ch == 't' || ch == 'u' || ch == 'v' || ch == 'w' || ch == 'x' || ch == 'y' || ch == 'z') {
                        estado = 3;
                        break;
                    }

                case 1:
                    if (ch == '=') {
                        estado = 0;
                        return new Token(TipoToken.SATRIBUICAO, ":=", linha, coluna);
                    } else {
                        //devolver();
                        estado = 0;
                        return new Token(TipoToken.STIPO, ":", linha, coluna);
                    }
                case 2:
                    while (ch != ' ' && ch != '\r' && ch != '\n' && ch != ';' && ch != ',' && ch != '.' && ch != '('
                            && ch != ')' && ch != '+' && ch != '-' && ch != '*' && ch != '/') {
                        // Lexema ira receber o valor do ch lido
                        lexema += String.valueOf(ch);
                        ch = leCh();
                    }
                    // Senao vai devolver o ch lido
                    devolver();
                    estado = 0;
                    // Devolvendo o lexema, sua linha e coluna descontando o tamanho do lexema
                    return new Token(TipoToken.SNUMERO, lexema, linha, col);
                case 3:
                    // While para formar e reconhecer as palavras , le ate que encontre algum ch
                    // abaixo
                    while (ch != ' ' && ch != '\r' && ch != ';' && ch != '\n' && ch != ',' && ch != '.' && ch != '('
                            && ch != ')' && ch != ':') {
                        lexema += String.valueOf(ch);
                        ch = leCh();
                    }
                    // Devolve o que foi encontrado
                    devolver();
                    estado = 0;
                    // if para reconhecer as palavras identificadoras, sempre retornando o
                    if (lexema.equals("programa")) {
                        return new Token(TipoToken.SPROGRAMA, lexema, linha, coluna - lexema.length());
                    } else if (lexema.equals("inicio")) {
                        return new Token(TipoToken.SINICIO, lexema, linha, coluna - lexema.length());
                    } else if (lexema.equals("fim")) {
                        return new Token(TipoToken.SFIM, lexema, linha, coluna - lexema.length());
                    } else if (lexema.equals("var")) {
                        return new Token(TipoToken.SVAR, lexema, linha, coluna - lexema.length());
                    } else if (lexema.equals("escreva")) {
                        System.out.println(escreveResultado());
                        return new Token(TipoToken.SESCREVA, lexema, linha, coluna - lexema.length());
                    } else if (lexema.equals("inteiro")) {
                        return new Token(TipoToken.SINTEIRO, lexema, linha, coluna - lexema.length());
                    } else if (lexema.equals("booleano")) {
                        return new Token(TipoToken.SBOOLEANO, lexema, linha, coluna - lexema.length());
                    } else {
                        Chave chave = new Chave("Programa", TipoToken.SIDENTIFICADOR, lexema);
                        Token token = new Token(TipoToken.SIDENTIFICADOR, lexema, linha, coluna - lexema.length());
                        ts.addTokenNaTS(chave, token);
                        System.out.println("Tabela de simbolos por enquanto: " + ts.getToken(chave));
                        return token;
                    }

                case 16:
                    while (ch != '}') {
                        leCh();
                    }
                    estado = 0;
                    break;

            }
        } //Fim while
        return new Token(TipoToken.SERRO, " ", linha, coluna);
    }

    // funcao incompleta, acredito que sera necessario lidar melhor com questoes do operando e operador
    // criando um novo metodo para gerenciar isto com base nos ch lidos
    public Token escreveResultado() {

        int operador1 = 0;
        int operador2 = 0;
        int resultado = 0;
        
        ch = leCh();
        coluna++;

        if (Character.isSpaceChar(ch)) {
            ch = leCh();
            // se for identificado que o caracter seguinte eh numero, faz os calculos 
            if (Character.isDigit(ch)) {
                operador1 = (int) ch;
                ch = leCh();
                coluna++;
                switch (ch) {
                    case '/':
                        break;
                    case '*':
                        break;
                    case '-':
                        break;
                    case '+':
                        ch = leCh();
                        coluna++;
                        if(Character.isDigit(ch)) operador2 = (int) ch;
                        System.out.println(ch);
                        resultado = soma(operador1, operador2);
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("Não posso realizar esta operação.");
            }
        }
        
        return new Token(TipoToken.SNUMERO, String.valueOf(ch), linha, coluna);
    }

    // Criacao do metodo devolver
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

    //Criacao do metodo leCh
    private char leCh() {
        try {
            intch = r.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // A cada nova linha soma uma linha - com base no caractere ASCii
        if (intch == 10) { // 10 é o código ASCII do \n
            linha++;
            coluna = 1;
        } else {
            coluna++;
        }

        if (intch == -1) {
            return '@';
        } else {
            return (char) intch;
        }
    }

    // Método analisa recebe nome do arquivo
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
                } else {
                    ch = leCh();
                }
            }

            // A cada volta do laço, enquanto não chegar no fim, determinado pelo caract @
            // Vai lendo token a token e adicionando na lista
            listaToken.add(buscaToken());
        }

        // Retorna a lista de token pro programa principal LexicoMain
        return listaToken;
    }
    
    // Metodo para lidar com a operacao de soma
    public int soma(int operadorA, int operadorB) {
        return operadorA + operadorB;
    }
    
    // Metodo para lidar com a operacao de subtracao
    public int subtrai(int operadorA, int operadorB) {
        return operadorA + operadorB;
    }
    
    // Metodo para lidar com a operacao de multiplicacao
    public int multiplica(int operadorA, int operadorB) {
        return operadorA * operadorB;
    }
    
    // Metodo para lidar com a operacao de divisao
    public int divide(int operadorA, int operadorB) {
        
        if(operadorB == 0) {
            System.out.println("Impossível dividir por zero!");
            return 0;
        }
        
        return operadorA / operadorB;
    }
}
