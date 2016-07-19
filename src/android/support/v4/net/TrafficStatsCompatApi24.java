package android.support.v4.net;

import android.net.TrafficStats;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TrafficStatsCompatApi24
{
  public static void tagDatagramSocket(DatagramSocket paramDatagramSocket)
    throws SocketException
  {
    TrafficStats.tagDatagramSocket(paramDatagramSocket);
  }
  
  public static void untagDatagramSocket(DatagramSocket paramDatagramSocket)
    throws SocketException
  {
    TrafficStats.untagDatagramSocket(paramDatagramSocket);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.net.TrafficStatsCompatApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */