package com.cs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: szh
 * @since:
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg implements Serializable {
    private String input1;
    private String input2;
}
