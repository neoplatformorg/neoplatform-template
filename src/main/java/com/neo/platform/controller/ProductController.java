package com.neo.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neo.platform.dto.ProductDTO;
import com.neo.platform.entity.Product;
import com.neo.platform.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Tag(name = "产品管理", description = "产品相关接口")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "根据ID获取产品详情", responses = {
            @ApiResponse(responseCode = "200", description = "成功获取产品详情"),
            @ApiResponse(responseCode = "404", description = "产品未找到")
    })
    @GetMapping("/{id}")
    public ProductDTO getProductById(
            @Parameter(description = "产品ID", required = true) @PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            return productDTO;
        }
        return null; // Or throw an exception
    }

    @Operation(summary = "获取产品列表（分页）", responses = {
            @ApiResponse(responseCode = "200", description = "成功获取产品列表")
    })
    @GetMapping
    public IPage<ProductDTO> getProducts(
            @Parameter(description = "页码", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "每页数量", example = "10") @RequestParam(defaultValue = "10") int size) {
        IPage<Product> productPage = productService.page(new Page<>(page, size));
        return productPage.convert(product -> {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            return productDTO;
        });
    }

    @Operation(summary = "创建新产品", responses = {
            @ApiResponse(responseCode = "200", description = "产品创建成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @PostMapping
    public boolean createProduct(
            @Parameter(description = "产品信息", required = true) @RequestBody ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return productService.save(product);
    }

    @Operation(summary = "更新产品信息", responses = {
            @ApiResponse(responseCode = "200", description = "产品更新成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "404", description = "产品未找到")
    })
    @PutMapping("/{id}")
    public boolean updateProduct(
            @Parameter(description = "产品ID", required = true) @PathVariable Long id,
            @Parameter(description = "更新后的产品信息", required = true) @RequestBody ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        product.setId(id); // Ensure the ID is set for update
        return productService.updateById(product);
    }

    @Operation(summary = "删除产品", responses = {
            @ApiResponse(responseCode = "200", description = "产品删除成功"),
            @ApiResponse(responseCode = "404", description = "产品未找到")
    })
    @DeleteMapping("/{id}")
    public boolean deleteProduct(
            @Parameter(description = "产品ID", required = true) @PathVariable Long id) {
        return productService.removeById(id);
    }
}