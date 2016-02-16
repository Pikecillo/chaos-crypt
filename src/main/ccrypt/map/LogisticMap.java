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
 * Logistic map: x(i+1) = a * x(i) * (1 - x(i)).
 * This map is chaotic with periodic windows (i. e. for most values)
 * for a in the interval (3.57, 4).
 */
public class LogisticMap extends ChaoticMap {
    private double a;
    
    /**
     * Create and instance of the logistic map.
     *
     * @param p Scale parameter.
     */
    public LogisticMap(double p) {
	a = p;
    }
    
    /**
     * Evaluate logistic map given the current state.
     *
     * @param x Current state.
     * @return Next state.
     */
    public double eval(double x) {
	return a * x * (1 - x);
    }
}
