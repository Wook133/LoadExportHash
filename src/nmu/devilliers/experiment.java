package nmu.devilliers;

public class experiment
{
    public long inputLength;
    public Integer outputLength;
    public String sAlgorithm;
    public Integer buffersize;
    public long time;

    public experiment(long inputLength, Integer outputLength, String sAlgorithm, Integer buffersize, long time) {
        this.inputLength = inputLength;
        this.outputLength = outputLength;
        this.sAlgorithm = sAlgorithm;
        this.buffersize = buffersize;
        this.time = time;
    }
    @Override
    public String toString()
    {
      //  return "Experiment [inputLength=" + inputLength + ", outputLength =" + outputLength + ", sAlgorithm =" + sAlgorithm +", buffersize =" + buffersize + ", time =" +time + "]";
        return sAlgorithm + ", " + inputLength + ", " + outputLength + ", " + buffersize + ", " + time;
    }

}
