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

package ccrypt.cipher;

import ccrypt.HexStringCodec;
import ccrypt.Random;
import ccrypt.map.Matrix;
import ccrypt.map.Vector;

/**
 * Key for the Text Dependent Encryption method. Basically the aggregation
 * of an initial state vector and the coupling matrix of a coupled map network.
 */
public class TextDependentKey {

    private Vector initialState;

    private Matrix couplingMatrix;

    /**
     * Creates a randomly generated TDE key. All elements
     * of the initial state and coupling are uniformly distributed
     * between -0.05 and 0.05.
     *
     * @return Random TDE key.
     */
    public static TextDependentKey create() {
        Vector state = new Vector(Random.randomDoubles(8));
        Matrix coupling = new Matrix(Random.randomDoubles(64), 8);

        return new TextDependentKey(state, coupling);
    }

    /**
     * Create an instance of the key.
     *
     * @param state Vector representing the initial state of a coupled
     *        map network.
     * @param coupling Matrix representing the coupling of elements in
     *        a coupled map network. 
     */
    public TextDependentKey(Vector state, Matrix coupling) {
        initialState = state;
        couplingMatrix = coupling;
    }

    /**
     * Create a key from a hex string representation.
     *
     * @param keyHexString A hex string representation of a key.
     */
    public TextDependentKey(String keyHexString) {
        int size = 8;
        int stateLength = size * 16; // Amount of hex digits for state
        String state = keyHexString.substring(0, stateLength);
        String coupling = keyHexString.substring(stateLength);

        initialState = new Vector(HexStringCodec.fromHexString(state));
        couplingMatrix = new Matrix(HexStringCodec.fromHexString(coupling),
                size);
    }

    /**
     * Convert the key into a hex string.
     *
     * @return Hex string representation of the key.
     */
    public String toString() {
        return HexStringCodec.toHexString(initialState.getElements()) +
                HexStringCodec.toHexString(couplingMatrix.getElements());
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
