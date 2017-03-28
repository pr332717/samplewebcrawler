package com.prasant.crawler;

import java.util.Iterator;

public class Crawler {
    private CrawlerLeg leg = new CrawlerLeg();
    
   
    public void search(String url)
    {
    	WebsiteLink sl = new WebsiteLink(url);
    	System.out.println("Crawling main url:" + url);
    	traverseAllLink(sl);
    }
    	
    private void traverseAllLink(WebsiteLink link)
    {
    	leg.crawl(link.getParentUrl(), link);
    	System.out.println("found " + link.getExternalLinks().size() + " external links and " + link.getSameDomainLinks().size() + " samedomain links and " + link.getStaticResourcesLinks().size() + " static resources links for "+ link.getParentUrl());
    	Iterator<WebsiteLink> it = link.getSameDomainLinks().iterator();
    	while(it.hasNext()){
    		traverseAllLink(it.next());
    	}
    }

}
