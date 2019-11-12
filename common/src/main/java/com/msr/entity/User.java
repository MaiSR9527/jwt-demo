package com.msr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: maishuren
 * @Date: 2019/11/12 10:55
 */
@Data
@AllArgsConstructor
public class User {

    private String mobile;
    private String name;
    private String psd;
    private String role;
}
