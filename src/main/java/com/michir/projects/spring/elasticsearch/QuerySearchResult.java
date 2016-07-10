package com.michir.projects.spring.elasticsearch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class QuerySearchResult {

	private String queried;
	
	private String query;
	
	private Collection<Map<String, Object>> results = new ArrayList<>();

	public String getQueried() {
		return queried;
	}

	public void setQueried(String queried) {
		this.queried = queried;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Collection<Map<String, Object>> getResults() {
		return results;
	}

	public void setResults(Collection<Map<String, Object>> results) {
		this.results = results;
	}



}
