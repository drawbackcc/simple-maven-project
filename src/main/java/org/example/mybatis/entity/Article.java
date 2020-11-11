package org.example.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/2 18:05
 */
@Data
public class Article implements Serializable {
    private Integer aid;
    private Integer uid;
    private String title;
    private String content;
}
