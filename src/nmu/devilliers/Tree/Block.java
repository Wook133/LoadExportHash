package nmu.devilliers.Tree;

import nmu.devilliers.GeneralHASH;
import nmu.devilliers.MerkleTree;
import nmu.devilliers.Source;
import nmu.devilliers.Trash.deVillSource;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Block extends LinkedMultiTreeNode<Source> {

    int staticnumber;
    int blocksize;
    int numberofsources;
    private long nonce;
    private long timestamp;
    private String prevBlockHash;
    private String merkleRoot;
    private ArrayList<Source> listSourceLeaves;

    /**
     * Creates an instance of this class
     *
     * @param data data to store in the current tree node
     */
    public Block(Source data) {
        super(data);
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


