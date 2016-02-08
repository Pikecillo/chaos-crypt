package ccrypt.cmn;

/**
 * A pretty basic vector of doubles
 */
public class Vector {

    private double elements[];
    
    public Vector(int size){
	elements = new double[size];
	
	initialize();
    }
    
    public Vector(double s[]) {
	elements = new double[elements.length];

	for(int i = 0; i < elements.length; i++)
	    elements[i] = s[i];
    }
    
    /**
     * Addition with a given vector
     *
     * @param v A given vector
     * @return The result of the addition
     */
    public Vector add(Vector v) {
	Vector res = new Vector(v.getSize());

	for(int i = 0; i < elements.length; i++)
	    res.elements[i] = elements[i] + v.elements[i];

	return res;
    }

    /** 
     * Set the value of an element of the vector given its index.
     *
     * @param i Index of element.
     * @param v New value of the element.
     */
    public void setElement(int i, double v) {
	elements[i] = v;
    }

    /**
     * Get value of an element of the vector given its index.
     *
     * @param i Index of element.
     * @return Value of element.
     */
    public double getElement(int i) {	
	return elements[i];
    }

    /**
     * Get elements of the vector as an array of doubles.
     *
     * @return Array with elements of the vector.
     */
    public double[] getElements() {
	return elements;
    }

    /**
     * Get the size of the vector.
     *
     * @return The size of the vector.
     */
    public int getSize(){
	return elements.length;
    }

    /**
     * Initializes the element of the networks.
     * This values are the ones used in the article describing the
     * Text Dependent Encryption method.
     * This initial values were arbitrary. The initial state of the network
     * can be initialize to any value whitout affecting the effectiveness
     * of the TDE.
     */
    private void initialize(){

	for(int i = 0 ; i < elements.length ; i++)
	    elements[i] = 1.0 + 0.1 * (i + 1);
    }
}
