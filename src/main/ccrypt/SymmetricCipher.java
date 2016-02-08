package ccrypt;

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
