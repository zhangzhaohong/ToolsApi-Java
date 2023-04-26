
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Poi_patch_info {

    private String extra;
    private int item_patch_poi_prompt_mark;
    public void setExtra(String extra) {
         this.extra = extra;
     }
     public String getExtra() {
         return extra;
     }

    public void setItem_patch_poi_prompt_mark(int item_patch_poi_prompt_mark) {
         this.item_patch_poi_prompt_mark = item_patch_poi_prompt_mark;
     }
     public int getItem_patch_poi_prompt_mark() {
         return item_patch_poi_prompt_mark;
     }

}