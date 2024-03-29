package com.shtumpf.restapi.models;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shtumpf.restapi.models.data.Page;

public interface PageRepository extends JpaRepository<Page, Integer> {
	
	Optional<Page> findBySlug(String slug);
	
	Page findBySlugAndIdNot(String slug, int id);
	
	List<Page> findAllByOrderBySortingAsc();

}