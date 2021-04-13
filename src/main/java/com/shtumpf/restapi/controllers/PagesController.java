package com.shtumpf.restapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shtumpf.restapi.models.PageRepository;
import com.shtumpf.restapi.models.data.Page;

@RestController
@RequestMapping(path = "/pages", produces = "application/json")
@CrossOrigin(origins = "*") // any domain can access this api
public class PagesController {

	@Autowired
	private PageRepository pageRepo;

	@GetMapping("/all")
	public Iterable<Page> pages() {

		List<Page> pages = pageRepo.findAllByOrderBySortingAsc();

		return pages;
	}

	@GetMapping("/{slug}")
	public ResponseEntity<Page> page(@PathVariable String slug) {

//		 it is not good as with page == null client receives empty body page
//		 and status code 200(which means - 'ok')
//		 Page page = pageRepo.findBySlug(slug); 
//		 if(page == null) return null; 
//		  
//		 return page;

		
		 Optional<Page> optPage = pageRepo.findBySlug(slug);
		 
		 if(optPage.isPresent()) { 
			 return new ResponseEntity<>(optPage.get(), HttpStatus.OK); 
		 }
		 
		 return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 
	}

}
