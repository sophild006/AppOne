package com.wx.myproject.bean.main;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wwq on 2017/3/26.
 */

public class MainBean implements Serializable {
    private static final long serialVersionUID = 1L;


    public final static String TYPE_URL = "1";
    public final static String TYPE_IMG = "2";


    private String id;
    private String content;
    private String createTime;
    private String type;//1:链接  2:图片 3:视频
    private String linkImg;
    private String linkTitle;
    private List<PhotoInfo> photos;
    private User user;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getTypeUrl() {
        return TYPE_URL;
    }

    public static String getTypeImg() {
        return TYPE_IMG;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public List<PhotoInfo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoInfo> photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MainBean{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", type='" + type + '\'' +
                ", linkImg='" + linkImg + '\'' +
                ", linkTitle='" + linkTitle + '\'' +
                ", photos=" + photos +
                ", user=" + user +
                '}';
    }
}
