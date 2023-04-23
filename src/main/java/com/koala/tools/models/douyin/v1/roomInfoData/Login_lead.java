
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Login_lead {

    private boolean is_login;
    private int level;
    private Items items;
    public void setIs_login(boolean is_login) {
         this.is_login = is_login;
     }
     public boolean getIs_login() {
         return is_login;
     }

    public void setLevel(int level) {
         this.level = level;
     }
     public int getLevel() {
         return level;
     }

    public void setItems(Items items) {
         this.items = items;
     }
     public Items getItems() {
         return items;
     }

}