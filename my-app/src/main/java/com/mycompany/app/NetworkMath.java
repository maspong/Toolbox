import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.function.IntToDoubleFunction;
import java.lang.Integer;

public class NetworkMath {

  public List<String> networkRange(String ipAddress) {
    List<String> addresses = new ArrayList<>();
    Boolean cidr = false;
    Boolean customRange = false;
    Boolean hasCidr = ipAddress.contains("/");
    Boolean hasCustom = ipAddress.contains("-");

    if (hasCidr == true) {
      cidrRange(ipAddress);
    }
    if (hasCustom == true) {
      addresses = customRange(ipAddress);
    }
    return addresses;
  }

  public List<String> cidrRange(String cidraddress) {
    List<String> addresses = new ArrayList<>();
    long max = 2147483648L;
    double numberofAddresses;
    ////// ToDo
    for(int x=0;x<(Integer.parseInt(cidraddress));x++){
      numberofAddresses = max / java.lang.Math.pow(2, (Integer.parseInt(cidraddress)));
    }
    return addresses;
  }

  public List<String> customRange(String customrange) {
    List<String> addresses = new ArrayList<>();
    String[] octet = { "0", "0", "0", "0", "0" };
    String delim = "[.-]+";
    int a = 0;
    Boolean done = true;
    octet = customrange.split(delim);
    for (int x = Integer.parseInt(octet[3]); x <= Integer.parseInt(octet[4]); x++) {
      addresses.add(octet[0] + "." + octet[1] + "." + octet[2] + "." + Integer.toString(x));
    }

    return addresses;
  }
}