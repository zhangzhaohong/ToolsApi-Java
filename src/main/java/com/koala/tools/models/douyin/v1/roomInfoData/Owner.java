
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Owner {

    private String id_str;
    private String sec_uid;
    private String nickname;
    private Avatar_thumb avatar_thumb;
    private Follow_info follow_info;
    public void setId_str(String id_str) {
         this.id_str = id_str;
     }
     public String getId_str() {
         return id_str;
     }

    public void setSec_uid(String sec_uid) {
         this.sec_uid = sec_uid;
     }
     public String getSec_uid() {
         return sec_uid;
     }

    public void setNickname(String nickname) {
         this.nickname = nickname;
     }
     public String getNickname() {
         return nickname;
     }

    public void setAvatar_thumb(Avatar_thumb avatar_thumb) {
         this.avatar_thumb = avatar_thumb;
     }
     public Avatar_thumb getAvatar_thumb() {
         return avatar_thumb;
     }

    public void setFollow_info(Follow_info follow_info) {
         this.follow_info = follow_info;
     }
     public Follow_info getFollow_info() {
         return follow_info;
     }

}