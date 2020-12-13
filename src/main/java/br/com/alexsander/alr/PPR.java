package br.com.alexsander.alr;
// package alr1;

import java.io.IOException;

/**
 *
 * @author Alexsander
 * @author taywornath
 */
public class PPR extends Parser {

    public PPR(String arquivo) {
        super();
    }

    // Método que inicia a analise sintatica do codigo LPD
    @Override
    public void parse() {
        expressao();        
    }
    
    public void expressao() {
        // Aqui eh chamado o metodo pai da classe para retornar os tokens e entao
        // realizar a validacao das regras sintaticas
        token = super.buscaToken();
        if (token.tipo == TipoToken.SMAIS || token.tipo == TipoToken.SMENOS) {
            token = super.buscaToken();
        }
        termo();
        while (token.tipo == TipoToken.SMAIS || token.tipo == TipoToken.SMENOS) {
            token = super.buscaToken();
            termo();
        }
    }

    public void termo() {
        fator();
        token = super.buscaToken();
        while (token.tipo == TipoToken.SMULTIPLICACAO || token.tipo == TipoToken.SDIVISAO) {
            token = super.buscaToken();
            fator();
            token = super.buscaToken();
        }
    }

    public void fator() {
        if (token.tipo == TipoToken.SIDENTIFICADOR || token.tipo == TipoToken.SNUMERO); 
        else if (token.tipo == TipoToken.SABRE_PARENTESIS) {
            expressao();
            token = super.buscaToken();
            if (token.tipo == TipoToken.SFECHA_PARENTESIS); else {
                System.out.println(") esperado");
            }
        }

    }

    public boolean analisaPrograma() throws IOException {
        /*boolean bandeira = true;
        if(erro(" "))
            bandeira = false;
        return bandeira;
         */

        buscaToken();

        if (token.tipo == TipoToken.SPROGRAMA) {
            System.out.print(token.tipo + "");
            buscaToken();

            if (token.tipo == TipoToken.SIDENTIFICADOR) {
                System.out.print(token.tipo + ": " + token.lexema + ' ');
                //Adiciona identificador na tabela de simbolos
                Chave chave = new Chave("programa", token.tipo, token.lexema);
                ts.ts.put(chave, token);
                buscaToken();

                if (token.tipo == TipoToken.SPONTO_E_VIRGULA) {
                    System.out.print(token.tipo + " ");
                    if (analisaBloco()) {
                        buscaToken();
                        if (token.tipo == TipoToken.SPONTO) {
                            System.out.print(token.tipo + " ");
                        }
                        return true;
                    } else {
                        System.out.println("Bloco principal nao encontrado: " + token.linha + ", " + token.coluna);
                        return false;
                    }
                } else {
                    System.out.println("Ponto e virgula esperado: " + token.linha + ", " + token.coluna);
                    return false;
                }
            }
        } else {
            System.out.println("Identificador esperado: " + token.linha + ", " + token.coluna);
            return false;
        }
        // Adicionado o return para poder compilar... ajustar os retornos
        return false;
    }

    public boolean analisaBloco() {
        return false;
    }

    public boolean analisaEtapaDeclaracaoDeVariaveis() {
        return false;
    }

    @Override
    public boolean erro(String cadeia) {
        return super.erro(cadeia);
    }

}
