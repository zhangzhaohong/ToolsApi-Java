
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Luna_info {

    private boolean has_copyright;
    private boolean is_luna_user;
    public void setHas_copyright(boolean has_copyright) {
         this.has_copyright = has_copyright;
     }
     public boolean getHas_copyright() {
         return has_copyright;
     }

    public void setIs_luna_user(boolean is_luna_user) {
         this.is_luna_user = is_luna_user;
     }
     public boolean getIs_luna_user() {
         return is_luna_user;
     }

}