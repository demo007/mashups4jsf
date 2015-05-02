**Abstract**

Creating mashups inside web applications is complex. The web developers have to use intensive Javascript, perform custom RSS, ATOM, and JSON parsing and write a great deal of integration code in order to integrate with Mashup services.

Adding to these complexities, the web developers have also to learn the low level APIs provided by the mashup service providers.

Mashups4JSF is an open source project that aims at integrating Mashup services with both Java and JSF world. JSF developers will be able to construct rich and customized mashups by using simple tags. Mashups4JSF target is to have an integrated set of Mashup tags and APIs. Mashups4JSF allows exporting the Java Enterprise Application data as Mashup feeds by annotating the application domain classes with @Feed annotation.

**Mashups4JSF 1.0.0 provides the following features**
  * Creating RSS Mashup feed sources in JSF applications using @Feed annotations.  [See example](Creating_your_mashup_feed_producer_simply_using_Mashups4JSF.md).
  * Creating ATOM Mashup feed sources in JSF applications using @Feed annotations.  [See example](Creating_your_mashup_feed_producer_simply_using_Mashups4JSF.md).
  * Reading RSS, Atom and JSON feed formats inside JSF application by using [rssFeedReader](rssFeedReader.md), [atomFeedReader](atomFeedReader.md), and [jsonFeedReader](jsonFeedReader.md) components.
  * Creating Rich Google Maps with all of map details (Markers, Notes, Graphics, ...etc) declaratively using [GMaps4JSF](http://code.google.com/p/gmaps4jsf).
  * Getting yahoo weather information using [yahooWeather](yahooWeather.md) component.
  * Performing public search in YouTube Videos using [youTubeVideoList](youTubeVideoList.md) component.
  * Performing public search in Google using [googleSearchList](googleSearchList.md) component.
  * Performing public search in Twitter using [twitterSearchList](twitterSearchList.md) component.
  * Performing public search in Digg using [diggSearchList](diggSearchList.md) component.
  * Pretty Integration with Google Location Services.

If you want an easy integration with the popular Google Maps inside your JSF applications, then use GMaps4JSF http://code.google.com/p/gmaps4jsf. Here is a video session about it below.

<a href='http://www.youtube.com/watch?feature=player_embedded&v=J8eFDTP6GIg' target='_blank'><img src='http://img.youtube.com/vi/J8eFDTP6GIg/0.jpg' width='425' height=344 /></a>

**Adding Mashups4JSF to your Maven application**
  * all what you should do is adding this to your pom.xml:
```
    <repositories>
    	...
        <repository>
            <id>googlecode.com</id>
            <url>http://mashups4jsf.googlecode.com/svn/trunk/mashups4jsf-repo</url>
        </repository>
        
    </repositories>    

    <dependencies>
	...    
        <dependency>
            <groupId>com.googlecode.mashups4jsf</groupId>
            <artifactId>mashups4jsf-core</artifactId>
            <version>x.y.z</version>
        </dependency>  
    </dependencies>
```
  * adding the dependency of any version of JSF (As Mashups4JSF works with any version of JSF), we should also add only one dependency to (Rome 0.9).
```
    <dependencies>
	...   
	<dependency>
	    <groupId>rome</groupId>
	    <artifactId>rome</artifactId>
	    <version>0.9</version>	
	</dependency> 	
    </dependencies>
```

**Main Components**
  * Check the [Components](Components.md) page.

**Online Demos**
  * Mashups4JSF Demo: http://www.mashups4jsf.com.

**How To Use The Library**
  * [HowToUseTheLibrary](HowToUseTheLibrary.md) shows how to configure and use the library components.