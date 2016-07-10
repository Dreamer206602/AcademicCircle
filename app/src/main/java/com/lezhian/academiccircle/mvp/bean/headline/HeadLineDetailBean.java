package com.lezhian.academiccircle.mvp.bean.headline;

import java.io.Serializable;

/**
 * Created by ${CQ} on 2016/6/30.
 */
public class HeadLineDetailBean implements Serializable {
    /** 收藏量 */
    private String favorite;
    /** 文章id */
    private String articleId;
    /** 1：新闻
     * 2：论文
     * 3：图书
     * 4：博客
     * 5：会议
     * 6：专利
     * 7：课程
     */
    private String type;
    /**  */
    private String source;
    /** 阅读量 */
    private String readCount;
    /**  */
    private String acif;
    /** 机构 */
    private String agency;
    /** 作者 */
    private String author;
    /** 奖项 */
    private String awards;
    /** 分类 */
    private String classify;
    /** 描述 */
    private String description;
    /** 下载地址 */
    private String downloadUrl;
    /** 图片地址 */
    private String imageUrl;
    /**  */
    private String sourceUrl;
    /**  */
    private String issueNumber;
    /**  */
    private String keyword;
    /**  */
    private String patentApp;
    /**  */
    private String patentDate;
    /**  */
    private String patentNum;
    /**  */
    private String patentStatus;
    /**  */
    private String patentType;
    /**  */
    private String periodical;
    /**  */
    private String publicationDate;
    /**  */
    private String publicationNum;
    /**  */
    private String publisher;
    /**  */
    private String referenceNum;
    /**  */
    private String site;
    /**  */
    private String subjectTerms;
    /**  */
    private String tag;
    /**  */
    private String time;
    /**  */
    private String title;
    /**  */
    private String videoUrl;
    /**  */
    private String volume;
    /**  */
    private String weighting;
    /**  */
    private String year;

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getAcif() {
        return acif;
    }

    public void setAcif(String acif) {
        this.acif = acif;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPatentApp() {
        return patentApp;
    }

    public void setPatentApp(String patentApp) {
        this.patentApp = patentApp;
    }

    public String getPatentDate() {
        return patentDate;
    }

    public void setPatentDate(String patentDate) {
        this.patentDate = patentDate;
    }

    public String getPatentNum() {
        return patentNum;
    }

    public void setPatentNum(String patentNum) {
        this.patentNum = patentNum;
    }

    public String getPatentStatus() {
        return patentStatus;
    }

    public void setPatentStatus(String patentStatus) {
        this.patentStatus = patentStatus;
    }

    public String getPatentType() {
        return patentType;
    }

    public void setPatentType(String patentType) {
        this.patentType = patentType;
    }

    public String getPeriodical() {
        return periodical;
    }

    public void setPeriodical(String periodical) {
        this.periodical = periodical;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublicationNum() {
        return publicationNum;
    }

    public void setPublicationNum(String publicationNum) {
        this.publicationNum = publicationNum;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getReferenceNum() {
        return referenceNum;
    }

    public void setReferenceNum(String referenceNum) {
        this.referenceNum = referenceNum;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSubjectTerms() {
        return subjectTerms;
    }

    public void setSubjectTerms(String subjectTerms) {
        this.subjectTerms = subjectTerms;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWeighting() {
        return weighting;
    }

    public void setWeighting(String weighting) {
        this.weighting = weighting;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

