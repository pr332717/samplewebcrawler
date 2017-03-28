package com.prasant.crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerLeg
{
    // We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private Document htmlDocument ;
 
    public boolean crawl(String url, WebsiteLink websiteLink)
    {
        try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            String requestedDomain = connection.request().url().getHost() ;
            	
            Document document = connection.get();
            this.htmlDocument = document;
            if(connection.response().statusCode() == 200) // 200 is the HTTP OK status code
                                                          // indicating that everything is great.
            {
                System.out.println("\n**Visiting** Received web page at " + url);
            }
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("**Failure** Retrieved something other than HTML");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            Set<WebsiteLink> sameDomainLinks = new HashSet<>();
            Set<String> externalLinks = new HashSet<>();
        	Set<String> staticResourcesLinks  = new HashSet<>();
        	
            for(Element link : linksOnPage)
            {
                String absurl = link.absUrl("href");
               // System.out.println("found:" + absurl);
                if(absurl.contains(requestedDomain)){
                	Elements childElements = link.children();
                	if(childElements.iterator().hasNext())
                	{
                		staticResourcesLinks.add(absurl);
                	}
                	else 
                		sameDomainLinks.add(new WebsiteLink(absurl));
                }else{
                	externalLinks.add(absurl);
                }
            }
            websiteLink.setExternalLinks(externalLinks);
            websiteLink.setSameDomainLinks(sameDomainLinks);
            websiteLink.setStaticResourcesLinks(staticResourcesLinks);
            return true;
        }
        catch(IOException ioe)
        {
            // We were not successful in our HTTP request
            return false;
        }
    }

}