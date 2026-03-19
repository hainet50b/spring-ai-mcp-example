package com.programacho.wikipediamcpserver.application;

import com.programacho.wikipediamcpserver.domain.Page;
import com.programacho.wikipediamcpserver.domain.SearchResult;
import com.programacho.wikipediamcpserver.domain.WikipediaClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WikipediaTools {

    private final WikipediaClient wikipediaClient;

    public WikipediaTools(WikipediaClient wikipediaClient) {
        this.wikipediaClient = wikipediaClient;
    }

    @Tool(name = "search-pages", description = "Search Wikipedia pages for the provided search terms.")
    public List<SearchResult> searchPages(
            @ToolParam(description = "Search terms") String query,
            @ToolParam(description = "Number of pages to return") Integer limit
    ) {
        return wikipediaClient.searchPages(query, limit);
    }

    @Tool(name = "get-page-source", description = "Get the content of a Wikipedia page.")
    public Page getPageSource(
            @ToolParam(description = "Title of the article") String title
    ) {
        return wikipediaClient.getPageSource(title);
    }
}
