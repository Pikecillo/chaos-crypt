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

package ccrypt.tde;

import java.security.SecureRandom;
import ccrypt.cmn.Vector;
import ccrypt.cmn.Matrix;

/**
 * Generator of random TDE keys.
 */
public class KeyGenerator {
    
    /**
     * Creates a randomly generated TDE key. All elements
     * of the initial state and coupling are uniformly distributed
     * between 0.0 and 1.0.
     *
     * @return Random TDE key.
     */
    public static Key create() {
	int keySize = (8 + 64) * 8; // 576 bytes
	byte randomBytes[] = new byte[keySize];
	SecureRandom secureRandom = new SecureRandom();

	Vector state = new Vector(KeyGenerator.randomDoubles(8));
	Matrix coupling = new Matrix(KeyGenerator.randomDoubles(64), 8);

	return new Key(state, coupling);
    }

    /**
     * Create an array of random doubles
     *
     * @param size Number of doubles to generate.
     * @return An array of random doubles.
     */
    private static double[] randomDoubles(int size) {
	double array[] = new double[size];
	SecureRandom secureRandom = new SecureRandom();

	for(int i = 0; i < size; i++) {
	    array[i] = secureRandom.nextDouble();
	}

	return array;
    }
}
