import java.net.*;
import java.io.*;
import java.util.*;
import java.net.Inet4Address;
import java.io.*;
//import java.net.InterfaceAddress;
public class Wu_taiChin {
     public static BufferedWriter logging(){
          boolean written = false;
	  BufferedWriter wTo = null;
          do{
                try{
                     wTo = new BufferedWriter(new FileWriter("Briefcase.txt"));
                     wTo.write("**************Report ******************\n");
                }catch(IOException e){
                     written = false;
                }
          }while(written = false);
          return wTo;
     }

    public static void checkHosts(CharSequence first3octet, BufferedWriter wTo)throws IOException {
       int timeout=1000;
       for (int i=1;i<255;i++){
           String neighbor=first3octet + "." + i;
           try{
               if (InetAddress.getByName(neighbor).isReachable(timeout)){
                   System.out.println(neighbor + " is reachable");
		   wTo.append(neighbor + " is reachable"+"\n");
               }
	       else{
         	   System.out.println("Trying "+ neighbor+ "... Failed");
               }
           }catch (IOException e) {
               System.out.println("IOError exists" + e);
           }
       //wTo.close();
       //wTo.();
       }
    }
    public static void HostAddress() {

    }
    public static void main(String[] args) {
         //Get internal address
        String address ="";
        String host = "";
	short mask = 0;
	int[] octect;
	BufferedWriter wTo = logging();
	//ip_range findrange = new ip_range();
	//NetworkInterface nic = NetworkInterface.getByName("");
        try{
	     NetworkInterface nic = NetworkInterface.getByName("");
	     String OS = System.getProperty("os.name");
	     System.out.println(OS);                                   // comment out for file writing
	     wTo.append("Operating system of infected host is: " + OS +"\n");// comment out for system viewing
	     if("Windows".equals(OS)){
                  nic = NetworkInterface.getByName("Wireless LAN adapter Wi-Fi");
	     }
	     if ("Linux".equals(OS)){
		  nic = NetworkInterface.getByName("wlan0");
	     }
	     int x = 0;
	     do {
	          mask = nic.getInterfaceAddresses().get(x).getNetworkPrefixLength();
	          x += 1;
	     }while(mask > 32);
	     System.out.println(mask);                                // comment out for file writing
	     wTo.append("Subnet of infected host is: " + mask +"\n");       // comment out for system viewing
	     ip_range(mask, wTo);
             Enumeration<InetAddress> inetaddress = nic.getInetAddresses();
             InetAddress currentAddress;
             //String subnetmask = currentAddress.getSubnetMask();
             //System.out.println(subnetmask);
	      while (inetaddress.hasMoreElements()) {
                   currentAddress = inetaddress.nextElement();
                   address = currentAddress.getHostAddress();
                   if (currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress())
                       //host = currentAddress.toString();
                       break;
             }
             System.out.println("The Address of infected host is: " + address);  //delete/comment out if you are not getering info localy
	     wTo.append("The Address of the infected host is: " + address+"\n");      //delete/commnet out if you are not needing to write to a file
             //System.out.println(currentAddress.getNetworkPrefixLength());
             int parsedIndex= address.lastIndexOf(".");
             CharSequence first3octet = address.subSequence(0,parsedIndex);
             checkHosts(first3octet, wTo); //get devices on the network
        }catch(IOException|NullPointerException a){
            System.out.println("Error: " + a.toString() + "\n\n");
        }
	try{
	wTo.close();
	}catch(IOException e){
	     return;
	}
    }
    public static int[] ip_range(short mask, BufferedWriter wTo)throws IOException {
     	 int[] octect = {0,0,0,0};
	 int x = 0;
	 int a = 0;
	 do{
	     octect[x] = 255;
	     mask = (short) (mask - 8);
	     x ++;
	 }while((mask-8)>=0);
	 int r = octect[x];
         int j= 128;
	 for(int b=0 ; b < r; b++){
	     octect[x] = octect[x] + j;
             j = j/2;
	 }
	 System.out.println(octect[0]+"."+octect[1]+"."+octect[2]+"."+octect[3]);  //comment out for file writing
	 wTo.append(octect[0]+"."+octect[1]+"."+octect[2]+"."+octect[3]+"\n");          // comment out for system viewing
     	 return octect;
    }
}
