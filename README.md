# chaos-crypt
This repo contains **ccrypt**, a Java implementation of the private key
cryptosystem described in ["Coupled Map Networks as Communication Schemes"(1)]
(http://arxiv.org/abs/nlin/0201042). Super simple, and presumably strong
until shown otherwise, they call it *Text Dependent Encryption*.

If you want to try it out, you can go to folder ccrypt-dist and run
the *ccrypt* shell script.

    $ sh ccrypt e ../res/keys/KEY1 ccrypt ccrypt.enc

That'll encrypt the ccrypt script into file ccrypt.enc

To decrypt it run

   $ sh ccrypt d ../res/keys/KEY1 ccrypt.enc ccrypt.dec

A copy of Sun Tzu's The Art of War, and a copy of the original paper where
the method is described, can be found (encrypted) in folder
res/cipher-text. To decrypt them use key res/keys/KEY1 (or was it KEY2?)
as well.

### A note to anyone who might intend to use **ccrypt** for anything other than mere intellectual curiosity

-Use it for a good purpose. Don't be evil.
-I would bet you (unless you are [Bruce Schneier]
(https://www.schneierfacts.com/)) $50 bucks you cannot break the encryption.
However. I don't know about any thorough analysis of the strength of the
cryptosystem, so that's something to keep in mind.
-Last but not least some caveats:
      + The size of the generated ciphertext is twice as large as the size
      of the plaintext.
      + It seems to me there is no absolute guarantee that *any* piece of
      plaintext can be encrypted with the **ccrypt** implementation (anything
      that hass been encrypted can be brought back, but not everything can be
      encrypted). Theoretically, it is possible. But then again,
      theoretically, there would be no bound to the size of the ciphertext
      might.
      + The following is true of the method: decrypt(encrypt(M)) = M.
      But the following is not necessarily true: encrypt(decrypt(M)) = M
      + The method is slow.

Please report any bugs to: mario.rincon.nigro@gmail.com

##References
(1) Garcia P, et al. *Phys Rev E Stat Nonlin Soft Matter Phys*. 2002 Apr;65(4 Pt 2A):045201. Epub 2002 Mar 28.
