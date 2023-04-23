
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Follow_info {

    private int follow_status;
    private String follow_status_str;
    public void setFollow_status(int follow_status) {
         this.follow_status = follow_status;
     }
     public int getFollow_status() {
         return follow_status;
     }

    public void setFollow_status_str(String follow_status_str) {
         this.follow_status_str = follow_status_str;
     }
     public String getFollow_status_str() {
         return follow_status_str;
     }

}