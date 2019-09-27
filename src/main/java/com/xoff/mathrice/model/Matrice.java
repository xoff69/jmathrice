package com.xoff.mathrice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xoffp
 * @description simple integer matrix
 */
public class Matrice {

    private static final int MODULO_MAX = 7;

    /**
     * la matrice
     */
    private final int[][] matrice;
    private int rowmax;
    private int colmax;

    /**
     * le constructor
     *
     * @param row
     * @param col
     */
    public Matrice(int rowmax, int colmax) {

        matrice = new int[rowmax][colmax];
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
     * @param autre
     * @return 
     */
    public Matrice product(Matrice autre) {
        //@TODO + condition du produit this.ligne=autre.col
        return autre;
    }
    /**
     * M^-1
     * @return 
     */
    public Matrice inverse(){
        return this;
    }
    /**
     * tM
     * @return 
     */
    public Matrice transpose(){
        return this;
    }
    /**
     * matrice trace
     *
     * @return
     */
    public int trace() {
        assert (matricecarree());
        int trace = 0;
        int i = 0;
        // on a rowmax=col
        while (i < rowmax) {
            trace += matrice[i][i];
            i++;
        }
        return trace;
    }

    /**
     * random fill the matrix modulo MODULO_MAX
     */
    public void alea() {
        Random r = new Random();
        for (int i = 0; i < rowmax; i++) {
            for (int j = 0; j < colmax; j++) {
                matrice[i][j] = r.nextInt() % MODULO_MAX;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rowmax; i++) {
            for (int j = 0; j < colmax; j++) {
                res.append(matrice[i][j]).append(" ");

            }
            res.append("\n");
        }

        return res.toString();
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
    private int finaldeterminant(int a, int b, int c, int d) {
        //   System.out.println(a+" "+b+"\n"+c+" "+d+"\n");
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
    private int determinant(int lig, int coldebut, int colfin, List<Integer> colexclues) {
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
        int signe = 1;
        int determinant = 0;
        for (int i = coldebut; i < colfin; i++) {
            if (!colexclues.contains(i)) {
                List localList = new ArrayList();
                localList.addAll(colexclues);
                localList.add(i);
                int loc = matrice[lig][i] * determinant(lig + 1, coldebut, colfin, localList);
                determinant = determinant + (loc * signe);

                signe = -signe;
            }
        }
        return determinant;
    }
    /**
     * main call
     * @return 
     */
    public int determinant() {
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
