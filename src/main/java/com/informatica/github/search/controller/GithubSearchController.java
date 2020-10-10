package com.informatica.github.search.controller;

import com.informatica.github.search.model.GithubProject;
import com.informatica.github.search.service.GithubSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The GithubSearchController is the entry point to expose the REST endpoints
 * to access the GitHub Search using the user requested query
 */
@RestController
public class GithubSearchController {

    Logger logger = LoggerFactory.getLogger(GithubSearchController.class);

    @Autowired
    private GithubSearchService githubSearchService;

    /**
     * REST endpoint which accepts language param as query string
     * to call the GithubSearchService to get Git repositories using GitHub API
     *
     * @param language
     * @return
     */
    @RequestMapping("/github/search")
    public List<GithubProject> searchProjects(@RequestParam String language) {
        List<GithubProject> list = null;
        try {
            logger.info("In searchProjects ...");
            list = githubSearchService.searchGitHubRepositories(language);
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }
        return list;
    }

    /**
     * REST endpoint which accepts language param as path variable
     * to call the GithubSearchService to get Git repositories using GitHub API
     *
     * @param language
     * @return
     */
    @RequestMapping("/github/search/{language}")
    public List<GithubProject> searchProjectsUsingPathVariable(@PathVariable String language) {
        List<GithubProject> list = null;
        try {
            logger.info("In searchProjects ...");
            list = githubSearchService.searchGitHubRepositories(language);
        } catch (Exception exc) {
            logger.error(exc.getMessage());
        }
        return list;
    }
}
