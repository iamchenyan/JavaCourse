package com.es.springboot_es.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br>
 * 〈搜索到时的数据对象信息〉
 *
 * @author chenyan
 * @create 2020/4/9
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private String img;
    private String title;
    private String price;
    // ...
}
