package de.ooad.rss.tests;

import de.ooad.rss.model.Feed;
import de.ooad.rss.model.FeedMessage;
import de.ooad.rss.read.RSSFeedParser;

public class ReadTest {
    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "https://www.feedotter.com/feed/");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }

    }
}