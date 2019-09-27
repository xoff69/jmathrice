package com.xoff.mathrice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xoffp
 * @description double matrix
 */
public class Matrice {

    /**
     * la matrice
     */
    private final double[][] matrice;
    private int rowmax;
    private int colmax;

    /**
     * le constructor
     *
     * @param row
     * @param col
     */
    public Matrice(int rowmax, int colmax) {

        matrice = new double[rowmax][colmax];
        this.rowmax = rowmax;
        this.colmax = colmax;
    }

    /**
     * true if matrix size is n*n
     *
     * @return
     */
    private boolean matricecarree() {
        return rowmax == colmax;
    }

    /**
     * matrix product
     *
     * @param autre
     * @return
     */
    public Matrice product(Matrice autre) {
        assert (colmax == autre.rowmax);

        Matrice produit = new Matrice(rowmax, autre.colmax);
        for (int i = 0; i < produit.rowmax; i++) {
            for (int j = 0; j < produit.colmax; j++) {
                // matrice 1 1 c'est le produit de la premiere ligne par les elements de la premiere colonne
                double prod = 0;
                for (int k = 0; k < colmax; k++) {
                    prod += matrice[i][k] * autre.matrice[k][j];
                }

                produit.matrice[i][j] = prod;
            }
        }
        return produit;
    }

    /**
     * multiply by scalar facteur
     *
     * @param facteur
     */
    public void mul(double facteur) {
        for (int i = 0; i < rowmax; i++) {
            for (int j = 0; j < colmax; j++) {
                matrice[i][j] = matrice[i][j] * facteur;
            }
        }
    }
    public boolean isSymetrique(){
        Matrice transposee=transpose();
          for (int i = 0; i < rowmax; i++) {
            for (int j = 0; j < colmax; j++) {
                if (matrice[i][j] != transposee.matrice[i][j]) return false;
            }
        }
          return true;
    }
    /**
     * M^-1
     *
     * @return
     */
    public Matrice inverse() {

        Matrice inverse = new Matrice(rowmax, colmax);
        // a: calcul du determinant !=0
        double determinant = determinant();
        assert (determinant != 0);

        // b: cofacteur
        // b1: calcul de cofacteur  
        for (int i = 0; i < rowmax; i++) {
            for (int j = 0; j < colmax; j++) {
                // sous matrice ne contenant pas i,j
                Matrice sub = new Matrice(rowmax - 1, colmax - 1);
                int k = 0, l = 0;
                for (int u = 0; u < rowmax; u++) {
                    for (int v = 0; v < colmax; v++) {
                        if (u != i && v != j) {
                            //   System.out.println("("+i+","+j+")"+"("+u+","+v+")->k="+k+"-"+l);
                            sub.matrice[k][l++] = matrice[u][v];
                        }
                    }
                    if (u != i) {
                        k++;
                    }
                    l = 0;
                }

                //    System.out.println("sub:\n" + sub);
                // System.out.println("det:"+i+"-"+j+":"+sub.determinant());
                inverse.matrice[i][j] = sub.determinant();
            }
        }
        //    System.out.println("etape 1\n:" + inverse);
        inverse.mul(1 / determinant);
        //    System.out.println("etape 2:" + inverse);
        // b2: on met le signe du cofacteur
        int signe = 1;
        for (int i = 0; i < rowmax; i++) {
            for (int j = 0; j < colmax; j++) {

                inverse.matrice[i][j] = inverse.matrice[i][j] * signe;
                signe = -signe;
            }
        }
        // c: transposee
        //    System.out.println("etape 3:" + inverse);
        return inverse.transpose();
    }

    /**
     * tM
     *
     * @return
     */
    public Matrice transpose() {
        Matrice transposee = new Matrice(colmax, rowmax);
        for (int i = 0; i < rowmax; i++) {
            for (int j = 0; j < colmax; j++) {
                transposee.matrice[j][i] = matrice[i][j];
            }
        }
        return transposee;
    }

    /**
     * matrice trace
     *
     * @return
     */
    public double trace() {
        assert (matricecarree());
        double trace = 0;
        int i = 0;
        // on a rowmax=col
        while (i < rowmax) {
            trace += matrice[i][i];
            i++;
        }
        return trace;
    }

    /**
     * random fill the matrix
     */
    public void alea() {
        Random r = new Random();
        for (int i = 0; i < rowmax; i++) {
            for (int j = 0; j < colmax; j++) {
                matrice[i][j] = r.nextDouble() * 2.1;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rowmax; i++) {
            for (int j = 0; j < colmax; j++) {
                // res.append(String.format("%.2f", matrice[i][j])).append(" ");
                res.append((int) matrice[i][j]).append(" ");
            }
            res.append("\n");
        }

        return res.toString().replaceAll(",", ".");
    }

    /**
     * a b
     * c d
     * ad-bc
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @return
     */
    private double finaldeterminant(double a, double b, double c, double d) {
        return a * d - (b * c);
    }

    /**
     *
     * @param lig
     * @param coldebut
     * @param colfin
     * @param colexclue list of excluse cols
     * @return
     */
    private double determinant(int lig, int coldebut, int colfin, List<Integer> colexclues) {
        if (getRowmax() - lig == 2) {
            // on va trouver les deux colonnes restantes (non exclues)
            List<Integer> inc = new ArrayList();
            for (int i = 0; i < colmax; i++) {
                if (!colexclues.contains(i)) {
                    inc.add(i);
                }
            }
            // inc size =2
            return finaldeterminant(matrice[lig][inc.get(0)], matrice[lig][inc.get(1)], matrice[lig + 1][inc.get(0)], matrice[lig + 1][inc.get(1)]);
        }
        double signe = 1;
        double determinant = 0;
        for (int i = coldebut; i < colfin; i++) {
            if (!colexclues.contains(i)) {
                List localList = new ArrayList();
                localList.addAll(colexclues);
                localList.add(i);
                double loc = matrice[lig][i] * determinant(lig + 1, coldebut, colfin, localList);
                determinant = determinant + (loc * signe);

                signe = -signe;
            }
        }
        return determinant;
    }

    /**
     * main call
     *
     * @return
     */
    public double determinant() {
        return determinant(0, 0, colmax, new ArrayList());
    }

    /**
     * @return the rowmax
     */
    public int getRowmax() {
        return rowmax;
    }

    /**
     * @param rowmax the rowmax to set
     */
    public void setRowmax(int rowmax) {
        this.rowmax = rowmax;
    }

    /**
     * @return the colmax
     */
    public int getColmax() {
        return colmax;
    }

    /**
     * @param colmax the colmax to set
     */
    public void setColmax(int colmax) {
        this.colmax = colmax;
    }
}
