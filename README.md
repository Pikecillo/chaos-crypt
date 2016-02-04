# chaos-crypt
Encryption through coupled map networks

Together with this README is the source code for an implementation
of an encryption scheme called Text Dependent Encryption.

The application was developed in the Java programming language

TDE usage: java TDE mode key input output [perturbate]
        mode --> (e | d)
        key  --> path to key file
                e: encrypt
                d: decrypt
        input --> full path to input plaintext/ciphertext file
        output --> full path to output ciphertext/plaintext file
        perturbate (optional) --> 1

Note: if encryption is performed with the perturbate
option on, then decryption must be performed with the
perturbation option on as well. Keys for the TDE consists of 72 floating point
numbers.

Directory structure:

+ TDE:

  - README.md

  - TDE.java: Main class

  - TDE.class

  + cmn: Classes implementing chaotic coupled map networks

  + tde: Classes implementing the Text Dependent Encryption Scheme

  + sample_keys: contains two files with sample keys. This two keys
    		 appear to be identical but due the nature of
		 the encryption method they provide very different results

  + sample_plaintext: contains a text file and a pdf file to be used
    		      as plaintext for the application

  + sample_ciphertext: contains ciphertext files for the provided plaintext
    		       Note: the ciphertext was obtained by using KEY1 and
		       using letting of the perturbate option of encryption

All the details about the Text Dependent Encryption scheme are provided in the
pdf file in the directory sample_plaintext. That file is the article in which
it was published
		       
Examples of usage:

$ java  TDE  e  sample_keys/KEY1  sample_plaintext/19_CMN-Comm\(R\).pdf  sample_ciphertext/19_CMN-Comm\(R\).pdf.enc

$ java  TDE  e  sample_keys/KEY1  sample_plaintext/the_art_of_war_by_sun_tzu.txt  sample_ciphertext/the_art_of_war_by_sun_tzu.txt.enc

$ java  TDE  d  sample_keys/KEY1  sample_ciphertext/19_CMN-Comm\(R\).pdf.enc  article.pdf

$ java  TDE  d  sample_keys/KEY1  sample_ciphertext/the_art_of_war_by_sun_tzu.txt.enc art_of_war.txt

Please report any bugs to: mario.rincon.nigro@gmail.com
