package ccrypt.cmn;

/**
 * Coupling matrix for a coupled map network.
 */
public class CouplingMatrix {

    // Size of the network
    private int size;
    // Array for the coupling coefficients
    private double matrix[];

    /**
     * This constructor is for creating instances of a
     * coupling matrixes of size s, initialized to the
     * values used on the article describing the
     * Text Dependet Encryption method.
     *
     * @param s Size of the square matrix.
     */
    public CouplingMatrix(int s){

	size = s;
	matrix = new double[size * size];

	initialize();
    }

    /**
     * This constructor is for creating instances of
     * a coupling matrix, whose coefficient are given
     * by array m.
     *
     * @param m Square matrix of coefficients.
     */
    public CouplingMatrix(double m[][]){

	size = m.length;
	matrix = new double[size * size];

	for(int i = 0 ; i < size ; i++)
	    for(int j = 0 ; j < size ; j++)
		matrix[i * size + j] = m[i][j];
    }

    /**
     * Set the element m_{i, j} of the coupling matrix to
     * a given value.
     *
     * @param i Row index of element.
     * @param j Column index of element.
     * @param v Element value.
     */
    public void setElement(int i, int j, double v){

	matrix[i * size + j]= v;
    }

    /**
     * Get value of element (i, j) of the matrix.
     *
     * @param i Row index of element.
     * @param j Column index of element.
     * @return Value of element.
     */
    public double getElement(int i, int j){

	return matrix[i * size + j];
    }

    /**
     * Get the size of the matrix
     */
    public int getSize(){

	return size;
    }

    /**
     *	Initializes the coefficients to those
     *	of the article describing the Text Dependent Encryption.
     */
    private void initialize(){

	for(int i = 0 ; i < size ; i++)
	    for(int j = 0 ; j < size ; j++)
		matrix[i * size + j] = 0.01 * ((i + 1) - (double)(j + 1) / 2);
    }
}
