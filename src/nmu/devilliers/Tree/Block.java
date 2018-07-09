package nmu.devilliers.Tree;

import nmu.devilliers.*;
import nmu.devilliers.Trash.deVillSource;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Block  {
/*extends LinkedMultiTreeNode<Source>*/
    int staticnumber;
    long blocksize;
    int numberofsources;
    private long nonce;
    private long timestamp;
    private String prevBlockHash;
    private String merkleRoot;
    private ArrayList<Source> listSourceLeaves;
    private int idifficulty;

    public Block(int staticnumber, long blocksize, int numberofsources, long nonce, long timestamp, String prevBlockHash, String merkleRoot, ArrayList<Source> listSourceLeaves, int dif) {
        this.listSourceLeaves = listSourceLeaves;
        this.staticnumber = staticnumber;
        this.blocksize = blocksize;
        this.numberofsources = numberofsources;
        this.nonce = nonce;
        this.timestamp = timestamp;
        this.prevBlockHash = prevBlockHash;
        this.merkleRoot = merkleRoot;
        this.idifficulty = dif;
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
        String sInputPow =  toPoWinputString();

        ProofOfWork pow = new ProofOfWork(sInputPow, spattern);
        nonce = pow.pow(0);//decide on which PoW algorithm to use
    }



    public int getStaticnumber() {
        return staticnumber;
    }

    public void setStaticnumber(int staticnumber) {
        this.staticnumber = staticnumber;
    }

    public long getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(int blocksize) {
        this.blocksize = blocksize;
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

}


