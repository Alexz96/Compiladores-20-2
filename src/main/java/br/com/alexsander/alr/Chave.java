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
    private String tipo;
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
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
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
    
    public Chave(String escopoParametro, String tipoParametro, String lexemaParametro) {
        setEscopo(escopoParametro);
        setTipo(tipoParametro);
        setLexema(lexemaParametro);
    }
    
}
