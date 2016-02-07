package ccrypt.cmn;

/*
    Representation of a state for a coupled coupled map network
*/
public class NetworkState {

    // Size of the network
    private int size;
    // States
    private double state[];

    public NetworkState(int s){
	
	size = s;
	state = new double[size];

	initialize();
    }

    public NetworkState(double s[]){
	
	size = s.length;
	state = new double[size];

	for(int i = 0 ; i < size ; i++)
	    state[i] = s[i];
    }

    /* 
	Sets the value of the ith element in the network
    */
    public void setElement(int i, double v){
	
	state[i] = v;
    }

    /*
	Returns the value of the ith element of the network
    */
    public double getElement(int i){
	
	return state[i];
    }

    /*
	Returns the size of the network
    */
    public int getSize(){

	return size;
    }

    /*
	Initializes the element of the networks.
	This values are the ones used in the article describing the
	Text Dependent Encryption method.
	This initial values were arbitrary. The initial state of the network
	can be initialize to any value whitout affecting the effectiveness
	of the TDE.
    */
    public void initialize(){

	for(int i = 0 ; i < size ; i++)
	    state[i] = 1.0 + 0.1 * (i + 1);
    }
}
