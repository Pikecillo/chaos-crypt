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

package ccrypt;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import javax.xml.bind.DatatypeConverter;

/**
 * Encode/decode an array of doubles to a hex string.
 */
public class HexStringCodec {

    /**
     * Convert an array of doubles to a hex string.
     *
     * @param doubles An array of doubles.
     * @return The hex string representation.
     */
    public static String toHexString(double doubles[]) {
	byte bytes[] = new byte[doubles.length << 3];
	ByteBuffer buffer = ByteBuffer.wrap(bytes);

	for(int i = 0; i < doubles.length; i++) {
	    buffer.putDouble(doubles[i]);
	}

	return DatatypeConverter.printHexBinary(bytes);
    }

    /**
     * Convert a hex string into an array of doubles.
     *
     * @param doubles An array of doubles.
     * @return The hex string representation.
     */
    public static double[] fromHexString(String hexString) {
	byte bytes[] = DatatypeConverter.parseHexBinary(hexString);
	double doubles[] = new double[bytes.length >> 3];

	ByteBuffer.wrap(bytes).asDoubleBuffer().get(doubles);

	return doubles;
    }
}
