import com.crypto.TranspositionCipher;

import java.util.Random;

public class TranspositionTest {
    public static void main(String[] args) {
        Random r = new Random();
        r.setSeed(42);
        String alphabetUpper = "AВCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = alphabetUpper.toLowerCase();
        String numbers = "0123456789";
        String dictionary = alphabetUpper + alphabetLower + numbers;
        int dictionaryLength = dictionary.length();

        for (int i = 1; i <= 20; i++) {
            int keyLength = r.nextInt(40);

            StringBuilder sb = new StringBuilder(keyLength);
            while (keyLength >= 0) {
                sb.append(dictionary.charAt(r.nextInt(dictionaryLength)));
                keyLength--;
            }
            String message = sb.toString();
            TranspositionCipher tc = new TranspositionCipher(message, 9);
            String encrypt = tc.encrypt();
            String decrypt = new TranspositionCipher(encrypt,9).decrypt();
            if (message.equals(decrypt)) {
                System.out.printf("Test #%s: %s - %s\n",i , message, decrypt);
            }else {
                System.err.printf("Test #%s: %s - %s  Error!", i, message, decrypt);
                System.exit(-1);
            }
        }
    }

}
