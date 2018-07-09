package nmu.devilliers.Tree;

import nmu.devilliers.*;
import nmu.devilliers.Trash.deVillSource;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Block  {
/*extends LinkedMultiTreeNode<Source>*/
    final int staticnumber = 0xD9B4BEF9;//3652501241
    static final long ALLOWED_TIME_DRIFT = 7200L;
    long blocksize;
    int numberofsources;
    private long nonce;
    private long timestamp;
    private String prevBlockHash;
    private String merkleRoot;
    private ArrayList<Source> listSourceLeaves;
    private int idifficulty;
    private String blockHash;

    public Block(ArrayList<Source> listSourceLeaves, int dif) {
        for (Source s : listSourceLeaves) {
            this.listSourceLeaves.add(s);
        }
        this.idifficulty = dif;
        this.blockHash = null;
    }

    public Block()
    {

    }

    public String toPoWinputString()
    {
        String s = staticnumber+blocksize+numberofsources+timestamp+prevBlockHash+merkleRoot+idifficulty;
        for (Source so : listSourceLeaves)
        {
            s.concat(so.toString());
        }
        return s;

    }

    public void deriveBlock()
    {//public ProofOfWork(String sContent, String sPat)
        //ProofOfWork pow = new ProofOfWork();

        numberofsources = listSourceLeaves.size();
        generateMerkleRoot();
        TimerServer ts = new TimerServer();
        timestamp = ts.getTime();
        String spattern = "";
        for (int i = 0; i <= idifficulty; i++)
        {
            spattern.concat("0");//set number of 0's for PoW
        }
        makeBlocksize();
        ProofOfWork pow = new ProofOfWork(toPoWinputString(), spattern);
        nonce = pow.pow(0);//decide on which PoW algorithm to use
        blockHash = pow.hashMe();
    }



    public int getStaticnumber() {
        return staticnumber;
    }


    public long getBlocksize() {
        return blocksize;
    }
    public String getBlockHash() {
        return blockHash;
    }

    public void makeBlocksize() {
        long l = 0;
        for (Source s : listSourceLeaves)
        {
            l = s.getApproximateSize();
        }
        l = l + (Integer.BYTES * 3) + (Long.BYTES * 4) + getByteLengthString(prevBlockHash) + getByteLengthString(merkleRoot);
        blocksize = l;


    }

    public int getNumberofsources() {
        return numberofsources;
    }

    public void setNumberofsources(int numberofsources) {
        this.numberofsources = numberofsources;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public ArrayList<Source> getListSourceLeaves() {
        return listSourceLeaves;
    }

    public void setListSourceLeaves(ArrayList<Source> listSourceLeaves) {
        this.listSourceLeaves = listSourceLeaves;
    }

    public void generateMerkleRoot()
    {
        ArrayList<String> listSourceHashes = new ArrayList<>();
        for (Source scur : listSourceLeaves) {
            try {
                GeneralHASH gh = new GeneralHASH();
                String sHash = gh.HashnoPrint(scur.toString(), "SHA3-256");
                listSourceHashes.add(scur.toString());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        MerkleTree mt = new MerkleTree();
        mt.populateListHashes(listSourceHashes);
        mt.buildTree("SHA3-256");
        merkleRoot = mt.getMerkleRoot();
    }

    public static long getByteLengthString(String s)
    {
        return s.getBytes().length;
    }

    public static int getByteLengthInt()
    {
        return Integer.BYTES;
    }

    public String toString()
    {
        String s = staticnumber + "_" + ALLOWED_TIME_DRIFT + "_" + blocksize + "_" + numberofsources + "_" + nonce + "_" + timestamp + "_" + prevBlockHash + "_" + merkleRoot;
        for (Source scur : listSourceLeaves)
        {
            s.concat(scur.toString());
            s.concat("_");
        }
        s = s + idifficulty;
        return s;
    }

    /*final int staticnumber = 0xD9B4BEF9;//3652501241
    static final long ALLOWED_TIME_DRIFT = 7200L;
    long blocksize;
    int numberofsources;
    private long nonce;
    private long timestamp;
    private String prevBlockHash;
    private String merkleRoot;
    private ArrayList<Source> listSourceLeaves;
    private int idifficulty;*/

}


