package com.example.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 */
@Data
public class QueryVo implements Serializable {
    private Integer current;
    private Integer size;
    private String search;
}
