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

package ccrypt.cipher;

/**
 * A symmetric cipher interface
 */
public interface SymmetricCipher {

    /**
     * This method should implement encryption. This method should
     * be the inverse of decrypt.
     *
     * @param plaintext The plaintext.
     * @return The corresponding ciphertex.
     */
    public byte[] encrypt(byte plaintext[]);

    /**
     * This method should implement decryption. This method should
     * be the inverse of encrypt.
     *
     * @param ciphertext The ciphertext.
     * @return The corresponding plaintext.
     */
    public byte[] decrypt(byte ciphertext[]);
}
