package com.github.dvdme.ForecastIOLib;

/**
 * Created by david.ervideira on 02/01/2017.
 */
class Alert {

    private String title;
    private Long time;
    private Long expire;
    private String description;
    private String uri;

    public Alert(){
        setTitle("");
        setTime(0L);
        setExpire(0L);
        setDescription("");
        setUri("");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}//class Alert - end
