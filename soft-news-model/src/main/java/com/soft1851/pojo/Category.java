package com.soft1851.pojo;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;

/**
 * @author zhao
 * @className Category
 * @Description TODO
 * @Date 2020/11/24
 * @Version 1.0
 **/
public class Category {
    @Id
    private Integer id;

    private String name;

    @Column(name = "tag_color")
    private String tagColor;

    /**
     * id
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**获取分类名，比如：科技，人文，历史，汽车等等...**/
    public String getName() {
        return name;
    }
    /**设置分类名，比如：科技，人文，历史，汽车等等...**/
    public void setName(String name) {
        this.name = name;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }
}
