package com.prasant.crawler;

public class CrawlerTest
{
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)
    {
        Crawler crawler = new Crawler();
        if(args.length ==0 || args.length > 1)
        	System.err.println("This program expects only one argument");
        else{
        	crawler.search(args[0]);
        }
    }
}