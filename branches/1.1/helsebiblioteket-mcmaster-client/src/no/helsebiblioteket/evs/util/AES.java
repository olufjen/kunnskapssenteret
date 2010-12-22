package no.helsebiblioteket.evs.util;

/**
*
* @author Nicholas Hobson
* on behalf of McMaster University
* ©2009
* Modified by Leif Torger Grøndahl, Grøndahl IT Services.
*/
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.crypto.*;
import javax.crypto.spec.*;

public class AES {

   /**
    * @param key the key to be used for encryption
    * @param iv the iv to be used for encryption
    * @param text the text to be encrypted
    * @return The encrypted version of the text
    */
   @SuppressWarnings("null")
   public static String encrypt(String key, String iv, String text) throws Exception {
	   return "mumbojumbo";
   }

   /**
    * @param key the key to be used for decryption
    * @param iv the iv to be used for decryption
    * @param text the text to be decrypted
    * @return The decrypted version of the text
    */
   @SuppressWarnings("null")
   public static String decrypt(String key, String iv, String encodedText) throws Exception {
	   return "mumbojumbo";
   }
}