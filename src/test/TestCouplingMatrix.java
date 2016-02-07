import org.junit.Test;
import static org.junit.Assert.assertTrue;

import ccrypt.cmn.CouplingMatrix;

public class TestCouplingMatrix {

    @Test
    public void testGetSize() {
	int size = 10;
	CouplingMatrix cm = new CouplingMatrix(size);

	assertTrue(size == cm.getSize());

	size = 20;
	cm = new CouplingMatrix(new double[size][size]);

	assertTrue(size == cm.getSize());
    }

    @Test
    public void testGetSetElement() {
	double matrix[][] = {
	    { 1.1, 1.2, 1.3 },
	    { 2.1, 2.2, 2.3 },
	    { 3.1, 3.2, 3.3 }
	};

	CouplingMatrix cm = new CouplingMatrix(matrix);

	for(int i = 0; i < matrix.length; i++) {
	    for(int j = 0; j < matrix[i].length; j++) {
		assertTrue(matrix[i][j] == cm.getElement(i, j));
	    }
	}

	cm.setElement(0, 0, 0.1);

	assertTrue(cm.getElement(0, 0) == 0.1);
    }
}
