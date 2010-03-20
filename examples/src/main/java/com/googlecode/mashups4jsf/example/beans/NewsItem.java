package com.googlecode.mashups4jsf.example.beans;

import com.googlecode.mashups.services.generic.api.ItemAuthor;
import com.googlecode.mashups.services.generic.api.ItemCategory;
import com.googlecode.mashups.services.generic.api.ItemDescription;
import com.googlecode.mashups.services.generic.api.ItemLink;
import com.googlecode.mashups.services.generic.api.ItemTitle;
import com.googlecode.mashups.services.generic.api.RssItem;

/**
 * @author hazems
 *
 */
@RssItem
public class NewsItem {
    String title;
    String desc;
    String link;
    String category;
    String author;
    
    /**
     * @return the title
     */
    @ItemTitle
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the desc
     */
    @ItemDescription
    public String getDesc() {
        return desc;
    }
    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the link
     */
    @ItemLink
    public String getLink() {
        return link;
    }
    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }
    
    /**
     * @return the category
     */
    @ItemCategory
    public String getCategory() {
        return category;
    }
    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * @return the author
     */
    @ItemAuthor
    public String getAuthor() {
        return author;
    }
    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * @param title
     * @param desc
     * @param link
     * @param category
     * @param author
     */
    public NewsItem(String title, String desc, String link, String category, String author) {
	super();
	this.title = title;
	this.desc = desc;
	this.link = link;
	this.category = category;
	this.author = author;
    }
}
