package ccrypt.tde;

import ccrypt.cmn.CouplingMatrix;
import ccrypt.cmn.NetworkState;

/*
    Key for the Text Encryption Method
*/
public class Key{

    // Initial state of a coupled map network
    private NetworkState initialState;
    // Coupling matrix for a coupled map network
    private CouplingMatrix couplingMatrix;

    /*
	For creating and instance of a key
    */
    public Key(NetworkState initial, CouplingMatrix coupling){

	initialState = initial;
	couplingMatrix = coupling;
    }

    /*
	Returns the coupling matrix associated with the key
    */
    public CouplingMatrix getCoupling(){
	return couplingMatrix;
    }

    /*
	Returns the initial state associated with the key
    */
    public NetworkState getState(){
	return initialState;
    }
}
