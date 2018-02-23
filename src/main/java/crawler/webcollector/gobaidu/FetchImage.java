package crawler.webcollector.gobaidu;

import java.io.File;

import org.apache.commons.lang.exception.ExceptionUtils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;

/**
 * 爬取百度照片
 *2018年2月9日-下午3:34:08
 * @author 赵群
 */
public class FetchImage extends BreadthCrawler {

	public FetchImage(String crawlPath) {
		super(crawlPath, true);
		
		//添加种子URL
        addSeed("https://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gbk&word=%B8%A3&fr=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=111111");
        //限定爬取范围
	}

	public void visit(Page page, CrawlDatums arg1) {
		//根据http头中的Content-Type信息来判断当前资源是网页还是图片
        String contentType = page.contentType();
        
        if(contentType!=null && contentType.startsWith("image")){
            //从Content-Type中获取图片扩展名
            String extensionName=contentType.split("/")[1];
            try {
                byte[] image = page.content();
                //根据图片MD5生成文件名
                String fileName = String.format("%s.%s",11, extensionName);
                File imageFile = new File("/E:/photos/", fileName);
                FileUtils.write(imageFile, image);
                System.out.println("保存图片 "+page.url()+" 到 "+ imageFile.getAbsolutePath());
            } catch (Exception e) {
                //ExceptionUtils.fail(e);
            }
        }
		
	}
	
	public static void main(String[] args) {
		FetchImage demoImageCrawler = new FetchImage("crawl");
        demoImageCrawler.setRequester(new OkHttpRequester());
        //设置为断点爬取，否则每次开启爬虫都会重新爬取
        demoImageCrawler.setResumable(true);
        try {
			demoImageCrawler.start(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
