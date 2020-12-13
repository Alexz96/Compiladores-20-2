/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alexsander.alr;

/**
 *
 * @author Alexsander
 */
public class ParseCodigo {

    Lexico lexico;
    Token token;

    //contador para nome de variaveis temporarias
    int temp = 1;
    //C�digo a ser escrito
    String codigo = "";

    public ParseCodigo(Lexico lex) {
        lexico = lex;
    }

    public void Erro(String s) {
        System.out.print(s);
    }

    public Token bt() {
        Token t = lexico.buscaToken();
        System.out.println(t.toString());
        return t;
    }

    public void salvaCodigo() {
        CodigoIntermediario codIntermediario = new CodigoIntermediario(temp, codigo);
        codIntermediario.geraLLVMIR();
    }

    public String parse() {
        expressao();
        salvaCodigo();
        return codigo;
    }

    public Token expressao() {
        Token t = new Token(TipoToken.SCODIGO, "", 0, 0);
        token = bt();

        if (token.tipo == TipoToken.SMAIS || token.tipo == TipoToken.SMENOS) {
            token = bt();
        }

        Token t1 = termo();
        while (token.tipo == TipoToken.SMAIS || token.tipo == TipoToken.SMENOS) {
            token = bt();
            Token t2 = termo();
            t.nome = geraTemp();
            geraCod(t.nome + " = add i32 " + t1.nome + ", " + t2.nome);
        }
        return t;
    }

    public Token termo() {
        Token t = new Token(TipoToken.SCODIGO, "", 0, 0);
        Token t1 = fator();
        token = bt();
        while (token.tipo == TipoToken.SMULTIPLICACAO || token.tipo == TipoToken.SDIVISAO) {
            token = bt();
            Token t2 = fator();
            t.nome = geraTemp();
            geraCod(t.nome + " = mul i32 " + t1.nome + ", " + t2.nome);
            token = bt();
        }
        return t;
    }

    public Token fator() {
        Token t = new Token(TipoToken.SCODIGO, "", 0, 0);
        if (token.tipo == TipoToken.SIDENTIFICADOR || token.tipo == TipoToken.SNUMERO) {
            t.lexema = token.lexema;
            t.codigoGerado = token.lexema;
            t.nome = geraTemp();
            geraCod("store i32 " + t.lexema + ", i32* " + t.nome + ", align 4");
        } else if (token.tipo == TipoToken.SABRE_PARENTESIS) {
            expressao();
            token = bt();
            if (token.tipo == TipoToken.SFECHA_PARENTESIS); else {
                System.out.println(") esperado");
            }
        }
        return t;

    }

    public String geraTemp() {
        Integer i = temp++;
        String nomeVar = '%' + i.toString();
        geraCod(nomeVar + " = alloca i32, align	4");
        return nomeVar;
    }

    public void geraCod(String s) {
        codigo += s + "\n";
    }
}
