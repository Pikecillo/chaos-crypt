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

    private static SecureRandom secureRandom = new SecureRandom();

    /**
     * Get a random double uniformly distributed within a given interval.
     *
     * @return A random double in the given interval
     */
    public static double randomDouble(double lo, double hi) {
	if(lo > hi) {
	    double temp = hi;
	    hi = lo;
	    lo = temp;
	}

	return (hi - lo) * secureRandom.nextDouble() + lo;
    }

    /**
     * Create an array of random doubles uniformly distributes within a
     * given interval.
     *
     * @param size Number of doubles to generate.
     * @param lo Lower bound of the interval.
     * @param hi Higher bound of the interval.
     * @return An array of random doubles.
     */
    public static double[] randomDoubles(int size, double lo, double hi) {
        double array[] = new double[size];
	
	if(lo > hi) {
	    double temp = hi;
	    hi = lo;
	    lo = temp;
	}

        for(int i = 0; i < size; i++) {
            array[i] = randomDouble(lo, hi);
        }
	
        return array;
    }

    /**
     * Randomly shuffle elements of a given array.
     *
     * @param array The given array.
     */
    public static <T> void shuffle(T array[]) {

	for(int i = array.length - 1; i > 0; i--) {
	    int j = secureRandom.nextInt(i + 1);
	    T temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}
    }
}
