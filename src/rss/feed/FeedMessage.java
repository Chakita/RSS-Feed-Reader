package rss.feed;

/*
 * Represents one RSS message
 */
public class FeedMessage {

    String Title;
    String Description;
    String URL;
    

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getLink() {
        return URL;
    }

    public void setLink(String url) {
        this.URL = url;
    }


    

    @Override
    public String toString() {
        return "Feeds: \n Title=" + Title +"\n"+ "Description=" +"\n"+ Description+"\n"
                + "Link=" + URL ;
    }

}