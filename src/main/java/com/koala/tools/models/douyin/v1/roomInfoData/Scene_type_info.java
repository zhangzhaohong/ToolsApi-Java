
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Scene_type_info {

    private boolean is_union_live_room;
    private boolean is_life;
    private int is_protected_room;
    private int is_lasted_goods_room;
    private int is_desire_room;
    private boolean commentary_type;
    public void setIs_union_live_room(boolean is_union_live_room) {
         this.is_union_live_room = is_union_live_room;
     }
     public boolean getIs_union_live_room() {
         return is_union_live_room;
     }

    public void setIs_life(boolean is_life) {
         this.is_life = is_life;
     }
     public boolean getIs_life() {
         return is_life;
     }

    public void setIs_protected_room(int is_protected_room) {
         this.is_protected_room = is_protected_room;
     }
     public int getIs_protected_room() {
         return is_protected_room;
     }

    public void setIs_lasted_goods_room(int is_lasted_goods_room) {
         this.is_lasted_goods_room = is_lasted_goods_room;
     }
     public int getIs_lasted_goods_room() {
         return is_lasted_goods_room;
     }

    public void setIs_desire_room(int is_desire_room) {
         this.is_desire_room = is_desire_room;
     }
     public int getIs_desire_room() {
         return is_desire_room;
     }

    public void setCommentary_type(boolean commentary_type) {
         this.commentary_type = commentary_type;
     }
     public boolean getCommentary_type() {
         return commentary_type;
     }

}