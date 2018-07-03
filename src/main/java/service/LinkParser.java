package service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkParser {
	
	private static Logger logger = LoggerFactory.getLogger(LinkParser.class);

    final static Pattern pattern = Pattern.compile("<a href=\"(.+?)\"");
   

    public static void parseLink(String line, ConcurrentHashMap<String, Long> map) {
    	    logger.debug("parsing");
            final Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String link = matcher.group(1);
                Long count = map.get(link);
                if (count != null) {
                    count++;
                    map.put(link, count);
                } else {
                    map.put(link, 1L);
                }

            }
        }   
}