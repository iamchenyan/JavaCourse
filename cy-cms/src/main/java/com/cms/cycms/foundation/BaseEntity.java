package com.cms.cycms.foundation;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity<PK> implements Serializable {

    private PK id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

}
