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

import ccrypt.cmn.Matrix;
import ccrypt.cmn.Vector;

public class TestMatrix {

    @Test
    public void testGetSize() {
        int size = 10;
        Matrix m = new Matrix(size);

        assertTrue(size == m.getSize());

        size = 20;
        m = new Matrix(new double[size][size]);

        assertTrue(size == m.getSize());
    }

    @Test
    public void testGetSetElement() {
        double matrix[][] = {
                { 1.1, 1.2, 1.3 },
                { 2.1, 2.2, 2.3 },
                { 3.1, 3.2, 3.3 }
        };

        Matrix m = new Matrix(matrix);

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                assertTrue(matrix[i][j] == m.getElement(i, j));

                double val = i * 0.1 + j * 0.1;
                m.setElement(i, j, val);

                assertTrue(m.getElement(i, j) == val);
            }
        }
    }

    @Test
    public void testMul() {
        double matrix[][] = {
                { 1.0, 0.0, 0.0 },
                { 0.0, 1.0, 0.0 },
                { 0.0, 0.0, 1.0 }
        };
        double vector[] = { 17.0, 19.0, 18.0 };

        Vector v = new Vector(vector);
        Matrix m = new Matrix(matrix);

        Vector r = m.mul(v);

        for(int i = 0; i < r.getSize(); i++) {
            assertTrue(r.getElement(i) == v.getElement(i));
        }
    }
}
