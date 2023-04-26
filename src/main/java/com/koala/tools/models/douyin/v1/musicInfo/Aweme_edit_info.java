
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Aweme_edit_info {

    private int button_status;
    private String button_toast;
    public void setButton_status(int button_status) {
         this.button_status = button_status;
     }
     public int getButton_status() {
         return button_status;
     }

    public void setButton_toast(String button_toast) {
         this.button_toast = button_toast;
     }
     public String getButton_toast() {
         return button_toast;
     }

}