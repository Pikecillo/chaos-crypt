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

package ccrypt.map;

/**
 * Abstract class for unidimensional chaotic maps.
 */
public abstract class ChaoticMap {

    /**
     * The implementation of this method should provide the state
     * of the map for the subsequent time step.
     *
     * @param x Current state of the chaotic map.
     * @return State of the map on the subsequent time step.
     */
    public abstract double eval(double x);

    /**
     * Iterate the chaotic map a given number of times;
     *
     * @param x Current state of the chaotic map.
     * @param it Number of iterations.
     * @return State of the map after the given number of iterations.
     */
    public double iterate(double x, int it) {
	for(int i = 0; i < it; i++)
	    x = eval(x);

	return x;
    }
}
