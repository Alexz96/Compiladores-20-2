package br.com.alexsander.alr;

import br.com.alexsander.alr.TipoToken;

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
    	
    	if (t.tipo == TipoToken.SPROGRAMA) {
    		System.out.print (token.tipo + "");
    		buscaToken();
    		
    		if (t.tipo == TipoToken.SIDENTIFICADOR) {
    			System.out.print(t.tipo +": " + t.c + ' ');
    			buscaToken();
    			//Adiciona identificador na tabela de simbolos
    			ts.ts.put(c, t);
    			buscaToken();
    			
    			if (t.tipo == TipoToken.SPONTO_E_VIRGULA) {
    				System.out.print(t.tipo + " ");
    				if (analisaBloco()) {
    					buscaToken();
    					if (t.tipo == TipoToken.SPONTO)
    						System.out.print(T.tipo + " ");
    						return true;
    				}else {
    					System.out.println("Bloco principal nao encontrado: " +t.linha +", " +t.coluna);
    					return false;
    				}
    			}else {
    				System.out.println("Ponto e virgula esperado: " +t.linha +", " +t.coluna);
					return false;
    			}
    		}
    	}else {
    		System.out.println("Identificador esperado: " +t.linha +", " +t.coluna);
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
