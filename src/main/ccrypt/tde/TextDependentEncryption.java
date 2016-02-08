package ccrypt.tde;

import ccrypt.SymmetricCipher;
import ccrypt.cmn.CoupledMapNetwork;
import ccrypt.cmn.ChaoticMap;
import ccrypt.cmn.Maps;
import ccrypt.cmn.Vector;

/**
 * Class for performing Text Depenent Encryption
 */
public class TextDependentEncryption implements SymmetricCipher {

    // If set to true dynamic of the coupled map network
    // should be perturbated at each time step
    private boolean perturb;
    // Coupled map network
    private CoupledMapNetwork cmn;
    // Text Dependent Encryption secret key
    private Key key;

    /**
     * Creates an and instance for TDE encryption/decryption
     * using a given key k
     */
    public TextDependentEncryption(Key k) {
	this(k, false);
    }

    /**
     * Creates and instance for TDE encryption/decryption using a given key k.
     * If the argument perturb is set to true then the coupled map network
     * dynamic should be perturbed externally.
     * By default the local dynamics is given by a logarithmic map.
     */
    public TextDependentEncryption(Key k, boolean perturb) {
	this(k, perturb, new Maps.Logarithmic(0.5));
    }

    /**
     * Creates and instance for TDE encryption/decryption
     * using a given key k, and allow to set the local dynamic
     * If the argument perturb is set to true then
     * the coupled map network dynamic should be perturbed
     * externally.
     */
    public TextDependentEncryption(Key k, boolean perturb, ChaoticMap map) {
	key = k;
	this.perturb = perturb;
	// Set the coupled map network from the key
	// The key for TDE is the initial state of a coupled map
	// network and the coefficients of a coupling matrix
	cmn = new CoupledMapNetwork(k.getState(), k.getCoupling());
	cmn.setLocalDynamic(map);
    }

    /**
     * Perform encryption. The resulting ciphertext from the encryption
     * method is a sequence of bytes, twice as large as the plaintext.
     * Each pair of them represents the number of iterations until
     * the corresponding symbol from the plaintext is generated by
     * the coupled map networks dynamics.
     *
     * @param plaintext An array containing the plaintext as a sequence
     *                  of bytes, and the output is an array containing.
     * @return The ciphertext. 
     */
    public byte[] encrypt(byte plaintext[]) {

	int i = 0, iterations = 0;
	byte ciphertext[] = new byte[plaintext.length << 1];

	// Set initial state for coupled map network from key
	cmn.setState(key.getState());

	// Start generating symbols with the coupled map network
	while(i < plaintext.length) {
	    cmn.iterate();
	    iterations++;
	    
            // If the generated symbol corresponds to the current
	    // plaintext symbol
	    if(binaryState(cmn.getState()) == plaintext[i]) {
		// Store the encoding integer in the ciphertext integer
		// sequence
		ciphertext[i << 1] = (byte)(iterations & 0xFFFF);
		ciphertext[(i << 1) + 1] = (byte)(iterations >> 8);

		iterations = 0;
		i++;

		// If the coupled map network should be
		// externally perturbated then do it
		// The authors of TDE recommend using a perturbation
		// factor of -1
		if(perturb) cmn.perturbState(-1.0);
	    }
	}

	System.out.println("---");
	for(i = 0; i < ciphertext.length; i++)
	    System.out.println("C " + ciphertext[i]);

	return ciphertext;
    }

    /**
     *	Perform decryption. This is basically the same
     */
    public byte [] decrypt(byte ciphertext[]) {
	
	byte plaintext[] = new byte[ciphertext.length >> 1];

	// Load the initial state of the coupled map network
	cmn.setState(key.getState());

	// For each integer on the ciphertext sequence
	for(int i = 0 ; i < ciphertext.length ; i += 2) {
	    // Iterate the coupled map network for
	    // the given number of states

	    int iterations = 0;
	    iterations |= (ciphertext[i] & 0xFF);
	    iterations |= ((ciphertext[i + 1] & 0xFF) << 8);

	    for(int j = 1 ; j <= iterations ; j++)
		cmn.iterate();

	    // Convert the network state to a byte
	    plaintext[i >> 1] = binaryState(cmn.getState());

	    // If network has to be perturbated then do it
	    if(perturb) cmn.perturbState(-1.0);
	}

	System.out.println("---");
	for(int i = 0; i < plaintext.length; i++)
	    System.out.println("P " + plaintext[i]);

	return plaintext;
    }

    /**
     * Converts the state of a coupled map network to a byte.
     *
     * @param state A vector state.
     * @return A byte obtained from the state.
     */
    private byte binaryState(Vector state) {
	byte c = 0x0;
	
	// For each element of the network
	for(int i = 0 ; i < state.getSize(); i++) {

	    // If the ith element is positive set the ith element of
            // the byte to 1, otherwise keep it as 0.
	    // This threshold can be any value if the local dynamic
            // is given by a logarithmic map.
	    // For other maps it is not necessary 0, but the behaviour
            // of the map should be taken into account or there might
            // be symbols forbidden by the dynamics of the coupled map
            // network.
	    // The authors of the TDE used this threshold
	    if(state.getElement(i) > 0.0)
		c |= (1 << i);
	}

	return c;
    }
}
