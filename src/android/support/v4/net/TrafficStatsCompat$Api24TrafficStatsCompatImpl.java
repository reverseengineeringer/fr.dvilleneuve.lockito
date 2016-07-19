package android.support.v4.net;

import java.net.DatagramSocket;
import java.net.SocketException;

class TrafficStatsCompat$Api24TrafficStatsCompatImpl
  extends TrafficStatsCompat.IcsTrafficStatsCompatImpl
{
  public void tagDatagramSocket(DatagramSocket paramDatagramSocket)
    throws SocketException
  {
    TrafficStatsCompatApi24.tagDatagramSocket(paramDatagramSocket);
  }
  
  public void untagDatagramSocket(DatagramSocket paramDatagramSocket)
    throws SocketException
  {
    TrafficStatsCompatApi24.untagDatagramSocket(paramDatagramSocket);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.net.TrafficStatsCompat.Api24TrafficStatsCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */