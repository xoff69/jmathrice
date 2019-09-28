#ifndef MATRIX_H
#define MATRIX_H

#include <ostream>
#include <list>
#include <iterator>
#include <vector>

class Matrix
{
private:
    std::string name;
    int ** matrice;
    int rowmax;
    int colmax;
    int finaldeterminant(int a, int b, int c, int d);
    int determinant(int lig, int coldebut, int colfin, std::vector <int> colexclues);

public:
    Matrix(std::string name,int nbrow,int nbcol);
    virtual ~Matrix();

    // fill matrix with random number
    void alea();
    //  true if matrix size is n*n
    bool isSquare();
    bool isSymetrique();
    int trace();
    Matrix operator + (Matrix const &other);
    Matrix operator * (Matrix const &other);
    Matrix operator * (int const scalar);
    Matrix transpose ();
    Matrix inverse();
    int determinant();

    friend std::ostream& operator<<(std::ostream&, const Matrix&);
protected:

};

#endif // MATRIX_H
