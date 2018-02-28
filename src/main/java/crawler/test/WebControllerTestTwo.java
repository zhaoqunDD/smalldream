package crawler.test;

import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 微博爬虫demo  失败
 *2018年2月27日-下午4:51:56
 * @author 赵群
 */
public class WebControllerTestTwo extends BreadthCrawler {

	
	
	public WebControllerTestTwo(String crawlPath) {
		super(crawlPath, false);
		
		/* 获取新浪微博的cookie，账号密码以明文形式传输，请使用小号 
        try {
			String cookie = WeiboCN.getSinaCookie("15962723902", "zhaoqun123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

	@Override
	public void visit(Page page, CrawlDatums arg1) {
		int pageNum = Integer.valueOf(page.meta("pageNum"));
        /* 抽取微博 */
        Elements weibos = page.select("div.c[id]");
        for (Element weibo : weibos) {
            System.out.println("第" + pageNum + "页\t" + weibo.text());
        }
		
	}
	
	public static void main(String[] args) {
		WebControllerTestTwo crawler = new WebControllerTestTwo("crawl_weibo");

	        /* 对某人微博前5页进行爬取 */
	        for (int i = 1; i <= 10; i++) {
	            String seedUrl = "http://weibo.cn/zhouhongyi?vt=4&page=" + i;
	            crawler.addSeedAndReturn(seedUrl).meta("pageNum", i);
	        }
	        try {
				crawler.start(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
	
}


class WeiboCN {

    /**
     * 获取新浪微博的cookie，这个方法针对weibo.cn有效，对weibo.com无效 weibo.cn以明文形式传输数据，请使用小号
     *
     * @param username 新浪微博用户名
     * @param password 新浪微博密码
     * @return
     * @throws Exception
     */
    public static String getSinaCookie(String username, String password) throws Exception {
        StringBuilder sb = new StringBuilder();
        HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER);

        driver.setJavascriptEnabled(true);
        driver.get("https://passport.weibo.cn/signin/login");
        driver.executeScript("document.getElementById('loginWrapper').style.display = 'block'");
        WebElement mobile = driver.findElementByCssSelector("input#loginName");
        mobile.sendKeys(username);
        WebElement pass = driver.findElementByCssSelector("input#loginPassword");
        pass.sendKeys(password);
        WebElement submit = driver.findElementByCssSelector("a#loginAction");
        submit.click();
        String result = concatCookie(driver);
        System.out.println("Get Cookie: " + result);
        driver.close();

        if (result.contains("SUB")) {
            return result;
        } else {
            throw new Exception("weibo login failed");
        }
    }

    public static String concatCookie(HtmlUnitDriver driver) {
        Set<Cookie> cookieSet = driver.manage().getCookies();
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookieSet) {
            sb.append(cookie.getName() + "=" + cookie.getValue() + ";");
        }
        String result = sb.toString();
        return result;
    }
}

