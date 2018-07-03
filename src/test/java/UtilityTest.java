import org.junit.Test;
import util.Utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class UtilityTest {

    @Test(expected = MalformedURLException.class)
    public void getMalformedURLException() throws MalformedURLException {
        Utility.getUrl("");
    }
    
    @Test
    public void getConnectionUrl() throws MalformedURLException {
        URL url=Utility.getUrl("http://www.google.com");
        assert(url!=null);
    }    

    @Test(expected = IOException.class)
    public void getIOException() throws IOException {
        URL url=new URL("");
        Utility.getConnection(url);
    }
    
    @Test
    public void getConnection() throws IOException {
        URL url=Utility.getUrl("http://www.google.com");
        HttpURLConnection connection=Utility.getConnection(url);
        assert(connection!=null);
    }


}
