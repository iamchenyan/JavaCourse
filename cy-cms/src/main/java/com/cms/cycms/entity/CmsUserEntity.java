package com.cms.cycms.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.cms.cycms.foundation.BaseEntity;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户副表entity〉
 *
 * @author chenyan
 * @create 2020/3/21
 * @since 1.0.0
 */
@Getter
@Setter
public class CmsUserEntity extends BaseEntity<Integer> {
    private String username;
    private Integer status;
    private Boolean admin;
    private String lastLoginIp;
    private String sessionId;
    //超级管理员
    private Boolean administrator;


}
