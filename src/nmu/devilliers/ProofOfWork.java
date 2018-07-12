package nmu.devilliers;

import java.io.*;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProofOfWork
{
    String sContent;
    String sHashtoUse;
    String sPattern;
    long nonce;

    public ProofOfWork(String sContent, String sHashtoUse, String sPattern, long nonce) {
        this.sContent = sContent;
        this.sHashtoUse = sHashtoUse;
        this.sPattern = sPattern;
        this.nonce = nonce;
    }
    public ProofOfWork(String sContent, String sPat)
    {
        this.sContent = sContent;
        this.sPattern = sPat;
        this.sHashtoUse = "SHA3-256";
        this.nonce = 0;
    }
    public static void main(String[] args)
    {
        ProofOfWork test = new ProofOfWork("Hello", "000");
        runExperiment("PoWExperiment.txt");
        //System.out.println(test.pow(1));
        //System.out.println(test.randPOW(1));
        //System.out.println(test.recPOW());
        //GenerateDifferentSizeFiles gdsf = new GenerateDifferentSizeFiles();
        //gdsf.writeInputFile("PoWExperiment.txt");
    }

    public String hashMe()
    {
        try {
            String sAttempt = "";
            GeneralHASH gHash = new GeneralHASH();
            return(gHash.HashnoPrint(sContent+nonce, sHashtoUse));
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    /**
     *
     * @param ipos identifies where the pattern occurs. 0 -> Start; 1 -> Anywhere; 2 -> End
     * @return
     */
    public long pow(int ipos)
    {
        long iAns = -1;
        Boolean bfound = false;

        try {
            String sAttempt = "";
            GeneralHASH gHash = new GeneralHASH();

            while (bfound == false)
            {
                iAns = iAns + 1;
                sAttempt = gHash.HashnoPrint(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                           // System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                            //System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                            //System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                            //System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                }
            }
           // System.out.println("POW (" + iAns + ") :  " + sAttempt);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }
    /**
     *
     * @param ipos identifies where the pattern occurs. 0 -> Start; 1 -> Anywhere; 2 -> End
     * @return
     */
    public long pow(long lstart, int ipos)
    {
        long iAns = lstart-1;
        Boolean bfound = false;

        try {
            String sAttempt = "";
            GeneralHASH gHash = new GeneralHASH();

            while (bfound == false)
            {
                iAns = iAns + 1;
                sAttempt = gHash.HashnoPrint(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                            // System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                            //System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                            //System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                            //System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                }
            }
            // System.out.println("POW (" + iAns + ") :  " + sAttempt);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }

    public String powString(long lstart, int ipos)
    {
        long iAns = lstart;
        Boolean bfound = false;
        String sAttempt = "";
        try {

            GeneralHASH gHash = new GeneralHASH();
            sAttempt = gHash.HashnoPrint(sContent+iAns, sHashtoUse);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return sAttempt;
    }

    public long powReverse(int ipos)
    {
        long iAns = Long.MAX_VALUE;
        Boolean bfound = false;

        try {
            String sAttempt = "";
            GeneralHASH gHash = new GeneralHASH();

            while (bfound == false)
            {
                sAttempt = gHash.HashnoPrint(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                            // System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                            //System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                            //System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                            //System.out.println("POW (" + iAns + ") :  " + sAttempt);
                            //return iAns;
                        }
                    }
                }
                iAns = iAns - 1;
            }
            // System.out.println("POW (" + iAns + ") :  " + sAttempt);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }

    public long recPOW()
    {
        try {
            GeneralHASH gh = new GeneralHASH();
            String scurhash = gh.HashnoPrint(sContent+nonce, sHashtoUse);
            if (scurhash.startsWith(sPattern) == false)
            {
                //System.out.println("Failure: " + scurhash);
                nonce = nonce + 1;
                recPOW();
            }
            else
            {
                System.out.println("RecurPOW (" + nonce + ") :  " + scurhash);
                return nonce;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return nonce;
    }

    public long randPOW(int ipos)
    {
        Random random = new Random();
        Boolean bfound = false;
        long iAns = -1;
        try
        {
            GeneralHASH gHash = new GeneralHASH();
            String sAttempt = "";
        /*    while (sAttempt.startsWith(sPattern) == false)
            {
                //l = random.longs().findFirst().getAsLong();
                l = random.nextLong();
                if (l < 0)
                {
                    l = l*-1;
                }
                sAttempt = gHash.HashnoPrint(sContent+l, sHashtoUse);
                if (sAttempt.startsWith(sPattern) == false)
                {
                    //System.out.println("Failure: " + sAttempt);
                }
            }*/
            while (bfound == false)
            {
                iAns = SecureRandom.getInstanceStrong().nextLong();
                if (iAns < 0)
                {
                    iAns = iAns*-1;
                }
                sAttempt = gHash.HashnoPrint(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                }
            }

           // System.out.println("POW (" + iAns + ") :  " + sAttempt);
            return iAns;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }

    public long randPosNegPOW(int ipos)
    {
        Random random = new Random();
        Boolean bfound = false;
        long iAns = -1;
        try
        {
            GeneralHASH gHash = new GeneralHASH();
            String sAttempt = "";
            while (bfound == false)
            {
                iAns = SecureRandom.getInstanceStrong().nextLong();
                sAttempt = gHash.HashnoPrint(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                }
            }

            // System.out.println("POW (" + iAns + ") :  " + sAttempt);
            return iAns;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }

    public static void writeCsvFile(String fileName, powExperiment e1) throws IOException {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(fileName+".csv", true);
            fileWriter.append(System.lineSeparator());
            fileWriter.append(e1.toString());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static ArrayList<String> readInput(String sInFile)
    {
        ArrayList<String> listOut = new ArrayList<String>();
        try {
            File fin = new File(sInFile);
            BufferedReader b = new BufferedReader(new FileReader(fin));
            String sreadcur = "";
            while((sreadcur = b.readLine()) != null)
            {
                System.out.println(sreadcur);
                listOut.add(sreadcur);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return listOut;
    }

    public static void runExperiment(String sFilename)
    {
        ArrayList<String> arrInput = new ArrayList<String>();
        arrInput = readInput(sFilename);
        ArrayList<String> arrPatterns = new ArrayList<>();
        //arrPatterns.add("0");
        //arrPatterns.add("00");
        //arrPatterns.add("000");
        //arrPatterns.add("0000");
        arrPatterns.add("00000");
        //arrPatterns.add("000000");

        for (int j = 0; j <= arrInput.size() - 1; j++) {
            System.out.println("Progress: " + j);
            for (int i = 0; i <= 2; i++) {
                for (int k =0; k <= arrPatterns.size() - 1; k++)
                {
                    long start = 0;
                    long end = 0;
                    long time = 0;
                    ProofOfWork curPoW = new ProofOfWork(arrInput.get(j), arrPatterns.get(k));
                    long lans = 0;
                    start = System.currentTimeMillis();
                    lans = curPoW.pow(i);
                    end = System.currentTimeMillis();
                    time = end - start;
                    powExperiment p1 = new powExperiment(arrInput.get(j), arrPatterns.get(k), i, "naive backwards", lans, time);
                    System.out.println(p1.printer());
                    start =0;
                    end = 0;
                    time = 0;
                    lans = 0;
                    start = System.currentTimeMillis();
                    lans = curPoW.randPOW(i);
                    end = System.currentTimeMillis();
                    time = end - start;
                    powExperiment p2 = new powExperiment(arrInput.get(j), arrPatterns.get(k), i, "PaN random", lans, time);
                    System.out.println(p2.printer());
                    try {
                        writeCsvFile("1000PoWdepth5", p1);
                        writeCsvFile("1000PoWdepth5", p2);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.fillInStackTrace());
                    }
                    System.out.println("________________");
                }
                System.out.println("____________________________________________");
            }
            System.out.println("____________________________________________________________________");
        }
    }

}
