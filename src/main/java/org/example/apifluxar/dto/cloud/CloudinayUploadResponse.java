package org.example.apifluxar.dto.cloud;

public class CloudinayUploadResponse {
    private String url;
    private String publicId;
    private Integer width;
    private Integer height;

    public CloudinayUploadResponse() {}

    public CloudinayUploadResponse(String url, String publicId, Integer width, Integer height) {
        this.url = url;
        this.publicId = publicId;
        this.width = width;
        this.height = height;
    }

    // getters & setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getPublicId() { return publicId; }
    public void setPublicId(String publicId) { this.publicId = publicId; }
    public Integer getWidth() { return width; }
    public void setWidth(Integer width) { this.width = width; }
    public Integer getHeight() { return height; }
    public void setHeight(Integer height) { this.height = height; }
}
