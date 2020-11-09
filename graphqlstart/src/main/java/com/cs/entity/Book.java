package com.cs.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author: szh
 * @since:
 */
@Builder
@Data
public class Book {
    private Integer id;
    private String name;
    private Author author;
    private String publisher;
}