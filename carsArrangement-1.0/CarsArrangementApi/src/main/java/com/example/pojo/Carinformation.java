package com.example.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName carinformation
 */
@Data
public class Carinformation implements Serializable {
    private String carid;

    private String carbrand;

    private String cartype;

    private Double speed;

    private String runsnumber;

    private String tyrepressure;

    private String seat;

    private String usetime;

    private String coverimagefilename;

    private static final long serialVersionUID = 1L;
}