import java.util.ArrayList;

public class NetworkMath{
    public ArrayList<String> networkRange(String ipAddress){
        ArrayList<String> addresses = new ArrayList<>();
        Boolean cidr = false;
        Boolean customRange = false;
        Boolean hasCidr = ipAddress.contains("/");
        Boolean hasCustom = ipAddress.contains("-");
        
        if (hasCidr == true){
            cidrRange(ipAddress);
        }
        if(hasCustom == true){
            addresses = customRange(ipAddress);
        }
        return addresses;
    }
    public ArrayList<String> cidrRange(String cidraddress){
        ArrayList<String> addresses = new ArrayList<>();
        return addresses;
    }
    public ArrayList<String> customRange(String customrange){
        ArrayList<String> addresses = new ArrayList<>();
        String[] octet = {"0","0","0","0","0"};
        String delim = "[.-]+";
        int a = 0;
        Boolean done = true;
        octet = customrange.split(delim);
            for (int x = Integer.parseInt(octet[3]); x<= Integer.parseInt(octet[4]); x++){
                addresses.add(octet[0]+"."+octet[1]+"."+octet[2]+"."+ Integer.toString(x));
            }
        
        return addresses;
    }
}