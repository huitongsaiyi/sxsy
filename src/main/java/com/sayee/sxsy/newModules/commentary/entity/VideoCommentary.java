package com.sayee.sxsy.newModules.commentary.entity;

import java.util.List;

public class VideoCommentary {
    private String commentId;//主键

    private String parentId;//父级id

    private String videoId;//视频id

    private String userId;//用户id

    private String userName;//用户姓名

    private String content;//内容

    private String praisePoints;//点赞数

    private String commentTime;//评论时间



    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPraisePoints() {
        return praisePoints;
    }

    public void setPraisePoints(String praisePoints) {
        this.praisePoints = praisePoints == null ? null : praisePoints.trim();
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime == null ? null : commentTime.trim();
    }
}