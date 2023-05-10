package com.example.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName admin
 */
@Data
public class Admin implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private static final long serialVersionUID = 1L;
}