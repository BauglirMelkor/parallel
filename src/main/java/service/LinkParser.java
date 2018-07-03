package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkParser {

    final static Pattern pattern1 = Pattern.compile("<a href=\"(.+?)\"");
    //final static Pattern pattern2 = Pattern.compile("<a href(.+?)</a>");

    public static void parseLink(String line, ConcurrentHashMap<String, Long> map) {

        List<Pattern> patterns = new ArrayList<>();
        patterns.add(pattern1);
    //    patterns.add(pattern2);
        for (Pattern pattern : patterns) {
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
}
