# OOADJ Project
RSS readers are used to aggregate news.
Users can subscribe to RSS feeds from the websites and sources of interest to them, and use an RSS reader to scan headlines and read articles from a variety of sources
<br>
The RSS Feed Reader works by periodically checking for updates on the list of URLs provided to it (stored in the form of a CSV file). 
When new content is published on the sites, the RSS Feed Reader takes the details of the new post and stores them on a database (PostgreSQL).
The list of sites for which the content has been updated can be viewed on the UI. 
