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

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import ccrypt.cipher.TextDependentKey;
import ccrypt.cipher.TextDependentCipher;
import ccrypt.map.Vector;
import ccrypt.map.Matrix;

public class TestTextDependentCipher {

    private double initialState[] = 
        { 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8 }; 

    private double couplingMatrix[][] = {
            { 0.0050, 0.0, -0.0050, -0.01, -0.015, -0.02, -0.025, -0.03 }, 
            { 0.015, 0.01, 0.0050, 0.0, -0.0050, -0.01, -0.015, -0.02 },
            { 0.025, 0.02, 0.015, 0.01, 0.0050, 0.0, -0.0050, -0.01 },
            { 0.035, 0.03, 0.025, 0.02, 0.015, 0.01, 0.0050, 0.0 },
            { 0.045, 0.04, 0.035, 0.03, 0.025, 0.02, 0.015, 0.01 },
            { 0.055, 0.05, 0.045, 0.04, 0.035, 0.03, 0.025, 0.02 },
            { 0.065, 0.06, 0.055, 0.05, 0.045, 0.04, 0.035, 0.03 },
            { 0.075, 0.07, 0.065, 0.06, 0.055, 0.05, 0.045, 0.04 }
    };

    private TextDependentKey key = new TextDependentKey(
            new Vector(initialState),
            new Matrix(couplingMatrix));

    @Test
    public void testDecryptEncrypt() {
        TextDependentCipher tde = new TextDependentCipher(key);

        // Create an array holding every possible 8-bit symbol

        byte text[] = new byte[256];

        for(int i = 0; i < text.length; i++)
            text[i] = (byte)i;

        // Test that decryption is the inverse of encryption

        byte recovered_plaintext[] = tde.decrypt(tde.encrypt(text));

        for(int i = 0; i < text.length; i++) {
            assertTrue(text[i] == recovered_plaintext[i]);
        }

        // Test the same with state perturbation option

        tde = new TextDependentCipher(key, true);

        recovered_plaintext = tde.decrypt(tde.encrypt(text));

        for(int i = 0; i < text.length; i++) {
            assertTrue(text[i] == recovered_plaintext[i]);
        }
    }
}
