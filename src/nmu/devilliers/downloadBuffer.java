package nmu.devilliers;

import java.io.*;
import java.net.URL;
import java.nio.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javafx.util.Pair;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import static java.nio.file.Files.size;


public class downloadBuffer {
    public static void main(String[] args)
    {
        //bufferDownloadOutput();
       // System.out.println(bufferDown("", ""));
       // String s = bufferDown("http://horriblesubs.info/images/b/Giveaway4BE.jpg", false);
        String s = bufferDown("https://www.nmbt.co.za/events/plett_arts_festival.html", false);
        //long ip = pow(s, "SHA3-256", "0");
        /*try
        {
            GeneralHASH gHash = new GeneralHASH();
            String sOut = gHash.HashnoPrint("2661938bec379fc6bf18fb06b8ce93e8ed95daa2259ffca59a41810703db61b26939","SHA3-256");
            System.out.println(sOut);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }*/
    }

    public static void bufferDownloadOutput()
    {
        try {
            URL website = new URL("http://horriblesubs.info/images/b/Giveaway4BE.jpg");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());

            FileOutputStream fos = new FileOutputStream("image.jpg");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        catch (Exception except)
        {
            System.out.println(except);
        }
    }

    /** Create directory to save? FileUtils.moveFileToDirectory
     * Download the file, save it, get the hash, if not permenantly storing file, delete it
     * @param sInputLocation URL of item
     * @param bdelete if true delete else keep
     */
    public static String bufferDown(String sInputLocation, boolean bdelete)
    {
        ArrayList<Pair<String,String>> meta = new ArrayList<>();
        String sout = "";
        String sFile = "";
        try {
            URL website = new URL(sInputLocation);
            sFile = getFileName(website);
            //FileUtils.copyURLToFile(website, sFile);
            //System.out.println(sFile);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(sFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        catch (Exception except)
        {
            System.out.println(except);
        }
        try
        {
            GeneralHASH gHash = new GeneralHASH();
            sout = gHash.naiveFileReaderHash(sFile, "SHA3-256", 65536);
            System.out.println("Hash: " + sout);
            System.out.println("Size: " + getFileSize(sFile) + " bytes");
            /*Path pfilepath = Paths.get(sFile);
            System.out.println("Size: " + size(pfilepath) + " bytes");*/
            if (bdelete == true) {
                File donewith = new File(sFile);

                //get path??

                FileUtils.deleteQuietly(donewith);
            }

        }
        catch (Exception exc)
        {
            System.out.println(exc);
        }
        return sout;
    }

    public static String getFileName(URL urlInput)
    {
        String sout = "";

       // System.out.println(FilenameUtils.getBaseName(url.getPath())); // -> file
        //System.out.println(FilenameUtils.getExtension(url.getPath())); // -> xml
        //System.out.println(FilenameUtils.getName(url.getPath())); // -> file.xml
        sout = FilenameUtils.getName(urlInput.getPath());
        return sout;
    }

    public static String hashYourself(String sIn, String sHashToUse, int iNonceNum)
    {
        String sOut = "";
        Boolean b = false;
        try {
            GeneralHASH gHash = new GeneralHASH();
            sOut = gHash.HashnoPrint(sIn, sHashToUse);
            while (sOut.startsWith("000") == false)
            {
                sOut = gHash.HashnoPrint(sOut, sHashToUse);
                if (sOut.startsWith("000") == false) {
                    System.out.println("Failure: " + sOut);
                }
            }
            System.out.println("POW? :  " + sOut);
        }
        catch (Exception exc)
        {
            System.out.println(exc);
        }

        return sOut;
    }

    public static long pow(String sIn, String sHashToUse, String sNonce)
    {
        long iAns = -1;

        try {
            String sAttempt = "";
            GeneralHASH gHash = new GeneralHASH();

            while (sAttempt.startsWith(sNonce) == false)
            {
                iAns = iAns + 1;
                sAttempt = gHash.HashnoPrint(sIn+iAns, sHashToUse);
                if (sAttempt.startsWith(sNonce) == false) {
                    System.out.println("Failure: " + sAttempt);
                }
            }
            System.out.println("POW (" + iAns + ") :  " + sAttempt);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }

    public static long getFileSize(String sFile){
        try {
            Path pfilepath = Paths.get(sFile);
            return size(pfilepath);
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }
        return 0;
    }

   /* public ArrayList<Pair<String, String>> getFileMetaData(Path filePath)
    {
        Path filePath = new Path(sPath);
        FileStore store = Files.getFileStore(sPath);


    }*/

}
