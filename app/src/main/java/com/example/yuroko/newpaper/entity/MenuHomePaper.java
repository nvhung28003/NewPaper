package com.example.yuroko.newpaper.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuHomePaper {
    String id;
    String title;
    String description;
    String published_at;
    String thumbnail;
    String parent_category;
    String link_web;
    String site_name;


    public MenuHomePaper (JSONObject obj)
    {
        try {
            id = obj.getString("id");
            title = obj.getString("title");
            description = obj.getString("description");
            published_at = obj.getString("published_at");
            thumbnail = obj.getString("thumbnail");
            parent_category = obj.getString("parent_category");
            site_name = obj.getString("site_name");
            link_web =obj.getString("link_web");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getParent_category() {
        return parent_category;
    }

    public void setParent_category(String parent_category) {
        this.parent_category = parent_category;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getLink_web() {
        return link_web;
    }

    public void setLink_web(String link_web) {
        this.link_web = link_web;
    }
}
