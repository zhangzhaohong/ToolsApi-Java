
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Text_extra {

    private int end;
    private String hashtag_id;
    private String hashtag_name;
    private boolean is_commerce;
    private int start;
    private int type;
    public void setEnd(int end) {
         this.end = end;
     }
     public int getEnd() {
         return end;
     }

    public void setHashtag_id(String hashtag_id) {
         this.hashtag_id = hashtag_id;
     }
     public String getHashtag_id() {
         return hashtag_id;
     }

    public void setHashtag_name(String hashtag_name) {
         this.hashtag_name = hashtag_name;
     }
     public String getHashtag_name() {
         return hashtag_name;
     }

    public void setIs_commerce(boolean is_commerce) {
         this.is_commerce = is_commerce;
     }
     public boolean getIs_commerce() {
         return is_commerce;
     }

    public void setStart(int start) {
         this.start = start;
     }
     public int getStart() {
         return start;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

}