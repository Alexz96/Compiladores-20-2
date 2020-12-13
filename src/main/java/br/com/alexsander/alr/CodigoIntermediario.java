/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alexsander.alr;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author Alexsander
 */
public class CodigoIntermediario {

    //Numero de variaveis intermediarias
    int temp;
    //Codigo que vem do parser
    String codigo;

    public CodigoIntermediario(int t, String cod) {
        temp = t;
        codigo = cod;
        geraLLVMIR();
    }

    public String geraPreambulo() {
        String print;
        print = "@.str = private unnamed_addr constant [ 3 x i8] c\"%d\00\", align 1 \n";
        print += "; Function Attrs: noinline nounwind optone uwtable \n";
        print += "define dso_local i32 @main() #0 { \n";
        return print;
    }

    public String geraCodigo() {
        return codigo;
    }

    public String geraEpilogo() {
        String print;
        print = "}\n";
        print += "declare dso_local i32 @printf(i8*, ...) #1\n";
        return print;
    }

    public void geraLLVMIR() {
        String preambulo = ";Preambulo\n";
        String epilogo = ";Epilogo\n";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("saida.ll"));
            writer.write(geraPreambulo());
            writer.write(geraCodigo());
            writer.write(geraEpilogo());
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        };
    }
}
