package nmu.devilliers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.io.DigestInputStream;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.util.MessageDigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Scanner;

public class GeneralHASH {

    public GeneralHASH() throws NoSuchAlgorithmException {
        /*String plainString = "Hello world, my name is john";
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        StringBuffer sout = new StringBuffer();
        byte[] hashedString = messageDigest.digest(plainString.getBytes());
        for (int i = 0; i < hashedString.length; i++) {
            sout.append(Integer.toString((hashedString[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println(sout.toString());*/
    }

    public String Hash(String sinput, String hashToUse) throws NoSuchAlgorithmException
    {
        String sOut = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashToUse);
            StringBuffer sout = new StringBuffer();
            byte[] hashedString = messageDigest.digest(sinput.getBytes());

            for (int i = 0; i < hashedString.length; i++) {
                sout.append(Integer.toString((hashedString[i] & 0xff) + 0x100, 16).substring(1));
            }
            //String shash = hashToString(hashedString);
            //System.out.println(hashToUse);
            //System.out.println(sinput + " --> " + sout);
           // System.out.println(sinput + " --> " + shash);
            //System.out.println("Byte:" + hashedString.toString());
            //System.out.println("________________________________________________");
        }
        catch (Exception e) {
            return sOut;
        }
        return sOut;
    }
    public String HashnoPrint(String sinput, String hashToUse) throws NoSuchAlgorithmException
    {
        String sOut = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashToUse);
            StringBuffer sout = new StringBuffer();
            byte[] hashedString = messageDigest.digest(sinput.getBytes());

            for (int i = 0; i < hashedString.length; i++) {
                sout.append(Integer.toString((hashedString[i] & 0xff) + 0x100, 16).substring(1));
            }
            sOut = hashToString(hashedString);
        }
        catch (Exception e) {
            return sOut;
        }
        return sOut;
    }

    public String naiveFileReaderHash(String sFileInput, String hashToUse, Integer bsize) throws NoSuchAlgorithmException
    {
        String sOut = "";
        String scur = "";
        String scurHash = "";
        String sHash = "";
        String sPasthash = "";
        try
        {
           // FileInputStream fin = new FileInputStream(sFileInput+".txt");
            FileInputStream fin = new FileInputStream(sFileInput);
            try (BufferedInputStream in = new BufferedInputStream(fin))
            {
                byte[] bbuf = new byte[bsize];
                int len;
                while ((len = in.read(bbuf)) != -1) {
                    // process data here: bbuf[0] thru bbuf[len - 1]
                    scur = hashToString(bbuf);//scur == hexadecimal ascii
                   // System.out.print(scur);
                    scurHash = HashnoPrint(scur, hashToUse);
                    sHash = sHash + scurHash;
                   // sPasthash = sPasthash + scurHash;
                   // String stemp = sPasthash;
                    sHash = HashnoPrint(sHash, hashToUse); }
            }
            System.out.println();
            fin.close();
            System.out.println(hashToUse + " --> " + sHash);
            System.out.println("________________________________________________");
            return sHash;
        }
        catch (Exception e)
        {
            return sHash;
        }
    }

    public String entireFileReaderHash(String sFileInput, String hashToUse) throws NoSuchAlgorithmException
    {
        String sOut = "";
        String scur = "";
        String scurHash = "";
        String sHash = "";
        String sPasthash = "";
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance(hashToUse);
            FileInputStream fin = new FileInputStream(sFileInput+".txt");
            byte[] hashedString = messageDigest.digest(fin.readAllBytes());
            String sin = hashToString(hashedString);
            sHash = HashnoPrint(sin, hashToUse);


            System.out.println();
            fin.close();
            System.out.println(hashToUse + " --> " + sHash);
            System.out.println("________________________________________________");
            return sHash;
        }
        catch (Exception e)
        {
            return sHash;
        }
    }

    public long inputSize(String sFileInput)
    {
        long il = 0;
        try {
            //
            Path imageFilePath = Paths.get("C:/Users/Jack/Desktop/LoadHashExport/LoadHashExport/LoadExportHash/" + sFileInput+".txt");
           // Path imageFilePath = Paths.get("X:/University/2018/Prototypes/LoadHashExport/" + sFileInput+".txt");
            //"X:/University/2018/Prototypes/LoadHashExport/" + sFileInput+".txt"
            FileChannel imageFileChannel = FileChannel.open(imageFilePath);
            il = imageFileChannel.size();
        }
        catch (Exception e)
        {
            return il;
        }
        return il;
    }



    public String hashoffile()
    {
        try {
            //File f = new File("name");
            //System.out.println(f.getAbsolutePath());
            byte[] buffer = new byte[8192];
            int count;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //DigestInputStream
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("he.png"));
            //DigestInputStream bis = new DigestInputStream(new FileInputStream("he.png"));
            while ((count = bis.read(buffer)) > 0) {
                digest.update(buffer, 0, count);
            }
            bis.close();
            String ed = "";

            byte[] hash = digest.digest();
            System.out.println(hash.toString());
            return ed;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return "No";
        }
    }

    public String bufferedHashofFile(String sHash, String sFile) throws NoSuchAlgorithmException
    {
        try
        {
            byte[] buffer = new byte[8192];
            SHA256Digest digest = new SHA256Digest();
            DigestInputStream dis = new DigestInputStream(new FileInputStream(sFile), digest);
            int count;

            while ((count = dis.read(buffer)) > 0) {
                digest.update(buffer, 0, count);
            }

            dis.close();
            String ed = "";

            String s = new String();
            System.out.println(s);
            System.out.println(digest.toString());
            return ed;

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return "No";
        }
    }

    public String bhof(String sHash, String sFile) throws NoSuchAlgorithmException
    {
        try
        {

            byte[] buffer = new byte[8192];

            StringBuffer sout = new StringBuffer();
            int icount = 0;
            int inumbuf = 0;
            int iread =0;

            SHA256Digest md = new SHA256Digest();
            DigestInputStream dis = new DigestInputStream(new FileInputStream(sFile), md);
            //MessageDigestUtils
            byte[] hashedString;
            while ((icount = dis.read(buffer)) > 0) {
                md.update(buffer, 0, icount);
            }
            System.out.println(hashToString(buffer));
             //   sout.append(Integer.toString((hashedString[i] & 0xff) + 0x100, 16).substring(1));




            return "No";
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return "No";
        }
    }

    public static String hashToString(MessageDigest hash) {
        return hashToString(hash.digest());
    }

    public static String hashToString(byte[] hash) {
        StringBuffer buff = new StringBuffer();
        byte[] var2 = hash;
        int var3 = hash.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            buff.append(String.format("%02x", b & 255));
        }

        return buff.toString();
    }

}
