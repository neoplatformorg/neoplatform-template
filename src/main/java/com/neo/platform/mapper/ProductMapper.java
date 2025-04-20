package com.neo.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.platform.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    // You can add custom mapper methods here if needed
}