# ChaosCrypt
This repo contains **ccrypt**, a Java implementation of the private key
cryptosystem described in ["Coupled Map Networks as Communication Schemes"]
(http://arxiv.org/abs/nlin/0201042)(1). Super simple, and presumably strong
until shown otherwise, they call it *Text Dependent Encryption*.

If you want to try it out, you can go to folder ccrypt-dist and run
the *ccrypt* shell script.

To generate a key into the current folder

    $ sh ccrypt g key.tde

To encrypt the ccrypt script into file ccrypt.enc

    $ sh ccrypt e key.tde ccrypt ccrypt.enc

To decrypt it run

    $ sh ccrypt d key.tde ccrypt.enc ccrypt.dec

A copy of Sun Tzu's The Art of War, and a copy of the original paper where
the method is described, can be found (encrypted) in folder
res/cipher-text. To decrypt them use key res/keys/key1 as well (or was it key2?).

### To anyone who might want to use **ccrypt** for anything other than mere intellectual curiosity

- Use it for a good purpose. Make ya momma proud.
- I would bet you (unless you are [Bruce Schneier]
(https://www.schneierfacts.com/)) 50 bucks you cannot break the encryption.
However, I don't know about any thorough study on the strength of the
cryptosystem, so that's something to keep in mind.
- Last but not least, some caveats:
    - The generated ciphertext is twice as large as the plaintext.
    - It seems to me there is no guarantee that *any* piece of
    plaintext can be encrypted with the **ccrypt** implementation (anything
    that has been encrypted can be brought back, but not *everything* can be
    encrypted). Theoretically, it is possible. But then again,
    theoretically, there would be no bound to the size the ciphertext
    might have.
    - The following is true of the method: *decrypt(encrypt(M)) = M*.
    But the following is not necessarily true: *encrypt(decrypt(M)) = M*.
    - The method is slow.

Please report any bugs to: mario.rincon.nigro@gmail.com

##References
(1) Garcia P, et al. *Phys Rev E Stat Nonlin Soft Matter Phys*. 2002 Apr;65(4 Pt 2A):045201. Epub 2002 Mar 28.
