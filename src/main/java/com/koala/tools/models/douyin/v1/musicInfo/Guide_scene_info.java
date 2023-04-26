
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Guide_scene_info {

    private String diamond_expose_info_str;
    private String feed_origin_gid_info_str;
    private int guide_scene_type;
    public void setDiamond_expose_info_str(String diamond_expose_info_str) {
         this.diamond_expose_info_str = diamond_expose_info_str;
     }
     public String getDiamond_expose_info_str() {
         return diamond_expose_info_str;
     }

    public void setFeed_origin_gid_info_str(String feed_origin_gid_info_str) {
         this.feed_origin_gid_info_str = feed_origin_gid_info_str;
     }
     public String getFeed_origin_gid_info_str() {
         return feed_origin_gid_info_str;
     }

    public void setGuide_scene_type(int guide_scene_type) {
         this.guide_scene_type = guide_scene_type;
     }
     public int getGuide_scene_type() {
         return guide_scene_type;
     }

}