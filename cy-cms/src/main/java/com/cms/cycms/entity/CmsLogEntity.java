package com.cms.cycms.entity;

import com.cms.cycms.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chenyan
 * @create 2020/3/24
 * @since 1.0.0
 */
@Getter
@Setter
public class CmsLogEntity extends BaseEntity<Integer> {
    private Integer userId;
    private String username;
    private String loginIp;
    private String url;
    private String content;
}
