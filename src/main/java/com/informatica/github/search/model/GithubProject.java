package com.informatica.github.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubProject {
    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;
    @JsonProperty("url")
    String url;
    @JsonProperty("owner")
    Owner owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {
        @JsonProperty("login")
        String login;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }
}
