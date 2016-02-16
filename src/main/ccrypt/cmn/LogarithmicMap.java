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
 * Logarithmic map: x(i+1) = b + ln(|x(i)|).
 * This map is chaotic with no periodic windows for b in the
 * interval (-1, 1).
 */
public class LogarithmicMap extends ChaoticMap {
    
    private double b;
    
    /**
     * Create and instance of the logarithmic map.
     *
     * @param p Translation parameter.
     */
    public LogarithmicMap(double p){
	b = p;
    }
    
    /**
     * Evaluate logarithmic map given the current state.
     *
     * @param x Current state.
     * @return Next state.
     */
    public double eval(double x){
	return b + Math.log(Math.abs(x));
    }
}
