package rsa;

/**
 *
 * @author sarthak
 */
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
 
public class RSA {
 
      private static Random random;
      private int bitLen;
      private BigInteger n, e, d;
     
      public RSA(int encryptionBits){
          
          //actual encoding steps
            bitLen = encryptionBits;
            random = new Random();
           
            BigInteger p = BigInteger.probablePrime(bitLen / 2, random);
            BigInteger q = BigInteger.probablePrime(bitLen / 2, random);
           
            n = p.multiply(q);
           
            BigInteger m = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            e = BigInteger.probablePrime(bitLen / 2, random);
           
            while(m.gcd(e).compareTo(BigInteger.ONE) > 0){
                  e.add(BigInteger.ONE);
            }
            d = e.modInverse(m);
      }
     //performs conversions
      private byte[] encrypt(String inputString){
          
            byte[] inputStringbyte = inputString.getBytes();
           
            BigInteger inputStringBigInteger = new BigInteger(inputStringbyte);
            byte[] encryptedStringBigIntegerByte = inputStringBigInteger.modPow(e, n).toByteArray();
           
            inputStringBytes(inputString.getBytes());
            encryptedStringBytes(encryptedStringBigIntegerByte);
           
            return encryptedStringBigIntegerByte;
      }
     
      private static void inputStringBytes(byte[] inputStringByte){
            String byteArrayString = "";
           
            for(byte stringByte : inputStringByte){
                  byteArrayString = byteArrayString+Byte.toString(stringByte);
            }
            System.out.println("Encrypted String:"+byteArrayString);
           
      }
     
      private static void encryptedStringBytes(byte[] inputStringByte){
            String byteArrayString = "";
           
            for(byte stringByte : inputStringByte){
                  byteArrayString = byteArrayString+Byte.toString(stringByte);
            }
           
      }
     
      private byte[] decrypt(byte[] encryptedByteArray) {
            byte[] decryptedArray = null;
           
            BigInteger decryptedStringByteArray = new BigInteger(encryptedByteArray);
            decryptedArray = decryptedStringByteArray.modPow(d, n).toByteArray();
           
            dencryptedStringBytes(decryptedArray);
            decryptedString(decryptedArray);
           
            return decryptedArray;
      }
     
      private static void dencryptedStringBytes(byte[] inputStringByte){
            String byteArrayString = "";
           
            for(byte stringByte : inputStringByte){
                  byteArrayString = byteArrayString+Byte.toString(stringByte);
            }
           
      }
     
      private void decryptedString (byte[] decryptedStringbByteArray){
            String decryptedString = new String(decryptedStringbByteArray);
           
            System.out.println("Decrypted Data: "+decryptedString);
      }
     
      public static void main(String[] args) {
           
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
           
            System.out.println("Please enter data to be encrypted.");
            String inputString = scanner.nextLine();
           
            RSA encryption = new RSA(1024);
           
            byte[] encryptedByteArray = encryption.encrypt(inputString);
            System.out.println("Encrypted Byte Array:" + encryptedByteArray );
            encryption.decrypt(encryptedByteArray);
      }
 
}