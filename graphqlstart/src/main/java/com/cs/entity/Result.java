package com.cs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: szh
 * @since:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String msg;
    private int code;
    //private Object data;
}
