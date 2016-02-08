package ccrypt.cmn;

/**
 * A network of coupled chaotic maps.
 */
public class CoupledMapNetwork {

    private Vector state;

    private Matrix coupling;
 
    private ChaoticMap localDynamic;

    /**
     * Create an instance of a couple map network given an initial state,
     * and a coupling matrix.
     *
     * @param s Initial state of the network.
     * @param c Coupling matrix of the network.
     */
    public CoupledMapNetwork(Vector s, Matrix c){
	setState(s);
	coupling = c;
    }

    /**
     * Set the chaotic map used by the network as local dynamic.
     *
     * @param map A given chaotic map.
     */
    public void setLocalDynamic(ChaoticMap map){
	localDynamic = map;
    }

    /**
     * Set the state of the network.
     * @param s A given state.
     */
    public void setState(Vector s){
	state = new Vector(s);
    }

    /**
     * Get the current state of the network.
     */
    public Vector getState(){
	return state;
    }

    /**
     * Perturbate externally the state of the network. The state of the
     * network is multiplied by a given factor.
     *
     * @param f Perturbation factor.
     */
    public void perturbState(double f){
	// Each element of the network is multiplied by a
	// perturbation factor
	for(int i = 0; i < state.getSize(); i++)
	    state.setElement(i, state.getElement(i) * f);
    }

    /**
     * Iterate the network one time step.
     */
    public void iterate() {
	// Compute result of global dynamic
	Vector global = coupling.mul(state);

	// Compute result of local dynamic
	Vector local = new Vector(global.getSize());

	for(int i = 0; i < state.getSize(); i++) {
	    local.setElement(i, localDynamic.eval(state.getElement(i)));
	}
	
	// The new state is the result of adding global and local
	// contributions
	state = local.add(global);
    }
}
