package cmn;

/*
    Representation of a coupled map network
*/
public class CoupledMapNetwork {

    // Size of the network
    private int size;
    // Initial state of the network
    private NetworkState initial;
    // Current state of the network
    private NetworkState current;
    // Last state of the network
    private NetworkState last;
    // Coupling matrix
    private CouplingMatrix coupling;
    // Local dynamic for elements in the network
    // it should be a chaotic one dimensional map
    private Maps.OneDimensionalMap localDynamic;

    /*
	For creating instances given an initial state and a coupling
	matrix
    */
    public CoupledMapNetwork (NetworkState initial, CouplingMatrix coupling){

	size = initial.getSize();
	this.initial = initial;
	this.coupling = coupling;
	last = new NetworkState(new double[size]);
	current = new NetworkState(new double[size]);
    
	loadInitialState();
    }

    /*
	Sets (resets) the coupled map network to its initial state
    */
    public void loadInitialState(){

	for(int i = 0 ; i < initial.getSize() ; i++)
	    current.setElement(i, initial.getElement(i));
    }

    /*
	Sets the local dynamic used by the network
    */
    public void setLocalDynamic(Maps.OneDimensionalMap map){

	localDynamic = map;
    }

    /*
	Sets the current network state
    */
    public void setState(NetworkState state){
	
	current = state;
    }

    /*
	Returns the current network state
    */
    public NetworkState getState(){

	return current;
    }

    /*
	Perturbate externally the state of the network
    */
    public void perturbState(double v){

	double e;

	// Each element of the network is multiplied by a
	// perturbation factor
	for(int i = 0 ; i < size ; i++)
	    current.setElement(i, current.getElement(i) * v);
    }

    /*
	Iterate the network one time step
    */
    public void iterate(){

	// Set current state as the previous
	for(int i = 0 ; i < size ; i++)
	    last.setElement(i, current.getElement(i));

	// Set the current value for each element
	// of the network
	for(int i = 0 ; i < size ; i++){
	    double sum = 0;

	    // Calculate global dynamic
	    for(int j = 0 ; j < size ; j++)
		sum += (coupling.getElement(i, j) * last.getElement(j));

	    // Calculate local dynamic
	    double x = localDynamic.function(last.getElement(i));

	    // Set the state of the element to the sum of both values
	    current.setElement(i, x + sum);
	}
    }
}
