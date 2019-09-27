package com.xoff.mathrice.run;

import com.xoff.mathrice.model.Matrice;

/**
 * @author xoffp
 * @description a remplir
 */
public class Run {

    public static void main(String[] args) {
        int DIM = 3;
        Matrice m1 = new Matrice(DIM, DIM);
        m1.alea();
        System.out.println("M1:\n"+m1);
 //       System.out.println("Determinant:" + m1.determinant());
      //  System.out.println("Tranposee:\n" + m1.transpose());
     //   System.out.println("Trace:" + m1.trace());
      //  Matrice m2 = new Matrice(DIM, DIM);
     //   m2.alea();
     //   System.out.println("M2:\n"+m2);
     //   System.out.println("Produit:\n" + m1.product(m2));
        
        System.out.println("Inverse:\n" + m1.inverse());
        /*
        
        -2 1 0 0 
1 0 -2 -2 
-1 -2 -1 0 
0 -1 1 -2 
=> -36
         */
    }
}
