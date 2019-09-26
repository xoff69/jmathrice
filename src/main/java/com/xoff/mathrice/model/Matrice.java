package com.xoff.mathrice.model;

import java.util.Random;

/**
 * @author xoffp
 * @description une matrice sur le type int premiere version que des entiers
 */
public class Matrice {

    /**
     * la matrice
     */
    private final int[][] matrice;
    private final int row;
    private final int col;

    /**
     * le constructeur
     *
     * @param row
     * @param col
     */
    public Matrice(int row, int col) {

        matrice = new int[row][col];
        this.row = row;
        this.col = col;
    }

    /**
     * vrai si la matrice est carree
     *
     * @return
     */
    private boolean matricecarree() {
        return row == col;
    }

    /**
     * calcule le determinant de la matrice
     *
     * @return
     */
    public int determinant() {
        assert (matricecarree());
        int determinant = 0;
        
        // on fait les positifs
        for (int j = 0; j < col; j++) {
            int i = j;
            int produitdiag = 1;
            while (i < row) {
                produitdiag *= matrice[i][j];
                i++;
            }
            determinant+=produitdiag;
        }
        return determinant;

    }

    public Matrice produit(Matrice autre) {
        //@TODO + condition du produit this.ligne=autre.col
        return autre;
    }

    /**
     * calcule le trace de la matrice
     *
     * @return
     */
    public int trace() {
        assert (matricecarree());
        int trace = 0;
        int i = 0;
        // on a row=col
        while (i < row) {
            trace += matrice[i][i];
            i++;
        }
        return trace;
    }

    /**
     * remplit la matrice aleatoirement
     */
    public void alea() {
        Random r = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrice[i][j] = r.nextInt();
            }
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res.append(matrice[i][j]).append(" ");

            }
            res.append("\n");
        }

        return res.toString();
    }
}
