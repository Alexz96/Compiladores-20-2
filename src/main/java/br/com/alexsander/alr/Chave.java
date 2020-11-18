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
public class Chave {
    
    private String escopo;
    private TipoToken tipo;
    private String lexema;

    /**
     * @return the escopo
     */
    public String getEscopo() {
        return escopo;
    }

    /**
     * @param escopo the escopo to set
     */
    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    /**
     * @return the tipo
     */
    public TipoToken getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the lexema
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * @param lexema the lexema to set
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    public Chave(String escopoParametro, TipoToken tipoParametro, String lexemaParametro) {
        this.escopo = escopoParametro;
        this.tipo = tipoParametro;
        this.lexema = lexemaParametro;
    }
    
}
