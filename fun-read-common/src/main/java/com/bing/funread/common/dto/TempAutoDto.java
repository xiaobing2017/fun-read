package com.bing.funread.common.dto;

import java.util.Date;

/**
 * Description:临时权限对象
 * Author: zhangfusheng
 * Date: 2018/4/2 下午2:23
 */
public class TempAutoDto {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date date;

    public TempAutoDto() {
    }

    public TempAutoDto(Long userId, Date date) {
        this.userId = userId;
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
