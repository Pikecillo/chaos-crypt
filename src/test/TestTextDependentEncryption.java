import org.junit.Test;
import static org.junit.Assert.assertTrue;

import ccrypt.tde.Key;
import ccrypt.tde.TextDependentEncryption;
import ccrypt.cmn.Vector;
import ccrypt.cmn.Matrix;

public class TestTextDependentEncryption {
    
    private double initialState[] = {
	1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8
    }; 

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
    
    private Key key = new Key(new Vector(initialState),
			      new Matrix(couplingMatrix));

    @Test
    public void testDecryptEncrypt() {
	TextDependentEncryption tde = new TextDependentEncryption(key);

	// Create an array holding every possible 8-bit symbol

	byte text[] = new byte[12];

	for(int i = 0; i < text.length; i++)
	    text[i] = (byte)i;

	// Test that decryption is the inverse of encryption

	byte recovered_plaintext[] = tde.decrypt(tde.encrypt(text));
 
	for(int i = 0; i < text.length; i++) {
	    assertTrue(text[i] == recovered_plaintext[i]);
	}
    }

    @Test
    public void testEncryptDecrypt() {
	TextDependentEncryption tde = new TextDependentEncryption(key);

	// Create an array holding every possible 8-bit symbol

	byte text[] = new byte[12];

	for(int i = 0; i < text.length; i++)
	    text[i] = (byte)i;

	// Test that encryption is the inverse of decryption

	byte recovered_ciphertext[] = tde.encrypt(tde.decrypt(text));
 
	for(int i = 0; i < text.length; i++) {
	    assertTrue(text[i] == recovered_ciphertext[i]);
	}
    }
}
