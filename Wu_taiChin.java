import java.net.*;
import java.io.*;
import java.util.*;
import java.net.Inet4Address;
import java.io.*;
/**Pings a local area network for live hosts */
public class Wu_taiChin {
  //////////////////////////////////////////////method for writing results to a file//
  public static BufferedWriter logging() {
    boolean written = false;
    BufferedWriter wTo = null;
    do {
      try {
        wTo = new BufferedWriter(new FileWriter("Briefcase.txt"));
        wTo.write("**************Report ******************\n");
      } catch (IOException e) {
        written = false;
      }
    } while (written == false);
    return wTo;
  }

  ///////////////////////////do a ping sweep to discover available hosts on a network//
  public static void checkHosts(CharSequence first3octet, BufferedWriter wTo) throws IOException {
    int timeout = 1000;
    String neighbor;
    for (int i = 1; i < 255; i++) {
      neighbor = first3octet + "." + i;
      try {
        if (InetAddress.getByName(neighbor).isReachable(timeout)) {
          System.out.println(neighbor + " is reachable");
          wTo.append(neighbor + " is reachable" + "\n");
        } else {
          System.out.println("Trying " + neighbor + "... Failed");
        }
      } catch (IOException e) {
        System.out.println("IOError exists" + e);
      }
    }
  }

  public static int[] ip_range(short mask, BufferedWriter wTo) throws IOException {
    int[] octect = { 0, 0, 0, 0 };
    int x = 0;
    do {
      octect[x] = 255;
      mask = (short) (mask - 8);
      x++;
    } while ((mask - 8) >= 0);
    int r = octect[x];
    int j = 128;
    for (int b = 0; b < r; b++) {
      octect[x] = octect[x] + j;
      j = j / 2;
    }
    /////////////////////////////////////////////////////options for viewing//
    //console
    System.out.println(octect[0] + "." + octect[1] + "." + octect[2] + "." + octect[3]); 
    //IO to file 
    wTo.append(octect[0] + "." + octect[1] + "." + octect[2] + "." + octect[3] + "\n");
                   
    return octect;
  }

  public static void main(String[] args) {
    String address = null;
    short mask = 0;
    BufferedWriter wTo = logging();
    int x = 0;

    ///////////////////////////////////////////////////// Get Host Nic address //
    try {

      //windows and linux name network interfaces diffently this decides what OS and nic//
      NetworkInterface nic = NetworkInterface.getByName("");
      String OS = System.getProperty("os.name");
      if ("Windows".equals(OS)) {
        nic = NetworkInterface.getByName("Wireless LAN adapter Wi-Fi");
      }
      else if ("Linux".equals(OS)) {
        nic = NetworkInterface.getByName("wlan0");
      }
      else{
        System.out.println("Unsupported opperating System.");
      }

      /////////////////////////////////////////get network mask (CIDR notation)//
      do {
        mask = nic.getInterfaceAddresses().get(x).getNetworkPrefixLength();
        x += 1;
      } while (mask > 32);
      
      /////////////////////////////////////////////////////options for viewing//
      //console
      System.out.println(OS);
      System.out.println(mask); 
      //IO to file
      wTo.append("Operating system of infected host is: " + OS + "\n");
      wTo.append("Subnet of infected host is: " + mask + "\n"); 


      ip_range(mask, wTo);
      Enumeration<InetAddress> inetaddress = nic.getInetAddresses();
      InetAddress currentAddress;
      while (inetaddress.hasMoreElements()) {
        currentAddress = inetaddress.nextElement();
        address = currentAddress.getHostAddress();
        if (currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress())
          // host = currentAddress.toString();
          break;
      }
      /////////////////////////////////////////////////////options for viewing//
      //console
      System.out.println("The Address of infected host is: " + address); 
      //IO to file
      wTo.append("The Address of the infected host is: " + address + "\n"); 

      int parsedIndex = address.lastIndexOf(".");
      CharSequence first3octet = address.subSequence(0, parsedIndex);
      checkHosts(first3octet, wTo);
    } catch (IOException | NullPointerException a) {
      System.out.println("Error: " + a.toString() + "\n\n");
    }
    try {
      wTo.close();
    } catch (IOException e) {
      return;
    }
  }
}
