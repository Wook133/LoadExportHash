package nmu.devilliers;

import nmu.devilliers.Trash.deVillSource;

import java.util.ArrayList;

public class devilBlock
{
    int staticnumber;
    int blocksize;
    int numberofsources;
    private long nonce;
    private long timestamp;
    private String prevBlockHash;
    private String merkleRoot;
    private ArrayList<deVillSource> listSourceLeafs;

    public devilBlock(long timestamp, String prevBlockHash, String merkleRoot) {
        this.timestamp = TimerServer.getTime();
        this.prevBlockHash = prevBlockHash;
        this.merkleRoot = merkleRoot;
    }


}
