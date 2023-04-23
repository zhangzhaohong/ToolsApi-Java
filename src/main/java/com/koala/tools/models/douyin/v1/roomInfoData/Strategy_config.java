
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Strategy_config {

    private Background background;
    private Detail detail;
    private Tab tab;
    public void setBackground(Background background) {
         this.background = background;
     }
     public Background getBackground() {
         return background;
     }

    public void setDetail(Detail detail) {
         this.detail = detail;
     }
     public Detail getDetail() {
         return detail;
     }

    public void setTab(Tab tab) {
         this.tab = tab;
     }
     public Tab getTab() {
         return tab;
     }

}