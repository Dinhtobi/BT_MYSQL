package com.assignment.java.Service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.java.Entities.Category;
import com.assignment.java.Exception.BadRequestException;
import com.assignment.java.Exception.NotFoundException;
import com.assignment.java.Payload.Request.CategoryRequest;
import com.assignment.java.Payload.Response.CategoryResponse;
import com.assignment.java.Payload.Response.DeleteResponse;
import com.assignment.java.Payload.Response.DetailCategoryResponse;
import com.assignment.java.Payload.Response.DetailProductResponse;
import com.assignment.java.Payload.Response.PageResponse;
import com.assignment.java.Repository.CategoryRepository;
import com.assignment.java.Repository.Projections.DetailCategoryProjection;
import com.assignment.java.Service.ICategoryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	private CategoryRepository categoryRepository; 
	
	 @PersistenceContext
	 private EntityManager entityManager;
	
	@Override
	public CategoryResponse addCategory(CategoryRequest categoryRequest) {
		Category newCategory = new Category();
		newCategory.setName(categoryRequest.getName());
		newCategory.setCreatedAt(new Date());
		categoryRepository.save(newCategory);
		Category categorySave = categoryRepository.findByName(categoryRequest.getName()).orElseThrow(() -> new NotFoundException("Category not found"));
		return new CategoryResponse(categorySave.getName(), categorySave.getId(),categorySave.getCreatedAt());
	}

	@Override
	public CategoryResponse updateCategory(int id ,CategoryRequest categoryRequest) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
		category.setName(categoryRequest.getName());
		categoryRepository.save(category);
		Category categorySave = categoryRepository.findByName(categoryRequest.getName()).orElseThrow(() -> new NotFoundException("Category not found"));
		return new CategoryResponse(categorySave.getName(), categorySave.getId(), categorySave.getCreatedAt(), categorySave.getUpdatedAt());
	}

	@Override
	public DeleteResponse deleteCategory(int id) {
		categoryRepository.deleteById(id);
		return new DeleteResponse("Deleted", "Category with id = " +id +" deleted");
	}

	@Override
	public PageResponse<CategoryResponse> filter(Date from, Date to, String searchField, String keyword,
			String sortType, String sortField, int size, int page) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		Root<Category> root = criteriaQuery.from(Category.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(from != null && to != null) {
			Path<Date> fieldCreateAt = root.get("createdAt");
			Predicate predicateFrom = criteriaBuilder.greaterThanOrEqualTo(fieldCreateAt, from);
			Predicate predicateTo = criteriaBuilder.lessThanOrEqualTo(fieldCreateAt, to);
			predicates.add(predicateTo);
			predicates.add(predicateFrom);
		}
		
		if(keyword != null) {
			Path<String> fieldName =null;
			if(searchField.equals("name")) {
				fieldName = root.get("name");
			}else if(searchField.equals("id")) {
				 fieldName = root.get("id");
			}else {
				throw new BadRequestException("Search Field incorrect!");
			}
			
			Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(fieldName), "%" + keyword.toLowerCase() +"%");
			predicates.add(predicate);
		}
		
		if(sortField == null || sortField.isBlank()) {
			sortField = "name";
		}
		
		if(sortType == null) {
			sortType = "asc";
		}
		Path<Object> sortRoute = null;
		try {
			sortRoute = root.get(sortField);
		} catch (Exception e) {
			throw new BadRequestException("Sort Field incorrect!");
		}
		
		Order order = "asc".equalsIgnoreCase(sortType) ? criteriaBuilder.asc(sortRoute) : criteriaBuilder.desc(sortRoute);
		criteriaQuery.orderBy(order);
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Category> typedQuery = entityManager.createQuery(criteriaQuery);
		
		int totalResult = typedQuery.getResultList().size();
		typedQuery.setFirstResult(page * size);
		typedQuery.setMaxResults(size);
		List<Category> categories = typedQuery.getResultList();
		PageRequest pagerRequest = PageRequest.of(page, size);
		List<CategoryResponse> categoryResponses = new ArrayList<CategoryResponse>();
		
		categories.forEach(category -> {
			categoryResponses.add(new CategoryResponse(category.getName(), category.getId(),category.getCreatedAt()));
		});
		
		Page<CategoryResponse> pagedResponse = new PageImpl<>(categoryResponses, pagerRequest, totalResult );
		
		return new PageResponse<CategoryResponse>( pagedResponse.getContent(), pagedResponse.getNumber(), pagedResponse.getSize(), pagedResponse.getTotalElements()
				, pagedResponse.getTotalPages(), pagedResponse.isLast());
	}

	@Override
	public DetailCategoryResponse getCategoryById(int id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
		List<DetailCategoryProjection> list = categoryRepository.getDetailCategory(category.getId());
		List<DetailProductResponse> products = new ArrayList<DetailProductResponse>();
				list.forEach(detailCategory -> {
			products.add(new DetailProductResponse(detailCategory.getNameProduct(), detailCategory.getPriceProduct(), detailCategory.getNumberProduct(), detailCategory.getName()));
		});
		return new DetailCategoryResponse(category.getId(), category.getName(), products);
	}

	
}
