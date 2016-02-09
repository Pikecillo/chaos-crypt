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
 * A pretty basic square matrix class.
 */
public class Matrix {
    
    private int size;
    
    private double elements[];
    
    /**
     * This constructor is for creating instances of a
     * coupling matrixes of size s, initialized to the
     * values used on the article describing the
     * Text Dependent Encryption method.
     *
     * @param s Size of the square matrix.
     */
    public Matrix(int s) {
	size = s;
	elements = new double[size * size];
	
	initialize();
    }
    
    /**
     * This constructor is for creating instances of
     * a coupling matrix, whose coefficient are given
     * by array m.
     *
     * @param m Square elements of coefficients.
     */
    public Matrix(double m[][]) {
	size = m.length;
	elements = new double[size * size];
	
	for(int i = 0 ; i < size ; i++)
	    for(int j = 0 ; j < size ; j++)
		elements[i * size + j] = m[i][j];
    }
    
    /**
     * Multiply the matrix with a given vector.
     *
     * @param v A given vector.
     * @return The result of the multiplication.
     */
    public Vector mul(Vector v) {
	Vector res = new Vector(v.getSize());
	
	for(int i = 0; i < size; i++) {
	    double dot = 0.0;
	    
	    for(int j = 0; j < size; j++) {
		dot += elements[i * size + j] * v.getElement(j);
	    }
	    
	    res.setElement(i, dot);
	}
	
	return res;
    }
    
    /**
     * Set an element of the matrix to a given value.
     *
     * @param i Row index of element.
     * @param j Column index of element.
     * @param v Value of element.
     */
    public void setElement(int i, int j, double v) {
	elements[i * size + j]= v;
    }
    
    /**
     * Get element of the matrix.
     *
     * @param i Row index of element.
     * @param j Column index of element.
     * @return Value of element.
     */
    public double getElement(int i, int j) {
	return elements[i * size + j];
    }
    
    /**
     * Get the size of the matrix.
     *
     * @return The size of the matrix.
     */
    public int getSize() {
	return size;
    }
    
    /**
     *	Initializes the coefficients to those
     *	of the article describing the Text Dependent Encryption.
     */
    private void initialize() {
	
	for(int i = 0 ; i < size ; i++)
	    for(int j = 0 ; j < size ; j++)
		elements[i * size + j] =
		    0.01 * ((i + 1) - (double)(j + 1) / 2);
    }
}
