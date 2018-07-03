import service.LinkParser;
import util.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class ParallelReader {

    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException {
        ConcurrentHashMap<String, Long> linkMap = new ConcurrentHashMap<>();

        Utility.byPassSsl();

        URL url = null;
        try {
            url = new URL("https://en.wikipedia.org/wiki/Europe");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("http-proxy.corporate.ge.com", 80));
            conn = (HttpURLConnection) url.openConnection(proxy);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Long startTime = System.nanoTime();
        try {
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                Stream<String> linesStream = br.lines();
                linesStream.parallel().filter(p->p.contains("<a href"))
                        .parallel().forEach((p -> LinkParser.parseLink(p, linkMap)));
                System.out.println("MAP:"+ linkMap.toString());

            }
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("TOTALTIME:" + totalTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
