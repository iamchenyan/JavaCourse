package com.es.springboot_es.controller;

import com.es.springboot_es.service.ConcentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈商品 搜索〉
 *
 * @author chenyan
 * @create 2020/4/9
 * @since 1.0.0
 */
@RestController
public class GoodsConcentController {

    @Autowired
    private ConcentServiceImpl concentService;

    // 将数据存入 es
    @GetMapping("/parse/{keyword}")
    public boolean parse(@PathVariable("keyword") String keyword) throws Exception {
        return concentService.parseContent(keyword);
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String,Object>> search(@PathVariable("keyword") String keyword,
                                           @PathVariable("pageNo") int pageNo,
                                           @PathVariable("pageSize")int pageSize) throws IOException {
        return concentService.searchPageHighlighter(keyword,pageNo,pageSize);
    }


}
