package nmu.devilliers;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {
            //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "sAlgorithm,inputLength,outputLength,buffersize,time";

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
	// write your code here
        GeneralHASH gh = new GeneralHASH();
        ArrayList<String> HashFunctions = new ArrayList<String>();
        HashFunctions.add("SHA-1");
        HashFunctions.add("SHA-224");
        HashFunctions.add("SHA-256");
        HashFunctions.add("SHA-384");
        HashFunctions.add("SHA-512");
        HashFunctions.add("SHA3-224");
        HashFunctions.add("SHA3-256");
        HashFunctions.add("SHA3-384");
        HashFunctions.add("SHA3-512");


      /*  MerkleTree mt = new MerkleTree();
        for (int i = 0; i <= 15; i++)
        {
            mt.addHashes(String.valueOf(i));
        }

        mt.buildTree(HashFunctions.get(0));
        System.out.println("I am a Merkle Root: "+ mt.getMerkleRoot());*/


       /* gh.Hash("Hello", "SHA-1");
        gh.Hash("Hello", "SHA-224");
        gh.Hash("Hello", "SHA-256");
        gh.Hash("Hello", "SHA-384");
        gh.Hash("Hello", "SHA-512");

        gh.Hash("Hello", "SHA3-224");
        gh.Hash("Hello", "SHA3-256");
        gh.Hash("Hello", "SHA3-384");
        gh.Hash("Hello", "SHA3-512");*/

        long start = 0;
        long end = 0;
        long time = 0;

        for (int i = 30; i <= 20; i++)//input file
        {
            for (int j = 0; j <= HashFunctions.size() - 1; j++)//hash function
            {
               // for (int k = 3; k <= 10; k++)//buffer size
                //{
                  //  Double il = Math.pow(4, k * 1.00);
                    start = System.currentTimeMillis();
                    //String s = gh.naiveFileReaderHash(String.valueOf(i), HashFunctions.get(j), il.intValue());
                    String s = gh.entireFileReaderHash(String.valueOf(i), HashFunctions.get(j));
                    System.out.println(s);
                    end = System.currentTimeMillis();
                    time = end - start;
                    long lis = gh.inputSize(String.valueOf(i));
                    Integer los = s.length();
                    experiment curExp = new experiment(lis, los, HashFunctions.get(j), 0, time);
                    System.out.println(i + " : " + j + " : " + 0 + " : " + curExp.toString());
                    writeCsvFile("unbufferedHASHTHING.csv", curExp);
              //  }
            }
        }




        //gh.hashoffile();
      //  gh.bufferedHashofFile("", "he.png");

       // gh.bhof("", "he.png");


        //gh.naiveFileReaderHash("0.txt", "SHA-256", 4096);

        //GenerateDifferentSizeFiles gdsf = new GenerateDifferentSizeFiles();
       /* try {
           // for (int i =)
            start = System.currentTimeMillis();
            //1048576
            gdsf.writeRandom("slow", 256);
            end = System.currentTimeMillis();
            System.out.println("Time: " + ((end - start)) + " milliseconds");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }*/

        try {
            for (int i = 18; i <= 1; i++) {
                Double il = Math.pow(2.00, i * 1.00);
                System.out.println(il);
                String s = String.valueOf(i);
                GenerateDifferentSizeFiles gdsf = new GenerateDifferentSizeFiles();
                start = System.currentTimeMillis();
                gdsf.givenWritingToFile_whenUsingFileChannel_thenCorrect(s, il.intValue());
                end = System.currentTimeMillis();
                System.out.println("Time Other: " + ((end - start)) + " milliseconds");

            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
            /*start = System.currentTimeMillis();
            gdsf.givenWritingToFile_whenUsingFileChannel_thenCorrect("fast", 8096);
            end = System.currentTimeMillis();
            System.out.println("Time Other: " + ((end - start)) + " milliseconds");*/
          //}




    }
    public static void writeCsvFile(String fileName, experiment e1) throws IOException {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(fileName, true);
            //fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(e1.toString());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }




}
