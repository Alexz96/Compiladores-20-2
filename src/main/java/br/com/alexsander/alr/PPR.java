package br.com.alexsander.alr;

/**
 *
 * @author alexs
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

    public boolean analisaPrograma() {
        boolean bandeira = true;
        
        if(erro(" "))
            bandeira = false;
        
        return bandeira;
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
