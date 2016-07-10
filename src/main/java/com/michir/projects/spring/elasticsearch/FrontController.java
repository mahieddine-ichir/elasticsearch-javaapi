package com.michir.projects.spring.elasticsearch;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class FrontController {

	@Autowired
	private SearchService service;
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public QuerySearchResult search(@RequestParam("q") String query, @RequestParam("index") String index, @RequestParam("field") String field) throws Exception {
		return service.search(index, field, query);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HttpStatus> index(@RequestParam("index") String index, @RequestParam("type") String type, @RequestBody Map<String, Object> source) throws Exception {
		service.index(index, type, source);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
}
