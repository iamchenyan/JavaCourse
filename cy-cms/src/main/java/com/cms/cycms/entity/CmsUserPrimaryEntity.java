package com.cms.cycms.entity;

import lombok.Getter;
import lombok.Setter;
import com.cms.cycms.foundation.BaseEntity;


/**
 * 〈用户主表映射〉<br>
 * 〈〉
 *
 * @author chenyan
 * @create 2020/3/20
 * @since 1.0.0
 */
@Getter
@Setter
public class CmsUserPrimaryEntity extends BaseEntity<Integer> {
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
}
