package com.informatica.github.search.service.impl;

import com.informatica.github.search.configuration.ConfigProperties;
import com.informatica.github.search.model.GithubProject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GithubSearchServiceImplTest {
    @InjectMocks
    GithubSearchServiceImpl githubSearchService;

    @Mock
    ConfigProperties configProperties;

    @Mock
    RestTemplate restTemplate;

    @Mock
    ResponseEntity responseEntity;
    // git hub response data for unit test
    private String gitHubRepo = "{\n" +
            "  \"total_count\": 1,\n" +
            "  \"incomplete_results\": false,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"id\": 538746,\n" +
            "      \"node_id\": \"MDEwOlJlcG9zaXRvcnk1Mzg3NDY=\",\n" +
            "      \"name\": \"ruby\",\n" +
            "      \"full_name\": \"ruby/ruby\",\n" +
            "      \"private\": false,\n" +
            "      \"owner\": {\n" +
            "        \"login\": \"ruby\",\n" +
            "        \"id\": 210414,\n" +
            "        \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjIxMDQxNA==\",\n" +
            "        \"avatar_url\": \"https://avatars3.githubusercontent.com/u/210414?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/ruby\",\n" +
            "        \"html_url\": \"https://github.com/ruby\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/ruby/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/ruby/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/ruby/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/ruby/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/ruby/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/ruby/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/ruby/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/ruby/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/ruby/received_events\",\n" +
            "        \"type\": \"Organization\",\n" +
            "        \"site_admin\": false\n" +
            "      },\n" +
            "      \"html_url\": \"https://github.com/ruby/ruby\",\n" +
            "      \"description\": \"The Ruby Programming Language [mirror]\",\n" +
            "      \"fork\": false,\n" +
            "      \"url\": \"https://api.github.com/repos/ruby/ruby\",\n" +
            "      \"forks_url\": \"https://api.github.com/repos/ruby/ruby/forks\",\n" +
            "      \"keys_url\": \"https://api.github.com/repos/ruby/ruby/keys{/key_id}\",\n" +
            "      \"collaborators_url\": \"https://api.github.com/repos/ruby/ruby/collaborators{/collaborator}\",\n" +
            "      \"teams_url\": \"https://api.github.com/repos/ruby/ruby/teams\",\n" +
            "      \"hooks_url\": \"https://api.github.com/repos/ruby/ruby/hooks\",\n" +
            "      \"issue_events_url\": \"https://api.github.com/repos/ruby/ruby/issues/events{/number}\",\n" +
            "      \"events_url\": \"https://api.github.com/repos/ruby/ruby/events\",\n" +
            "      \"assignees_url\": \"https://api.github.com/repos/ruby/ruby/assignees{/user}\",\n" +
            "      \"branches_url\": \"https://api.github.com/repos/ruby/ruby/branches{/branch}\",\n" +
            "      \"tags_url\": \"https://api.github.com/repos/ruby/ruby/tags\",\n" +
            "      \"blobs_url\": \"https://api.github.com/repos/ruby/ruby/git/blobs{/sha}\",\n" +
            "      \"git_tags_url\": \"https://api.github.com/repos/ruby/ruby/git/tags{/sha}\",\n" +
            "      \"git_refs_url\": \"https://api.github.com/repos/ruby/ruby/git/refs{/sha}\",\n" +
            "      \"trees_url\": \"https://api.github.com/repos/ruby/ruby/git/trees{/sha}\",\n" +
            "      \"statuses_url\": \"https://api.github.com/repos/ruby/ruby/statuses/{sha}\",\n" +
            "      \"languages_url\": \"https://api.github.com/repos/ruby/ruby/languages\",\n" +
            "      \"stargazers_url\": \"https://api.github.com/repos/ruby/ruby/stargazers\",\n" +
            "      \"contributors_url\": \"https://api.github.com/repos/ruby/ruby/contributors\",\n" +
            "      \"subscribers_url\": \"https://api.github.com/repos/ruby/ruby/subscribers\",\n" +
            "      \"subscription_url\": \"https://api.github.com/repos/ruby/ruby/subscription\",\n" +
            "      \"commits_url\": \"https://api.github.com/repos/ruby/ruby/commits{/sha}\",\n" +
            "      \"git_commits_url\": \"https://api.github.com/repos/ruby/ruby/git/commits{/sha}\",\n" +
            "      \"comments_url\": \"https://api.github.com/repos/ruby/ruby/comments{/number}\",\n" +
            "      \"issue_comment_url\": \"https://api.github.com/repos/ruby/ruby/issues/comments{/number}\",\n" +
            "      \"contents_url\": \"https://api.github.com/repos/ruby/ruby/contents/{+path}\",\n" +
            "      \"compare_url\": \"https://api.github.com/repos/ruby/ruby/compare/{base}...{head}\",\n" +
            "      \"merges_url\": \"https://api.github.com/repos/ruby/ruby/merges\",\n" +
            "      \"archive_url\": \"https://api.github.com/repos/ruby/ruby/{archive_format}{/ref}\",\n" +
            "      \"downloads_url\": \"https://api.github.com/repos/ruby/ruby/downloads\",\n" +
            "      \"issues_url\": \"https://api.github.com/repos/ruby/ruby/issues{/number}\",\n" +
            "      \"pulls_url\": \"https://api.github.com/repos/ruby/ruby/pulls{/number}\",\n" +
            "      \"milestones_url\": \"https://api.github.com/repos/ruby/ruby/milestones{/number}\",\n" +
            "      \"notifications_url\": \"https://api.github.com/repos/ruby/ruby/notifications{?since,all,participating}\",\n" +
            "      \"labels_url\": \"https://api.github.com/repos/ruby/ruby/labels{/name}\",\n" +
            "      \"releases_url\": \"https://api.github.com/repos/ruby/ruby/releases{/id}\",\n" +
            "      \"deployments_url\": \"https://api.github.com/repos/ruby/ruby/deployments\",\n" +
            "      \"created_at\": \"2010-02-27T15:55:23Z\",\n" +
            "      \"updated_at\": \"2020-10-10T18:57:43Z\",\n" +
            "      \"pushed_at\": \"2020-10-10T17:00:59Z\",\n" +
            "      \"git_url\": \"git://github.com/ruby/ruby.git\",\n" +
            "      \"ssh_url\": \"git@github.com:ruby/ruby.git\",\n" +
            "      \"clone_url\": \"https://github.com/ruby/ruby.git\",\n" +
            "      \"svn_url\": \"https://github.com/ruby/ruby\",\n" +
            "      \"homepage\": \"https://www.ruby-lang.org/\",\n" +
            "      \"size\": 231165,\n" +
            "      \"stargazers_count\": 17393,\n" +
            "      \"watchers_count\": 17393,\n" +
            "      \"language\": \"Ruby\",\n" +
            "      \"has_issues\": false,\n" +
            "      \"has_projects\": false,\n" +
            "      \"has_downloads\": false,\n" +
            "      \"has_wiki\": false,\n" +
            "      \"has_pages\": false,\n" +
            "      \"forks_count\": 4636,\n" +
            "      \"mirror_url\": null,\n" +
            "      \"archived\": false,\n" +
            "      \"disabled\": false,\n" +
            "      \"open_issues_count\": 222,\n" +
            "      \"license\": {\n" +
            "        \"key\": \"other\",\n" +
            "        \"name\": \"Other\",\n" +
            "        \"spdx_id\": \"NOASSERTION\",\n" +
            "        \"url\": null,\n" +
            "        \"node_id\": \"MDc6TGljZW5zZTA=\"\n" +
            "      },\n" +
            "      \"forks\": 4636,\n" +
            "      \"open_issues\": 222,\n" +
            "      \"watchers\": 17393,\n" +
            "      \"default_branch\": \"master\",\n" +
            "      \"score\": 1.0\n" +
            "    }]" +
            "}";

    @Test
    public void testSearchGitHubRepositories() throws Exception {
        ReflectionTestUtils.setField(githubSearchService, "restTemplate", restTemplate);
        when(configProperties.getUrl()).thenReturn("http://www.test.com");
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(responseEntity);
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(responseEntity.getBody()).thenReturn(gitHubRepo);
        List<GithubProject> githubProjects = githubSearchService.searchGitHubRepositories("acs-common");
        Assert.assertEquals(githubProjects.size(), 1);
        Assert.assertEquals(githubProjects.get(0).getId(), "538746");
        Assert.assertEquals(githubProjects.get(0).getName(), "ruby");
        Assert.assertEquals(githubProjects.get(0).getUrl(), "https://api.github.com/repos/ruby/ruby");
        Assert.assertEquals(githubProjects.get(0).getOwner().getLogin(), "ruby");
    }
}
