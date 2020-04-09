package com.es.springboot_es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈es配置〉
 *
 * @author chenyan
 * @create 2020/4/9
 * @since 1.0.0
 */
@Configuration
public class ElasticSearchConfigClient {

    @Bean // 放入spring容器
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "http")));
        return client;
    }


}
