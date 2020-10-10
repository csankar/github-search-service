package com.informatica.github.search.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatica.github.search.configuration.ConfigProperties;
import com.informatica.github.search.model.GithubProject;
import com.informatica.github.search.service.GithubSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The GithubSearchServiceImpl service calls GitHub search API
 * to get repository data which has specified string
 * which is requested by the user
 *
 * @author Sankar Chandran
 * @version 1.0
 * @since 2020-10-10
 */
@Component
public class GithubSearchServiceImpl implements GithubSearchService {

    private static final String JSON_PROPERTY_ITEMS = "items";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static RestTemplate restTemplate = new RestTemplate();
    private Logger logger = LoggerFactory.getLogger(GithubSearchServiceImpl.class);
    @Autowired
    private ConfigProperties configProperties;

    /**
     * @param language
     * @return
     * @throws Exception
     */
    public List<GithubProject> searchGitHubRepositories(String language) throws Exception {
        try {
            String json = searchGitHub(language);
            if (json != null) {
                return parseResults(json);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    /**
     * Parses the GutHub response JSON into custom JSON GithubProject
     *
     * @param json
     * @return
     * @throws Exception
     */
    private List<GithubProject> parseResults(String json) throws Exception {
        JsonNode readTree = objectMapper.readTree(json);
        JsonNode jsonNode = readTree.get(JSON_PROPERTY_ITEMS);
        return objectMapper.readValue(jsonNode.toString(), new TypeReference<List<GithubProject>>() {
        });
    }

    /**
     * Makes REST call to GitHub Search API to search for repositories
     * using the language value which is requested by the user
     *
     * @param language
     * @return
     */
    private String searchGitHub(String language) throws Exception {
        logger.info("Calling GitHub Search ...");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(String.format(configProperties.getUrl(), language), String.class);
        logger.info("Git Hub response : {}", responseEntity.getStatusCode());
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity.getBody();
        }
        logger.info("No repositories found ...");
        return null;
    }
}
