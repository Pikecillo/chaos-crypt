package cmn;

/*
    Representation of the coupling matrix for
    a coupled map network
*/
public class CouplingMatrix {

    // Size of the network
    private int size;
    // Array for the coupling coefficients
    private double matrix[][];

    /*
	This constructor is for creating instances of a
	coupling matrixes of size s, initialized to the
	values used on the article describing the
	Text Dependet Encryption method
    */
    public CouplingMatrix(int s){

	size = s;
	matrix = new double[size][size];

	initialize();
    }

    /*
	This constructor is for creating instances of
	a coupling matrix, whose coefficient are given
	by array m
    */
    public CouplingMatrix(double m[][]){

	size = m.length;
	matrix = new double[size][size];

	for(int i = 0 ; i < size ; i++)
	    for(int j = 0 ; j < size ; j++)
		matrix[i][j] = m[i][j];
    }

    /*
	Sets the element m_{i, j} of the coupling matrix to
	value v
    */
    public void setElement(int i, int j, double v){

	matrix[i][j]= v;
    }

    /*
	Returns the element m_{i, j} of the matrix
    */
    public double getElement(int i, int j){

	return matrix[i][j];
    }

    /*
	Returns the size of the matrix
    */
    public int getSize(){

	return size;
    }

    /*
	Initializes the coefficients to those
	of the article describing the Text Dependent Encryption 
    */
    public void initialize(){

	for(int i = 0 ; i < size ; i++)
	    for(int j = 0 ; j < size ; j++)
		matrix[i][j] = 0.01 * ((i + 1) - (double)(j + 1) / 2);
    }
}
