package alr1;

import java.io.IOException;


/**
 *
 * @author alexs
 * @author taywornath
 */

public class PPR extends Parser {

    public PPR(String arquivo) {
        super();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void parse() {
        analisaPrograma();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean analisaPrograma() throws IOException{
        /*boolean bandeira = true;
        if(erro(" "))
            bandeira = false;
        return bandeira;
        */
    	
    	buscaToken();
    	
    	if (token.tipo == TipoToken.SPROGRAMA) {
    		System.out.print (token.tipo + "");
    		buscaToken();
    		
    		if (token.tipo == TipoToken.SIDENTIFICADOR) {
    			System.out.print(token.tipo +": " + token.lexema + ' ');
    			buscaToken();
    			//Adiciona identificador na tabela de simbolos
    			ts.ts.put(key, value);
    			buscaToken();
    			
    			if (token.tipo == TipoToken.SPONTO_E_VIRGULA) {
    				System.out.print(token.tipo + " ");
    				if (analisaBloco()) {
    					buscaToken();
    					if (token.tipo == TipoToken.SPONTO)
    						System.out.print(token.tipo + " ");
    						return true;
    				}else {
    					System.out.println("Bloco principal nao encontrado: " +token.linha +", " +token.coluna);
    					return false;
    				}
    			}else {
    				System.out.println("Ponto e virgula esperado: " +token.linha +", " +token.coluna);
					return false;
    			}
    		}
    	}else {
    		System.out.println("Identificador esperado: " +token.linha +", " +token.coluna);
			return false;
    	}
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
    
    
    
    public static void main(String[] args) {
        PPR ppr = new PPR("teste1.lpd");
        ppr.parse();
    }

}
