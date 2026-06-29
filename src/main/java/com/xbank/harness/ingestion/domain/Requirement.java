package com.xbank.harness.ingestion.domain;

public class Requirement {
    private String id;
    private String content;
    private String mappedDomain;

    public Requirement(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() { return id; }
    public String getContent() { return content; }
    public String getMappedDomain() { return mappedDomain; }
    public void setMappedDomain(String mappedDomain) { this.mappedDomain = mappedDomain; }
}
