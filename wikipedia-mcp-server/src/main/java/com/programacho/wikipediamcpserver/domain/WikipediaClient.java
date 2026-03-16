package com.programacho.wikipediamcpserver.domain;

import java.util.List;

public interface WikipediaClient {

    List<SearchResult> searchPages(String query, Integer limit);

    Page getPageSource(String title);
}
