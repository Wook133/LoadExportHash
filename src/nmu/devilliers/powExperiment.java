package nmu.devilliers;

public class powExperiment {
    public String sInputHash;
    public String sPattern;
    public Integer ipos;
    public String sAlgorithm;
    public long lOutput;
    public long time;

    public powExperiment(String sInputHash, String sPattern, Integer ipos, String sAlgorithm, long lOutput, long time) {
        this.sInputHash = sInputHash;
        this.sPattern = sPattern;
        this.ipos = ipos;
        this.sAlgorithm = sAlgorithm;
        this.lOutput = lOutput;
        this.time = time;
    }

    @Override
    public String toString()
    {
        return sInputHash + ", " + sPattern + ", " + ipos + ", " + sAlgorithm + ", " + lOutput + ", " + time;
    }

    public String printer()
    {
        String sout = "Position: " + ipos + "\t\t" + "Pattern: " + sPattern + "\t\t" + sAlgorithm + "\t\t" + "Time: " + time + "ms";
        return sout;
    }



}
