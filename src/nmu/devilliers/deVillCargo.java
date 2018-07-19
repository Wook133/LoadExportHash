package nmu.devilliers;

import javafx.util.Pair;

import java.util.ArrayList;

public final class deVillCargo implements java.io.Serializable, Comparable<deVillCargo>
{
    private String HashofFile;
    private String Timestamp;
    private String SizeofFile;
    private String publicAddressAdder;
    private String OriginalURL;
    private ArrayList<Pair<String,String>> listMeta = new ArrayList<>();//removeMeta?
    private ArrayList<String> moreLinks = new ArrayList<>();
    private Integer sizeofSource;//Remove?

    public deVillCargo(String hashofFile, String timestamp, String sizeofFile, String pkAdder, String originalURL) {
        // LSize = (long)hashofFile.length() + (long)timestamp.length() + (long)sizeofFile.length() + (long)pkAdder.length() + (long)originalURL.length();
        //if (LSize < maxNumberBytes) {
        HashofFile = hashofFile;
        Timestamp = timestamp;
        SizeofFile = sizeofFile;
        this.publicAddressAdder = pkAdder;
        OriginalURL = originalURL;

        //}
    }

    public deVillCargo(String hashofFile, String sizeofFile, String pkAdder, String originalURL) {
        HashofFile = hashofFile;
        Timestamp = TimerServer.toStringGetTime();
        SizeofFile = sizeofFile;
        this.publicAddressAdder = pkAdder;
        OriginalURL = originalURL;
    }
    public deVillCargo() {
    }


    public Pair<String, String> removeMeta(int i)
    {

        //Pair<String, String> pCur = listMeta.get(i);
        //LSize = LSize - ((pCur.getKey().length()) +(pCur.getValue().length()));
        //pCur = null;
        return listMeta.remove(i);
    }
    public String removeLink(int i)
    {
        // String s = moreLinks.get(i);
        // LSize = LSize - (s.length());
        // s = null;
        return moreLinks.remove(i);
    }

    public Boolean addMeta(Pair<String, String> i)
    {
        /**Long size = Long.valueOf(i.getKey().length() + i.getValue().length());
         Long ltemp = size + LSize;
         if (ltemp < maxNumberBytes)
         {
         LSize = ltemp;**/
        if (listMeta.size() < 1000)
        {
            listMeta.add(i);
            return true;
        }
        else
            return false;
    }
    public Boolean addLink(String i)
    {

        if (moreLinks.size() < 1000)
        {
            moreLinks.add(i);
            return true;
        }
        else
            return false;
    }
    public Boolean addLinks(ArrayList<String> listIn)
    {
        boolean badded = false;
        for (String s : listIn)
        {
            badded = addLink(s);
        }
        return badded;
    }


    public String getHashofFile() {
        return HashofFile;
    }

    public void setHashofFile(String hashofFile) {
        HashofFile = hashofFile;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String getSizeofFile() {
        return SizeofFile;
    }

    public void setSizeofFile(String sizeofFile) {
        SizeofFile = sizeofFile;
    }

    public String getpublicAdressAdder() {
        return publicAddressAdder;
    }

    public void setPkAdder(String pkAdder) {
        this.publicAddressAdder = pkAdder;
    }

    public String getOriginalURL() {
        return OriginalURL;
    }

    public void setOriginalURL(String originalURL) {
        OriginalURL = originalURL;
    }

    public ArrayList<Pair<String, String>> getListMeta() {
        return listMeta;
    }

    public void setListMeta(ArrayList<Pair<String, String>> listMeta) {
        this.listMeta = listMeta;
    }

    public ArrayList<String> getMoreLinks() {
        return moreLinks;
    }

    public void setMoreLinks(ArrayList<String> moreLinks) {
        this.moreLinks = moreLinks;
    }

    @Override
    public String toString()
    {
        String sout = "";
        sout = HashofFile + "_" + Timestamp + "_" + SizeofFile + "_" + publicAddressAdder + "_" + OriginalURL;
        String stemp = "";
        /*for (int i =0; i <= listMeta.size() - 1; i++)
        {
            Pair pcur = listMeta.get(i);
            String skey = (String)pcur.getKey();
            String svalue = (String)pcur.getValue();
            stemp = stemp + "_" + skey + "_" + svalue;
        }*/
        sout = sout + "_" + stemp;
        stemp = "";
        if (moreLinks.size() < 100) {
            for (int j = 0; j <= moreLinks.size() - 1; j++) {
                stemp = stemp + "_" + moreLinks.get(j);
            }
            if (moreLinks.size() > 0) {
                sout = sout + "_" + stemp;
            }
        }
        return sout;
    }

    public String toStringWithHash()
    {
        String sout = "";
        sout = hashCargo() +"_"+ HashofFile + "_" + Timestamp + "_" + SizeofFile + "_" + publicAddressAdder + "_" + OriginalURL;
        String stemp = "";
        /*for (int i =0; i <= listMeta.size() - 1; i++)
        {
            Pair pcur = listMeta.get(i);
            String skey = (String)pcur.getKey();
            String svalue = (String)pcur.getValue();
            stemp = stemp + "_" + skey + "_" + svalue;
        }*/
        sout = sout + "_" + stemp;
        stemp = "";
        if (moreLinks.size() < 100) {
            for (int j = 0; j <= moreLinks.size() - 1; j++) {
                stemp = stemp + "_" + moreLinks.get(j);
            }
            if (moreLinks.size() > 0) {
                sout = sout + "_" + stemp;
            }
        }
        return sout;
    }

    @Override
    public int compareTo(deVillCargo o) {
        String s = this.toString();
        String sObj = o.toString();
        return (s.compareTo(sObj));
    }

    public Boolean equalsTo(deVillCargo obj1) {
        String s = this.toString();
        String sObj = obj1.toString();
        return (s.equals(sObj));
    }

    public String getPublicAddressOfCreator() {
        return publicAddressAdder;
    }

    public ArrayList<Pair<String, String>> toCargoArraylistPairForSending()
    {
        ArrayList<Pair<String, String>> listout = new ArrayList<>();
        /*
        private String OriginalURL;
        private ArrayList<Pair<String,String>> listMeta = new ArrayList<Pair<String,String>>();
        private ArrayList<String> moreLinks = new ArrayList<String>();
        */
        Pair<String, String> pairCur = new Pair<>("HashofCargo", this.hashCargo());
        listout.add(pairCur);
        pairCur = new Pair<>("HashofFile", HashofFile);
        listout.add(pairCur);
        pairCur = new Pair<>("Timestamp", Timestamp);
        listout.add(pairCur);
        pairCur = new Pair<>("SizeofFile", SizeofFile);
        listout.add(pairCur);
        pairCur = new Pair<>("PublicAddressAdder", publicAddressAdder);
        listout.add(pairCur);
        pairCur = new Pair<>("OriginalURL", OriginalURL);
        listout.add(pairCur);
        /*for (int i = 0; i <= listMeta.size() - 1; i++)
        {
            pairCur = new Pair<>(listMeta.get(i).getKey(), listMeta.get(i).getValue());
            listout.add(pairCur);
        }*/
        for (int i = 0; i <= moreLinks.size() - 1; i++)
        {
            pairCur = new Pair<>("AdditionalURL" + i + ":", moreLinks.get(i));
            listout.add(pairCur);
        }
        return listout;
    }


    public String hashCargo() {
        try {
            GeneralHASH gh = new GeneralHASH();
            return gh.HashnoPrint(this.toString(), "SHA3-256");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("de Vill Source, No Such Class or Method: General Hash");
        }
        return getHashofFile();
    }

    public long getApproximateSize()
    {

        /*private String HashofFile;
        private String Timestamp;
        private String SizeofFile;
        private String pkAdder;
        private String OriginalURL;
        private ArrayList<Pair<String,String>> listMeta = new ArrayList<>();
        private ArrayList<String> moreLinks = new ArrayList<>();
        private Integer sizeofSource;*/

        String s = toString();
        long lout = getByteLengthString(s);
        return lout;
    }

    public static int getByteLengthString(String s)
    {
        return s.getBytes().length;
    }
    public static int getByteLengthInteger()
    {
        return Integer.BYTES;
    }

    public boolean verifyHash()
    {//bufferDown
        try {
            downloadBuffer dBuf = new downloadBuffer();
            String sDownloadHash = dBuf.bufferDown(this.getOriginalURL(), false);
            if (sDownloadHash.compareTo(this.getHashofFile()) == 0)
            {
                return true;
            }
            else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("de Vill Source, No Such Class or Method: General Hash");
        }
        return false;
    }


}
