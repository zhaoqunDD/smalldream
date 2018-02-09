package crawler.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * jsoup爬虫
 * jsoup 是一款Java 的HTML解析器，
 * 可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，
 * 可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。
 *2018年2月8日-下午5:29:08
 * @author 赵群
 */
public class JsoupTest {

	public static void main(String[] args) {
		/*
		String url ="<html><head><title> 这里是字符串内容</title></head"+ ">"+"<body><p class='p1'> 这里是 jsoup 作用的相关演示</p></body></html>";
		Document doc = (Document) Jsoup.parse(url);
		
		Elements links = ((Element) doc).select("p[class]");
	       for(Element link:links){
	        String linkclass = link.className();
	            String linkText = link.text();
	            System.out.println(linkText);
	            System.out.println(linkclass);
	        }
	
	*/
		
		try {
			String url ="http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=js%20is%20not%20funcation";
			Document doc = (Document) Jsoup.connect(url).get();
			
			//得到html的所有东西
			Element content = doc.getElementById("content");
			/*
			Elements links = content.getElementsByTag("a");
			 Elements pngs = doc.select("img[src$=.png]");
			 
			 for (Element link : links) {
			String linkHref = link.attr("href");
			String linkText = link.text();
			System.out.println(linkText);
			}
			 */
			 System.out.println(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	
	
}
