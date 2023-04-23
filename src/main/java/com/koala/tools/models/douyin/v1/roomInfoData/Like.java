
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Like {

    private int UnableStyle;
    private String Content;
    private int OffType;
    public void setUnableStyle(int UnableStyle) {
         this.UnableStyle = UnableStyle;
     }
     public int getUnableStyle() {
         return UnableStyle;
     }

    public void setContent(String Content) {
         this.Content = Content;
     }
     public String getContent() {
         return Content;
     }

    public void setOffType(int OffType) {
         this.OffType = OffType;
     }
     public int getOffType() {
         return OffType;
     }

}