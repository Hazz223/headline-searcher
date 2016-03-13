# headline-searcher
Project to search news headlines, both now and in the past

Currently this only searches the Dailymail rss feeds (found here: http://www.dailymail.co.uk/home/article-2684527/RSS-Feeds.html)

I will soon be including the Huffington post and the Gurdian as well. 

##Install / usage
This runs on Springboot. It requires Java 8, and uses gralde as it's build system. Gradle comes packaged with this project. 

You'll also need an instance of Mongo DB running on the standard port for the data to be loaded into.

Navigate to the root directory of this project, then use ```gradlew bootrun``` to start it. Api's are set out below, and you can find the homepage of the project at localhost:9001

##Api
 
