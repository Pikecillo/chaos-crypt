/*======================================================================

 Copyright (C) 2009-2015. Mario Rincon-Nigro.

 This file is a part of Chaos-Crypt.

 This is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Chaos-Crypt is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Chaos-Crypt.  If not, see <http://www.gnu.org/licenses/>.

======================================================================*/

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

    /**
     * Iterate the network for a given number of time steps.
     *
     * @param it A given number of steps.
     */
    public void iterate(int it) {
	for(int i = 0; i < it; i++) {
	    iterate();
	}
    }
}
