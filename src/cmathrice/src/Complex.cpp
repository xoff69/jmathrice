#include "Complex.h"
#include <iostream>
#include <stdio.h>      /* printf */
#include <math.h>
#include <stdlib.h>
#include <time.h>
#include <cassert>
#include <ostream>
#include <sstream>
#include <list>
#include <vector>
#include <algorithm>

using namespace std;

std::ostream& operator<<(std::ostream &strm, const Complex &complex)
{
    strm<<complex.a<<" "<<complex.b<<"i"<<endl;
    return strm;
}
Complex::Complex(int r, int i )
{
    a = r;
    b = i;
}
Complex Complex::operator + (Complex const &obj)
{
    Complex res;
    res.a = a + obj.a;
    res.b = b + obj.b;
    return res;
}
Complex Complex::operator * (Complex const &obj)
{
    Complex res;
    res.a = a * obj.a-b * obj.b;
    res.b = a* obj.b +b*obj.a;
    return res;
}


float Complex::module()
{
    return sqrt(pow(a,2)+pow(b,2));
}
