package com.hhr.lazyread.model;

import java.io.Serializable;

/**
 * book
 * @author 
 */
public class Book implements Serializable {
    private Integer id;

    private String name;

    private String imageUrl;

    private String author;

    private String webType;

    private String bookUrl;

    private Integer userId;

    private Integer latestChapter;

    private String latestChapterName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWebType() {
        return webType;
    }

    public void setWebType(String webType) {
        this.webType = webType;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLatestChapter() {
        return latestChapter;
    }

    public void setLatestChapter(Integer latestChapter) {
        this.latestChapter = latestChapter;
    }

    public String getLatestChapterName() {
        return latestChapterName;
    }

    public void setLatestChapterName(String latestChapterName) {
        this.latestChapterName = latestChapterName;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Book other = (Book) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getWebType() == null ? other.getWebType() == null : this.getWebType().equals(other.getWebType()))
            && (this.getBookUrl() == null ? other.getBookUrl() == null : this.getBookUrl().equals(other.getBookUrl()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLatestChapter() == null ? other.getLatestChapter() == null : this.getLatestChapter().equals(other.getLatestChapter()))
            && (this.getLatestChapterName() == null ? other.getLatestChapterName() == null : this.getLatestChapterName().equals(other.getLatestChapterName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getWebType() == null) ? 0 : getWebType().hashCode());
        result = prime * result + ((getBookUrl() == null) ? 0 : getBookUrl().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLatestChapter() == null) ? 0 : getLatestChapter().hashCode());
        result = prime * result + ((getLatestChapterName() == null) ? 0 : getLatestChapterName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", author=").append(author);
        sb.append(", webType=").append(webType);
        sb.append(", bookUrl=").append(bookUrl);
        sb.append(", userId=").append(userId);
        sb.append(", latestChapter=").append(latestChapter);
        sb.append(", latestChapterName=").append(latestChapterName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}