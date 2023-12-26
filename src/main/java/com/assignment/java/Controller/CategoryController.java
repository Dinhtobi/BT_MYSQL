package com.assignment.java.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.java.Payload.Request.CategoryRequest;
import com.assignment.java.Service.ICategoryService;
import com.assignment.java.Utils.AppConstants;

import io.micrometer.common.lang.Nullable;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categoryRequest){
		
		return ResponseEntity.ok(categoryService.addCategory(categoryRequest));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @RequestBody CategoryRequest categoryRequest){
		return ResponseEntity.ok(categoryService.updateCategory(id, categoryRequest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){
		return ResponseEntity.ok(categoryService.deleteCategory(id));
	}
	
	@GetMapping
	public ResponseEntity<?> getCategory(@RequestParam(name = "from", required = false) @Nullable @DateTimeFormat( pattern = "yyyy-MM-dd") Date from,
			  @RequestParam(name = "to",required = false) @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			  @RequestParam(name = "searchField",required = false) String searchField,
			  @RequestParam(name = "keyword",required = false) String keyword,
			  @RequestParam(name = "sortType",required = false) String sortType,
			  @RequestParam(name = "sortField",required = false) String sortField,
			  @RequestParam(name = "size",required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
			  @RequestParam(name = "page",required = false , defaultValue = AppConstants.DEFAULT_PAGE) int page){
		return ResponseEntity.ok(categoryService.filter(from, to, searchField, keyword, sortType, sortField, size, page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") int id){
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}
	
}
