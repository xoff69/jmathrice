#include <iostream>
#include "Complex.h"
#include "Matrix.h"
using namespace std;



int main()
{
    /*
        cout << "Hello world!" << endl;

        Complex c1(10, 5), c2(2, 4);
        Complex c4(5,6);
        Complex c3 = c1 + c2;
        c3.print();
        cout<<"module:"<<c3.module()<<endl;
    */
    Matrix m("M1",3,3);
    m.alea();
    cout<<"carree?"<<m.isSquare()<<"symetrique?"<<m.isSymetrique()<<endl;
    cout<<"trace:"<<m.trace()<<endl;
    cout<<m<<endl;


    cout<<"determinant:"<<m.determinant()<<endl;
    cout<<m.inverse()<<endl;
    return 0;
}
