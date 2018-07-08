package nmu.devilliers;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;

import java.security.MessageDigest;

public class SHA3_test {
    public static void main(String[] args) {
        System.out.println(SHA3Runner("Hello"));
    }

    /**
     * @param input String to get the Hash of
     *
     * @return the hash value of the input
     */
    public static String SHA3Runner(final String input) {
        final DigestSHA3 sha3 = new Digest256();


        sha3.update(input.getBytes());

        return SHA3_test.hashToString(sha3);
    }

    public static String hashToString(MessageDigest hash) {
        return hashToString(hash.digest());
    }

    public static String hashToString(byte[] hash) {
        StringBuffer buff = new StringBuffer();

        for (byte b : hash) {
            buff.append(String.format("%02x", b & 0xFF));
        }

        return buff.toString();
    }
}
