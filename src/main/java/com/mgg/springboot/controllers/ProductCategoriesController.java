package com.mgg.springboot.controllers;

import java.util.Map;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgg.springboot.dao.models.ProductCategories;
import com.mgg.springboot.services.ProductCategoriesService;

import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "product-categories", description = "the Product Categories API")
@RequestMapping("product-categories")
public class ProductCategoriesController extends BaseController<ProductCategoriesService, ProductCategories>{
    // ============= swagger =================================================
    @Operation(summary = "Add a new Product Categories", description = "", tags = { "Product Categories" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact created", content = @Content(schema = @Schema(implementation = ProductCategories.class))),

            @ApiResponse(responseCode = "400", description = "Invalid input"),

            @ApiResponse(responseCode = "409", description = "Contact already exists") })
    // ============= swagger =================================================

    @GetMapping("")
    public ResponseEntity<Object> getAll(
            @QuerydslPredicate(root = ProductCategories.class) Predicate predicate,
            @RequestParam(name = "$skip", defaultValue = "0") int $skip,
            @RequestParam(name = "$limit", defaultValue = "10") int $limit,
            @RequestParam Map<String, String> requestParams) {
        return super.baseAll(predicate, $skip, $limit, requestParams);
    }
}
