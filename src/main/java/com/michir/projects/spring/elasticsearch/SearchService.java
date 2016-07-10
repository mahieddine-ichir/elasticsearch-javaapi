package com.michir.projects.spring.elasticsearch;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	private static final Log LOG = LogFactory.getLog(SearchService.class);

	@Autowired
	private Client client;
	
	public String index(String index, String type, Map<String, Object> toIndex) {
		return client.prepareIndex(index, type)
			.setSource(toIndex)
			.get().getId();
	}
	
	public QuerySearchResult search(String index, String field, String query) {
		LOG.info(">>> Searching "+query);
		final QuerySearchResult queries = new QuerySearchResult();
		queries.setQueried(field);
		queries.setQuery(query);
		
		client.prepareSearch(index)
			//.setTypes("log4j")
			.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			.setQuery(query.contains("*") || query.contains("?") ? QueryBuilders.wildcardQuery(field, query) : QueryBuilders.matchQuery(field, query))
			.execute()
			.actionGet()
			.getHits()
			.forEach(h -> {
				queries.getResults().add(h.getSource());
			});
		return queries;
	}
}
