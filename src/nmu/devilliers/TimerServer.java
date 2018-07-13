package nmu.devilliers;

import java.net.InetAddress;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;

public class TimerServer {
    //System Time
    //this.timestamp = System.currentTimeMillis() / 1000L;

   // public static final String TIME_SERVER = "nist1-ny.ustiming.org";
    public static final String TIME_SERVER = "time.google.com";

    public static void main(String[] args) throws Exception {
        long lsTime = getTime();
        Date time = new Date(lsTime);
        System.out.println("Time from: " + TIME_SERVER + "\t\t" + time + "\t\t" + lsTime);
        long lsystemTime = System.currentTimeMillis();
        Date systemTime = new Date(lsystemTime);
        System.out.println("Time from: System " + "\t\t" + "\t\t" + systemTime + "\t\t" + lsystemTime);

        //Thread.sleep(86400000);
        //lsystemTime = System.currentTimeMillis();
       // systemTime = new Date(lsystemTime);
       // System.out.println("Time from: System " + "\t\t" + "\t\t" + systemTime + "\t\t" + lsystemTime);



      /* try {
            NTPUDPClient timeClient = new NTPUDPClient();
            InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
            TimeInfo timeInfo = timeClient.getTime(inetAddress);
            NtpV3Packet message = timeInfo.getMessage();
            long serverTime = message.getTransmitTimeStamp().getTime();
            Date time = new Date(serverTime);
            System.out.println("Time from: " + TIME_SERVER + "\t\t" + time + "\t\t" + serverTime);
            long lsystemTime = System.currentTimeMillis();
            Date systemTime = new Date(lsystemTime);
            System.out.println("Time from: System " + "\t\t" + "\t\t" + systemTime + "\t\t" + lsystemTime);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }*/
//1528904794731
//1528904807769
//1528912123220
//1529322868421
    }
    public static long getTime()
    {
        long ltime = 0;
        try {
            NTPUDPClient timeClient = new NTPUDPClient();
            InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
            TimeInfo timeInfo = timeClient.getTime(inetAddress);
            NtpV3Packet message = timeInfo.getMessage();
            long serverTime = message.getTransmitTimeStamp().getTime();
            return serverTime;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }

    public static String toStringGetTime()
    {
        long time = getTime();
        return String.valueOf(time);
    }


}
