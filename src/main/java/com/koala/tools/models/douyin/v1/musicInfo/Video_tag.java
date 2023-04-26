
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Video_tag {

    private int level;
    private int tag_id;
    private String tag_name;
    public void setLevel(int level) {
         this.level = level;
     }
     public int getLevel() {
         return level;
     }

    public void setTag_id(int tag_id) {
         this.tag_id = tag_id;
     }
     public int getTag_id() {
         return tag_id;
     }

    public void setTag_name(String tag_name) {
         this.tag_name = tag_name;
     }
     public String getTag_name() {
         return tag_name;
     }

}