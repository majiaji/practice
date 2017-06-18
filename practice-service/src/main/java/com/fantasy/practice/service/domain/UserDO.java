package com.fantasy.practice.service.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by jiaji on 16/9/28.
 */
public class UserDO {
    @JSONField(name = "user_name")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
