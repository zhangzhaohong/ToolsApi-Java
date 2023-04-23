
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Stats {

    private String total_user_desp;
    private int like_count;
    private String total_user_str;
    private String user_count_str;
    public void setTotal_user_desp(String total_user_desp) {
         this.total_user_desp = total_user_desp;
     }
     public String getTotal_user_desp() {
         return total_user_desp;
     }

    public void setLike_count(int like_count) {
         this.like_count = like_count;
     }
     public int getLike_count() {
         return like_count;
     }

    public void setTotal_user_str(String total_user_str) {
         this.total_user_str = total_user_str;
     }
     public String getTotal_user_str() {
         return total_user_str;
     }

    public void setUser_count_str(String user_count_str) {
         this.user_count_str = user_count_str;
     }
     public String getUser_count_str() {
         return user_count_str;
     }

}