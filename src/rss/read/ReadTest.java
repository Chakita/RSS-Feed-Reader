package rss.read;
import rss.csv.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rss.feed.Feed;
import rss.feed.FeedMessage;

public class ReadTest {
    public static void main(String[] args) {
    	CSVReader csv = CSVReader.getInstance("RSS links - Sheet1.csv");
    	List<String> urls = new ArrayList<>();
		try {
			urls = csv.readData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        RSSFeedParser parser = new RSSFeedParser(urls);
        List<Feed>feeds = parser.readFeed();
        Feed feed=null;
        for(int i=0;i<feeds.size();i++)
        {   feed = feeds.get(i);
        	System.out.println(feed.printDetails());
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }

    }
}
}