package crawler.webcollector.go7k7k;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import util.DownImage;

/**
 * 爬取百度照片失败；找了个简单的游戏页面练手
 * 将http://www.7k7k.com页面上的所有图片下载到本地
 *2018年2月27日-下午5:21:44
 * @author 赵群
 */
public class Fetch7k7k extends BreadthCrawler {

	public Fetch7k7k(String crawlPath) {
		super(crawlPath, true);
		
		// 添加种子URL
		this.addSeed("http://www.7k7k.com");
		this.setThreads(30);
	}

	@Override
	public void visit(Page page, CrawlDatums arg1) {
		
		Elements imgSrc = page.select("img");
		
		/*页面img 对应的src各不同,存在以下情况：
		 * <img src="http://i5.7k7kimg.cn/cms/cms10/20180117/174118_7485.jpg" alt="7k7k赛尔号" class="pic" width="66" height="50">
		 * <img _src="http://i4.7k7kimg.cn/cms/cms10/20180205/112133_8360.jpg" alt="荒野行动" class="pic" width="66" height="50">
		 * <img lz_src="http://i3.7k7kimg.cn/cms/cms11/b/7/104068_1704.jpg" class="pic" width="76" height="77" alt="惊险过山车2">
		 */
			for(Element img : imgSrc){
				
				//三则调优
				String imgUrl = img.attr("src").equals("")? 
						img.attr("_src").equals("")?  img.attr("lz_src") : img.attr("_src")
						        		: img.attr("src");
//						String imgUrl = img.attr("src");
//						if(imgUrl.equals("")){
//							imgUrl = img.attr("_src");
//						}
//						if(imgUrl.equals("")){
//							imgUrl = img.attr("lz_src");
//						}
						
				String title = img.attr("alt");
				System.out.println(title+":"+imgUrl);
				DownImage.download(imgUrl, title+".jpg", "D:\\7k7k");
						
						
			}
		
	}
	
	public static void main(String[] args) {
		Fetch7k7k demoImageCrawler = new Fetch7k7k("7k7k");
		demoImageCrawler.setRequester(new OkHttpRequester());
		// 设置为断点爬取，否则每次开启爬虫都会重新爬取
		demoImageCrawler.setResumable(false);
		try {
			demoImageCrawler.start(3);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
