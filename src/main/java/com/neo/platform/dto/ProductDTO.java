package com.neo.platform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // You can add more fields or methods as needed for data transfer
}