package com.es.springboot_es.service;

import com.alibaba.fastjson.JSON;
import com.es.springboot_es.pojo.Content;
import com.es.springboot_es.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈搜索业务〉
 *
 * @author chenyan
 * @create 2020/4/9
 * @since 1.0.0
 */
@Service
public class ConcentServiceImpl {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 解析数据放入 es
    public boolean parseContent(String keywords) throws Exception {
        List<Content> contents = new HtmlParseUtil().parseJDSearch(keywords);

        // 查询到的数据放入 es
        // 1. 获得批量请求的 request
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        // 2. 将数据一条条放入 request
        for(int i = 0; i < contents.size(); i++){
            System.out.println(JSON.toJSONString(contents.get(i)));
            bulkRequest.add(new IndexRequest("goods_list")
                       .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
        }
        // 3.提交 request
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        // 4.验证是否失败（失败返回 true）
        // bulk.hasFailures()

        // 没有失败 return true;
        return !bulk.hasFailures();
    }

    // 获取数据，实现搜索
    public List<Map<String,Object>> searchPage(String keyword,int pageNo,int pageSize) throws IOException {
        if(pageNo<=0){
            pageNo = 1;
        }
        //1.创建 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //2.搜索的索引，得到 SearchRequest
        SearchRequest searchRequest = new SearchRequest("goods_list");
        //3.分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        //4.去匹配对应字段(title) - keyword
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //5.执行搜索,得到结果 searchResponse
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //6.解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for(SearchHit documentFields:searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    // 获取数据，实现高亮搜索
    public List<Map<String,Object>> searchPageHighlighter(String keyword,int pageNo,int pageSize) throws IOException {
        if(pageNo<=0){
            pageNo = 1;
        }
        //1.创建 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //2.搜索的索引，得到 SearchRequest
        SearchRequest searchRequest = new SearchRequest("goods_list");
        //3.分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        //4.去匹配对应字段(title) - keyword
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //搜索词高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        //是否多个关键字高亮
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        //5.执行搜索,得到结果 searchResponse
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //6.解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for(SearchHit hit:searchResponse.getHits().getHits()){

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap(); //得到原来的结果
            //将原字段替换
            if(title != null){
                Text[] fragments = title.fragments();
                String n_title = "";
                for(Text text : fragments){
                    n_title +=  text;
                }
                sourceAsMap.put("title",n_title);
            }
            list.add(sourceAsMap);
        }
        return list;
    }

}
