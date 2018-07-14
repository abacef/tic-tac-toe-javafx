package ip_address;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

public class GetHost {

    Enumeration<InetAddress> addresses;

    public GetHost() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                addresses = iface.getInetAddresses();
            }
        }
        catch (SocketException e) {
            System.out.println("Huh! It seems you are not connected to the " +
                    "internet");
            e.printStackTrace();
        }
    }

    public boolean hasMoreElements() {
        return addresses.hasMoreElements();
    }

    public String nextAddress() {
        return addresses.nextElement().getHostAddress();
    }

    public String getMyHost() {
        try {
            URL url = new URL("https://www.iplocation.net/find-ip-address");
            BufferedReader br = new BufferedReader(new InputStreamReader(url
                    .openStream()));
            String line;
            System.out.println();
            while ((line = br.readLine()) != null) {
                if (line.contains("Your IP Address is")) {
                    line = line.substring(110, line.length());
                    line = line.substring(0, line.indexOf('<'));
                    return line;
                }
            }
        }
        catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}
