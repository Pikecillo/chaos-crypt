import org.junit.Test;
import static org.junit.Assert.assertTrue;

import ccrypt.cmn.Matrix;

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
}
