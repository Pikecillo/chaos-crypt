package ccrypt.tde;

import java.io.InputStream;
import java.io.IOException;
import java.util.Vector;

/*
    Plaintext loader for the TDE
 */
public class TextLoader {

	byte text[];

	/**
	 * Create a text loader.
	 *
	 * @param input Text input stream from where to read.
	 */
	public TextLoader(InputStream input) throws IOException {
		int n;
		byte buffer[] = new byte[1024];

		Vector<Byte> vector = new Vector<Byte>();

		while((n = input.read(buffer)) != -1) {
			for(int i = 0 ; i < n ; i++) {
				vector.add(buffer[i]);
			}
		}

		text = new byte[vector.size()];

		for(int i = 0 ; i < vector.size() ; i++) {
			text[i] = vector.elementAt(i).byteValue();
		}
	}

	/**
	 * Get the text as an array of bytes.
	 *
	 * @return The text as an array of bytes.
	 */
	public byte [] getText(){

		return text;
	}
}
