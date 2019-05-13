package com.example.yuroko.newpaper.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Category {
    String id;
    String title;
    String icon;
    String iconactive;
    int icon1;
    int iconactive1;

    public Category(JSONObject obj)
    {
        try {
            id = obj.getString("id");
            title = obj.getString("title");
            icon= obj.getString("icon");
            iconactive=obj.getString("icon_active");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Category(String id, String title, int icon1, int iconactive1) {
        this.id = id;
        this.title = title;
        this.icon1 = icon1;
        this.iconactive1 = iconactive1;

    }

    public Category(String title) {
        this.title = title;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconactive() {
        return iconactive;
    }

    public void setIconactive(String iconactive) {
        this.iconactive = iconactive;
    }

    public int getIcon1() {
        return icon1;
    }

    public void setIcon1(int icon1) {
        this.icon1 = icon1;
    }

    public int getIconactive1() {
        return iconactive1;
    }

    public void setIconactive1(int iconactive1) {
        this.iconactive1 = iconactive1;
    }
}
