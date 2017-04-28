package voigaittest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FindUrl {
    
    public FindUrl(){
        try {
            String url = "https://jsoup.org/download";
            URI uri = new URI(url);
            System.out.println(uri.getHost());
            
             Document doc = Jsoup.connect(url).get();
            Elements questions = doc.select("a[href]");

            String lastUrl = null;
            
            for (Element link : questions) {
                System.out.println("Link = " + link);
                 String hrefUrl = link.attr("abs:href");
                 System.out.println(hrefUrl);
                 
            }
            
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(FindUrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FindUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    


/**
 * Represents a Web crawler, which collects all visited unique domains
 */
public class DomainCrawler {
    /**
     * The set, which contains all unique visitedDomains
     */
    private volatile Set<String> visitedDomains;

    /**
     * Creates DomainCrawler with empty set
     */
    public DomainCrawler() {
        this.visitedDomains = new HashSet<String>();
    }

    /**
     * Visits the url and finds all href's on it and calls itself on href's with unvisited domain
     *
     * @param url String with url to visit by crawler
     * @throws IOException
     */
    public void processPage(String url) throws IOException {
        Set<String> domainsFromPage = new HashSet<String>();
        String domain = getDomainName(url);
        if (domain != null) {
            visitedDomains.add(domain);
            System.out.println(domain);
        } else {
            return;
        }
        if (url.contains(".pdf") || url.contains("#") || url.contains(":80") || url.contains(".jpg") || url.contains(".pdf") || url.contains(".jpg"))
            return;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements questions = doc.select("a[href]");

            for (Element link : questions) {
                String hrefUrl = link.attr("abs:href");
                String hrefDomain = getDomainName(hrefUrl);
                if (hrefUrl.contains(".pdf") || hrefUrl.contains("#")
                        || hrefUrl.contains(":80")
                        || hrefUrl.contains(".jpg")
                        || hrefUrl.contains(".pdf") || hrefUrl.contains(".jpg") || !domainsFromPage.contains(hrefDomain)) {
                    domainsFromPage.add(hrefDomain);
                    if (hrefDomain != null && !visitedDomains.contains(hrefDomain)) {
                        processPage(hrefUrl);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Some mistake happened with url" + url);
        }
    }

    /**
     * Tests Crawler how many unique domains it will find for specified time and root page
     *
     * @param seconds time for test in seconds
     * @param url     root page
     * @return number of unique domains
     * @throws IOException
     */
    public int testCrawler(int seconds, String url) throws IOException {
        long start = System.currentTimeMillis();
        long end = start + seconds * 1000;
        while (System.currentTimeMillis() < end) {
            processPageForTest(url, end);
        }
        return visitedDomains.size();
    }

   /* public static void main(String[] args) throws IOException {
        DomainCrawler crawler = new DomainCrawler();
        int size = crawler.testCrawler(60, "http://stackoverflow.com/questions/5834808/designing-a-web-crawler");
        System.out.println("Total size: " + size);
    }*/

    /**
     * Process Page for Test(check if times is over, ends working)
     *
     * @param url root page
     * @param end time, to end crawling
     * @throws IOException
     */
    private void processPageForTest(String url, long end) throws IOException {

        if (System.currentTimeMillis() >= end) {
            return;
        }
        Set<String> domainsFromPage = new HashSet<String>();
        String domain = getDomainName(url);
        if (domain != null) {
            visitedDomains.add(domain);
            System.out.println(domain);
        } else {
            return;
        }
        try {
            Document doc = Jsoup.connect(url).get();
            Elements questions = doc.select("a[href]");

            for (Element link : questions) {
                String hrefUrl = link.attr("abs:href");
                String hrefDomain = getDomainName(hrefUrl);
                if (!domainsFromPage.contains(hrefDomain) || hrefUrl.contains(".pdf") || hrefUrl.contains("#")
                        || hrefUrl.contains(":80")
                        || hrefUrl.contains(".jpg")
                        || hrefUrl.contains(".pdf") || hrefUrl.contains(".jpg")) {
                    domainsFromPage.add(hrefDomain);
                    if (hrefDomain != null && !visitedDomains.contains(hrefDomain)) {
                        processPageForTest(hrefUrl, end);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Some mistake happened with url" + url);
        }
    }

    /**
     * Gets domain from url
     *
     * @param url String with url
     * @return domain
     */
    private  String getDomainName(String url) {
        URI uri = null;
        String domain = null;
        try {
            uri = new URI(url);
            domain = uri.getHost();
            domain = domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (Exception e) {
            return null;
        }
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

}


    
}
