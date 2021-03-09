package com.csc.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.csc.algafood.api.model.CookeriesXmlWrapper;
import com.csc.algafood.domain.model.Cookery;
import com.csc.algafood.domain.repository.CookeryRepository;
import com.csc.algafood.domain.service.CookeryService;

@RestController
@RequestMapping("/cookeries")
public class CookeryController {

	@Autowired
	private CookeryRepository repository;
	
	@Autowired
	private CookeryService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cookery> findAll(){
		return repository.findAll();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CookeriesXmlWrapper findAllXml(){
		return new CookeriesXmlWrapper(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public Cookery findById(@PathVariable("id") Long id) {
		
		return service.findOrThrow(id);

	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Cookery cookery){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cookery));
		
	}
	
	@PutMapping("/{id}")
	public Cookery update(@RequestBody Cookery cookery
			, @PathVariable Long id){
		
		Cookery foundCookery = service.findOrThrow(id);
		BeanUtils.copyProperties(cookery, foundCookery, "id");
		return service.save(foundCookery);
		
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete(@PathVariable("id") Long id){
//		
//		try {
//			
//			service.delete(id);
//			return ResponseEntity.noContent().build();
//			
//		} catch (EntityNotFoundException e) {
//			
//			return ResponseEntity.notFound().build();
//			
//		} catch (EntityInUseException e) {
//			
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//			
//		}
//		
//	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id){	
		service.delete(id);				
	}
	
	@GetMapping("/exists")
	public ResponseEntity<?> existsByName(@RequestParam("name") String name) {
		
		return ResponseEntity.ok(service.existsByName(name));
		
		
	}
	
	
}
