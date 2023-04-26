
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Matched_pgc_sound {

    private String author;
    private Cover_medium cover_medium;
    private String mixed_author;
    private String mixed_title;
    private String title;
    public void setAuthor(String author) {
         this.author = author;
     }
     public String getAuthor() {
         return author;
     }

    public void setCover_medium(Cover_medium cover_medium) {
         this.cover_medium = cover_medium;
     }
     public Cover_medium getCover_medium() {
         return cover_medium;
     }

    public void setMixed_author(String mixed_author) {
         this.mixed_author = mixed_author;
     }
     public String getMixed_author() {
         return mixed_author;
     }

    public void setMixed_title(String mixed_title) {
         this.mixed_title = mixed_title;
     }
     public String getMixed_title() {
         return mixed_title;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

}