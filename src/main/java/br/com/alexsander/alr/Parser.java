package br.com.alexsander.alr;

/**
 *
 * @author alexs
 */
public abstract class Parser {
    
    TabelaDeSimbolos ts;
    Lexico lexico;
    Token token;
    
    public Parser() {
        ts = new TabelaDeSimbolos();
        lexico = new Lexico();
    }
    
    public abstract void parse();
    
    public Token buscaToken() {
        token = lexico.buscaToken();
        return token;
    }
    
    public boolean erro(String cadeia) {
        int linha = 1;
        int coluna = 0;
        
        System.out.println("Palavra: " + cadeia + " na linha: " + linha + " na coluna: " + coluna);
        
        return false;
    }
    
}
