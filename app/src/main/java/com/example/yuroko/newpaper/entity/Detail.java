package com.example.yuroko.newpaper.entity;

public class Detail {
    String type;
    String content;
    String align;
    String img;

    public Detail(String type, String content, String align) {
        this.type = type;
        this.content = content;
        this.align = align;
    }
    public Detail(String type,String img,String content,String align){
        this.type = type;
        this.img = img;
        this.content=content;
        this.align= align;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

}
