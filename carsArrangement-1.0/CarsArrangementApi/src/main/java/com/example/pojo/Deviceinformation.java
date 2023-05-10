package com.example.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName deviceinformation
 */
@Data
public class Deviceinformation implements Serializable {
    private String deviceid;

    private String temperture;

    private String humidity;

    private String devicename;

    private String deviceworkplace;

    private String coverimagefilename;

    private static final long serialVersionUID = 1L;
}