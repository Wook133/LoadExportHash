package nmu.devilliers;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class GenerateDifferentSizeFiles {

    /**
     * Naive- not good for large file sizes
      * @param sOut Name of the output file
     * @param iNumBytes Byte size of file
     * @throws NoSuchAlgorithmException secure random is thread safe but not present in all Java
     * @throws IOException Creating a new file, probably doesn't need this
     */
    public void writeRandom(String sOut, int iNumBytes) throws NoSuchAlgorithmException, IOException {
        byte[] b = new byte[8096];     //byte
        try {
            FileOutputStream outputStream = new FileOutputStream(sOut+".txt");
            for (int i = 0; i <= iNumBytes; i++) {
                SecureRandom.getInstanceStrong().nextBytes(b);
                // new Random().nextBytes(b);  //assign random byte
                outputStream.write(b);
            }
            outputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void givenWritingToFile_whenUsingFileChannel_thenCorrect(String sOut, int iNumBytes)
            throws IOException, NoSuchAlgorithmException
    {
        byte[] b = new byte[8096];     //bytes
        RandomAccessFile stream = new RandomAccessFile(sOut+".txt", "rw");
        FileChannel channel = stream.getChannel();

        for (int i = 0; i <= iNumBytes; i++) {
            SecureRandom.getInstanceStrong().nextBytes(b);
            ByteBuffer buffer = ByteBuffer.allocate(b.length);
            buffer.put(b);
            buffer.flip();
            channel.write(buffer);
        }
        stream.close();
        channel.close();
    }

    public void writeInputFile(String sFileName)
    {
        try {
            FileWriter fileWriter = new FileWriter(sFileName, true);;
            String NEW_LINE_SEPARATOR = "%n";
            for (int i = 0; i <= 999; i++) {
                long lnum = SecureRandom.getInstanceStrong().nextLong();
                String slong = String.valueOf(lnum);
                GeneralHASH gh = new GeneralHASH();
                String s = gh.HashnoPrint(slong, "SHA3-256");
                fileWriter.append(s);
                fileWriter.append(System.lineSeparator());
            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }


    }


}
