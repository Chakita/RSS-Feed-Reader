package rss.read;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import rss.feed.Feed;
import rss.feed.FeedMessage;

public class RSSFeedParser {
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";

    List<URL> urls= new ArrayList<>();

    public RSSFeedParser(List<String> urls) {
    	for( int i=0;i<urls.size();i++)
    	{		
        try {
            URL url = new URL(urls.get(i));
            this.urls.add(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    }
    public List<Feed> readFeed() {
        List<Feed> feeds = new ArrayList<>();
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values initial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String pubdate = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            List <InputStream> instreams = read();
            for(int i=0;i<instreams.size();i++)
            { XMLEventReader eventReader = inputFactory.createXMLEventReader(instreams.get(i));
            // read the XML document
            while (eventReader.hasNext()) 
            {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) 
                {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart)
                    {
                    case ITEM:
                        if (isFeedHeader)
                        {
                            isFeedHeader = false;
                            feed = new Feed(title, link, description, language,
                                    copyright, pubdate);
                        }
                        event = eventReader.nextEvent();
                        break;
                    case TITLE:
                        title = getCharacterData(event, eventReader);
                        break;
                    case DESCRIPTION:
                        description = getCharacterData(event, eventReader);
                        break;
                    case LINK:
                        link = getCharacterData(event, eventReader);
                        break;
                    case LANGUAGE:
                        language = getCharacterData(event, eventReader);
                        break;
                    case PUB_DATE:
                        pubdate = getCharacterData(event, eventReader);
                        break;
                    case COPYRIGHT:
                        copyright = getCharacterData(event, eventReader);
                        break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        FeedMessage message = new FeedMessage();
                        message.setDescription(description);
                        message.setLink(link);
                        message.setTitle(title);
                        feed.getMessages().add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
            feeds.add(feed);
            isFeedHeader = true;
        } 
        }
        catch (XMLStreamException e) 
        {
            throw new RuntimeException(e);
        }
        return feeds;
        }
     
        
    

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private List<InputStream> read() {
    	List<InputStream> streams =  new ArrayList<>();
    	InputStream stream;
    	for(int i=0;i<urls.size();i++)
        {   URL url = urls.get(i);
    		try 
    		{  
    		  	stream= url.openStream();
    		  	streams.add(stream);
             } 
    		catch (IOException e) 
    		{
            throw new RuntimeException(e);
              }
    		
        }
    	return streams;
    }
}