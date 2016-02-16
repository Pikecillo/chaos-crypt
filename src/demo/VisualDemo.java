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

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;
import java.awt.image.DataBufferByte;

import ccrypt.cipher.TextDependentKey;
import ccrypt.cipher.TextDependentCipher;

public class VisualDemo {

    public static void main(String args[]) {
	(new VisualDemo()).run();
    }

    private void run() {
	BufferedImage img = null;
	String filename = "../../res/img/watercolor.jpg";

	try {
	    img = ImageIO.read(new File(filename));
	} catch(IOException e) {
	    System.out.println("Error reading image");
	    System.exit(1);
	}

	WritableRaster raster = img.getRaster();
	DataBufferByte dataBuffer = (DataBufferByte)raster.getDataBuffer();
	byte plaintext[] = dataBuffer.getData();
	    
	// Create a key
	TextDependentKey key = TextDependentKey.create();
	
	// Encrypt using the first one
	TextDependentCipher tde = new TextDependentCipher(key);
	byte ciphertext[] = tde.encrypt(plaintext);
	    
	// Change the key slightly
	double element = key.getState().getElement(0);
	key.getState().setElement(0, element + 1e-10);

	// Decrypt using the tampered key
	tde = new TextDependentCipher(key);
	byte recovered[] = tde.decrypt(ciphertext);
	    
	int intData[] = new int[recovered.length];
	    
	for(int i = 0; i < intData.length; i++)
	    intData[i] = recovered[i];
	    
	raster.setPixels(0, 0, img.getWidth(), img.getHeight(), intData);
	img.setData(raster);
	   
	// Save image recovered with wrong key
	try {
	    File outputfile = new File("tampered-key.jpg");
	    ImageIO.write(img, "png", outputfile);
	} catch (IOException e) {
	    System.err.println("Error reading image");
	}
    }
}
