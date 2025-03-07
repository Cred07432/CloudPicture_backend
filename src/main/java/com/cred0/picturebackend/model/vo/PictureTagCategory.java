package com.cred0.picturebackend.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 标签分类列表
 */
@Data
public class PictureTagCategory {

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 分类列表
     */
    private List<String> categoryList;
}
