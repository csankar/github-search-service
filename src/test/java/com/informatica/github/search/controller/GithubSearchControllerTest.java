package com.informatica.github.search.controller;

import com.informatica.github.search.model.GithubProject;
import com.informatica.github.search.service.GithubSearchService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GithubSearchController.class)
public class GithubSearchControllerTest {

    Logger logger = LoggerFactory.getLogger(GithubSearchControllerTest.class);
    @Mock
    Iterator<GithubProject> iterator;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GithubSearchService githubSearchService;
    private List<GithubProject> githubProjects = new ArrayList<GithubProject>();
    private GithubProject githubProject;
    private GithubProject.Owner owner;

    @Before
    public void init() {
        githubProject = new GithubProject();
        githubProject.setId("101");
        githubProject.setName("acs-common");
        githubProject.setUrl("http://www.adobe.com/acs-common");
        owner = new GithubProject.Owner();
        owner.setLogin("acs-accoun");
        githubProject.setOwner(owner);
        githubProjects.add(githubProject);
    }

    @Test
    public void searchProjects() throws Exception {
        when(githubSearchService.searchGitHubRepositories(anyString())).thenReturn(githubProjects);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/github/search?language=acs");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        logger.info("************ RESPONSE ************");
        logger.info(mvcResult.getResponse().getContentAsString());
        String expected = "[{\"id\":\"101\",\"name\":\"acs-common\",\"url\":\"http://www.adobe.com/acs-common\",\"owner\":{\"login\":\"acs-accoun\"}}]";
        Assert.assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

}
