package com.xoff.mathrice.run;

import com.xoff.mathrice.model.Matrice;

/**
 * @author xoffp
 * @description a remplir
 */
public class Run {
    public static void main(String[] args) {
        Matrice m=new Matrice(5,5);
        m.alea();
        System.out.println(m);
        System.out.println("Determinant:"+m.determinant());
        System.out.println("Trace:"+m.trace());
    }
}
