import org.junit.Test;
import service.LinkParser;

import java.util.concurrent.ConcurrentHashMap;

public class LinkParserTest {

    ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    @Test
    public void parseNoLinks() {
        LinkParser.parseLink("aaaaa", map);
        assert(map.size()==0);
    }

    @Test
    public void parseOneLink() {
        LinkParser.parseLink("<html> <head> </head> <body> <a href=\"test.jsp\"</a>", map);
        assert(map.size()==1);
    }

    @Test
    public void parseTwoLinks() {
        LinkParser.parseLink("<html> <head> </head> <body> <a href=\"test.jsp\"</a> <a href=\"test2.jsp\"</a>", map);
        assert(map.size()==2);
    }

}
