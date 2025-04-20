package com.neo.platform.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product") // Specify the table name
public class Product {

    @TableId(type = IdType.AUTO) // Use auto-increment ID
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @TableField(fill = FieldFill.INSERT) // Automatically set on insert
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) // Automatically set on insert/update
    private LocalDateTime updateTime;

    @TableLogic // Enable logical delete
    private Integer deleted;

    /**
     * Check if the product is in stock.
     * This is a placeholder domain method.
     * You would implement the actual logic based on your inventory system.
     * @return true if in stock, false otherwise
     */
    public boolean isInStock() {
        // TODO: Implement actual stock check logic
        return true; // Placeholder
    }

    /**
     * Apply a discount to the product price.
     * This is a placeholder domain method.
     * You would implement the actual logic based on your business rules.
     * @param discountPercentage The discount percentage (e.g., 0.10 for 10%)
     */
    public void applyDiscount(BigDecimal discountPercentage) {
        if (this.price != null && discountPercentage != null && discountPercentage.compareTo(BigDecimal.ZERO) >= 0 && discountPercentage.compareTo(BigDecimal.ONE) <= 0) {
            BigDecimal discountAmount = this.price.multiply(discountPercentage);
            this.price = this.price.subtract(discountAmount);
        }
    }
}