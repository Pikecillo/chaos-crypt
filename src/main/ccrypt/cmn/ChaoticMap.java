package ccrypt.cmn;

/**
 * Interface for unidimensional chaotic maps.
 */
public interface ChaoticMap {
    
    /**
     * The implementation of this method should provide the state
     * of the map for the subsequent time step.
     *
     * @param x Current state of the chaotic map.
     * @return State of the map on the subsequent time step.
     */
    public double eval(double x);
}
