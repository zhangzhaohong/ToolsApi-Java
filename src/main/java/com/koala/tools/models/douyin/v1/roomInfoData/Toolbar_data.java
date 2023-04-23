
package com.koala.tools.models.douyin.v1.roomInfoData;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Toolbar_data {

    private List<Entrance_list> entrance_list;
    private List<More_panel> more_panel;
    private int max_entrance_cnt;
    private List<String> landscape_up_right;
    private Skin_resource skin_resource;
    private int max_entrance_cnt_landscape;
    public void setEntrance_list(List<Entrance_list> entrance_list) {
         this.entrance_list = entrance_list;
     }
     public List<Entrance_list> getEntrance_list() {
         return entrance_list;
     }

    public void setMore_panel(List<More_panel> more_panel) {
         this.more_panel = more_panel;
     }
     public List<More_panel> getMore_panel() {
         return more_panel;
     }

    public void setMax_entrance_cnt(int max_entrance_cnt) {
         this.max_entrance_cnt = max_entrance_cnt;
     }
     public int getMax_entrance_cnt() {
         return max_entrance_cnt;
     }

    public void setLandscape_up_right(List<String> landscape_up_right) {
         this.landscape_up_right = landscape_up_right;
     }
     public List<String> getLandscape_up_right() {
         return landscape_up_right;
     }

    public void setSkin_resource(Skin_resource skin_resource) {
         this.skin_resource = skin_resource;
     }
     public Skin_resource getSkin_resource() {
         return skin_resource;
     }

    public void setMax_entrance_cnt_landscape(int max_entrance_cnt_landscape) {
         this.max_entrance_cnt_landscape = max_entrance_cnt_landscape;
     }
     public int getMax_entrance_cnt_landscape() {
         return max_entrance_cnt_landscape;
     }

}