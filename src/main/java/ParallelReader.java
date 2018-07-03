import service.LinkParser;
import util.FileWriter;
import util.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class ParallelReader {

    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException {
        ConcurrentHashMap<String, Long> linkMap = new ConcurrentHashMap<>();

        //Uncomment it if you want to test the application without importing SSL files.
       // Utility.byPassSsl();

        URL url = Utility.getUrl("https://www.wikiwand.com/en/Europe");

        HttpURLConnection conn = Utility.getConnection(url);

       
        try {
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                Stream<String> linesStream = br.lines();
                linesStream.parallel().filter(p -> p.contains("<a href"))
                        .parallel().forEach((p -> LinkParser.parseLink(p, linkMap)));
               
                FileWriter fileWriter = new FileWriter();

                fileWriter.writeToFile("C:\\Users\\TKA\\Documents\\links.txt", linkMap);

            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
