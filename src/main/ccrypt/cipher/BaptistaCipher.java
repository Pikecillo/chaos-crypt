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

import ccrypt.map.ChaoticMap;
import ccrypt.map.LogisticMap;

/**
 * Baptista encryption, as described in Cryptography with Chaos.
 */
public class BaptistaCipher implements SymmetricCipher {

    private BaptistaKey key;

    BaptistaCipher(BaptistaKey k) {
	key = k;
    }

    public byte[] encrypt(byte plaintext[]) {
	byte ciphertext[] = new byte[plaintext.length << 1];
	//ChaoticMap map = new LogisticMap(3.9);
	
	

	return ciphertext;
    }

    public byte[] decrypt(byte ciphertext[]) {
	byte plaintext[] = new byte[ciphertext.length >> 1];
	/*ChaoticMap map = new LogisticMap(3.9);
	double state = key.initialState;

	for(int i = 0; i < ciphertext.length; i += 2) {
	    int lower = ciphertext[i] & 0xFF;
            int upper = ciphertext[i + 1] & 0xFF;
            int iterations = (upper << 8) | lower;

	    // Iterate the coupled map network for
            // the given number of states
	    state = map.iterate(state, iterations);

            // Convert the network state to a byte
            plaintext[i >> 1] = stateSite(state);
	    }*/

	return plaintext;
    }

    private byte stateSite(double state) {
	//return key.shuffle[0];
	return 0;
    }
}
