package com.programacho.wikipediamcpserver.infrastructure;

import com.programacho.wikipediamcpserver.domain.Page;
import com.programacho.wikipediamcpserver.domain.SearchResult;
import com.programacho.wikipediamcpserver.domain.SearchResults;
import com.programacho.wikipediamcpserver.domain.WikipediaClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class WikipediaClientImpl implements WikipediaClient {

    private final RestClient restClient;

    public WikipediaClientImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<SearchResult> searchPages(String query, Integer limit) {
        SearchResults searchResults = restClient.get()
                .uri("/search/page?q={query}&limit={limit}", Map.of(
                        "query", query,
                        "limit", limit
                ))
                .retrieve()
                .body(SearchResults.class);

        return searchResults.pages();
    }

    @Override
    public Page getPageSource(String title) {
        return restClient.get()
                .uri("/page/{title}", title.replace(" ", "_"))
                .retrieve()
                .body(Page.class);
    }
}
