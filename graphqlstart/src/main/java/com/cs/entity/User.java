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
public class User {
    private int id;
    private String name;
}
