# headline-searcher
Project to search news headlines, both now and in the past

Currently this only searches the Dailymail rss feeds (found here: http://www.dailymail.co.uk/home/article-2684527/RSS-Feeds.html)

I will soon be including the Huffington post and the Gurdian as well. 

##Install / usage
This runs on Springboot. It requires Java 8, and uses gralde as it's build system. Gradle comes packaged with this project. 

You'll also need an instance of Mongo DB running on the standard port for the data to be loaded into.

Navigate to the root directory of this project, then use ```gradlew bootrun``` to start it. Api's are set out below, and you can find the homepage of the project at localhost:9001

##Api
 
```/``` -> Home page, which contains a search bar and the results. Still to come:
- Pagination (currently working, but not accessable from the front end)
- New Sources
- Colours for different new sources
- Tick boxes to filter the sources
- Date / better indication of source


```/article/{id}``` -> Search for article based on id

```/article?searchTerm={term}&page={page number}&size={no. results per page}``` -> Paginated search. The three request params aren't required. Without a search term, it will try to get all, but paginated. 
If no items are found with your search term, a 200 is returned, but with empty content.
You also get information assoicated with the pages:

```totalElements: 500,
last: false,
totalPages: 167,
size: 3,
number: 2,
sort: null,
first: false,
numberOfElements: 3```

These are the default from Spring pagination.

```/crawlers/all``` -> runs the crawler procedure. Currently only crawls Daily Mail Rss feed latest stories. 

##Todo / future

```/stats``` end point that allows you to get information on how many articles etc are currently stored, and when each srouce was updated last.

Multiple crawlers, for different sites, and tie them into the front end search.

Also, i'd like to add some graphs for words used, updates per day, and a few other items. I'd also like to collect the article themselves, NOT just the headlines, so more analysis could be performed.


 
