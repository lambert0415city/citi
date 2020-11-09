package com.cs.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author: szh
 * @since:
 */
@Builder
@Data
public class Author {
    private Integer id;
    private String name;
    private Integer age;
}