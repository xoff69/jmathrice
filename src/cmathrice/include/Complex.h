#ifndef COMPLEX_H
#define COMPLEX_H

#include <ostream>
#include <list>
#include <iterator>
#include <vector>

class Complex
{
private:
    int a; // reelle
    int b; // imaginaire

public:
    Complex(int r = 0, int i =0);
    Complex operator + (Complex const &obj);
    Complex operator * (Complex const &obj);
    Complex operator * (int const scalar);

    float module();

    friend std::ostream& operator<<(std::ostream&, const Complex&);

};

#endif // COMPLEX_H
