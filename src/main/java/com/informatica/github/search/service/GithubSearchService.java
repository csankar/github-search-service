package com.informatica.github.search.service;

import com.informatica.github.search.model.GithubProject;

import java.util.List;

public interface GithubSearchService {
    List<GithubProject> searchGitHubRepositories(String language) throws Exception;
}
