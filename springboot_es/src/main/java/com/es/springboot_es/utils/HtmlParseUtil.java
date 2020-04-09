package com.es.springboot_es.utils;

import com.es.springboot_es.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈解析网页工具类〉
 *
 * @author chenyan
 * @create 2020/4/8
 * @since 1.0.0
 */
public class HtmlParseUtil {

    /**
     * 根据 搜索词 解析得到网页里的信息
     * @param keyword
     * @return
     * @throws Exception
     */
    public List<Content> parseJDSearch(String keyword) throws Exception {
        // 请求地址 https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword="+keyword;
        // 解析网页 获得 html页面对象-Document
        //Todo 只支持英文 (?中文)
        Document document = Jsoup.parse(new URL(url), 30000);
        // 得到列表块
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();

        for (Element el : elements){
            // 由于很多使用懒加载 src的url只是临时 / 找到懒加载的属性 source-data-lazy-img
            String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);

            goodsList.add(content);
        }
        return goodsList;
    }



}
