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

package ccrypt;

import java.security.SecureRandom;

/**
 * Secure random number generation functions.
 */
public class Random {
    
    /**
     * Create an array of random doubles
     *
     * @param size Number of doubles to generate.
     * @return An array of random doubles.
     */
    public static double[] randomDoubles(int size) {
        double array[] = new double[size];
        SecureRandom secureRandom = new SecureRandom();
	
        for(int i = 0; i < size; i++) {
            // Note with larger values (even [-1.0, 0.0]) floating
            // point arithmetic overflows.
            array[i] = 0.1 * secureRandom.nextDouble() - 0.05;
        }
	
        return array;
    }
}
