package com.neo.platform.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.platform.entity.Product;
import com.neo.platform.mapper.ProductMapper;
import com.neo.platform.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    
    @Override
    public <E extends IPage<Product>> E page(E page) {
        return lambdaQuery()
            .orderByDesc(Product::getId)
            .page(page);
    }
}