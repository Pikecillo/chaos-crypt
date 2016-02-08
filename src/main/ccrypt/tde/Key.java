package ccrypt.tde;

import ccrypt.cmn.Matrix;
import ccrypt.cmn.Vector;

/**
 * Key for the Text Dependent Encryption method. Basically the aggregation
 * of a state vector and a coupling matrix of a coupled map network.
 */
public class Key{

    private Vector initialState;

    private Matrix couplingMatrix;

    /**
     * Create an instance of the key.
     *
     * @param state Vector representing the initial state of a coupled
     *        map network.
     * @param coupling Matrix representing the coupling of elements in
     *        a coupled map network. 
     */
    public Key(Vector state, Matrix coupling){

	initialState = state;
	couplingMatrix = coupling;
    }

    /**
     * Get the coupling matrix.
     *
     * @return The coupling matrix.
     */
    public Matrix getCoupling(){
	return couplingMatrix;
    }

    /**
     *	Get the initial state vector.
     *
     * @return The initial state vector.
     */
    public Vector getState(){
	return initialState;
    }
}
