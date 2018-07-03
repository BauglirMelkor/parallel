import org.junit.Test;
import util.Utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class UtilityTest {

    @Test(expected = MalformedURLException.class)
    public void getMalformedURLException() throws MalformedURLException {
        Utility.getUrl("");
    }

    

    @Test(expected = IOException.class)
    public void getIOException() throws IOException {
        URL url=new URL("tes");
        Utility.getConnection(url);
    }


}
