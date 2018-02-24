package crawler.test;

import org.jsoup.nodes.Document;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 爬虫框架WebCollectorTest
 *2018年2月9日-下午2:38:29
 * @author 赵群
 */
public class WebCollectorTest extends BreadthCrawler{

	public WebCollectorTest(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		
		//添加种子
		this.addSeed("http://news.hfut.edu.cn/list-1-1.html");

		//限定爬去范围
	    this.addRegex("http://news.hfut.edu.cn/show-.*html");
	    this.addRegex("-.*\\.(jpg|png|gif).*");
	    this.addRegex("-.*#.*");
	}

	public void visit(Page page, CrawlDatums arg1) {
		String url = page.url();
	    if (page.matchUrl("http://news.hfut.edu.cn/show-.*html")) {
	        /*we use jsoup to parse page*/
	        Document doc = page.doc();

	        /*extract title and content of news by css selector*/
	        String title = page.select("div[id=Article]>h2").first().text();
	        String content = page.select("div#artibody", 0).text();

	        System.out.println("URL:\n" + url);
	        System.out.println("title:\n" + title);
	        System.out.println("content:\n" + content);
		
	}
	}
	
	public static void main(String[] args) throws Exception {
		WebCollectorTest crawler = new WebCollectorTest("crawl", true);
	    crawler.setThreads(50);
	    //crawler.setTopN(100);
	    //crawler.setResumable(true);
	    /*start crawl with depth of 4*/
	    crawler.start(4);
	    }

	
}
