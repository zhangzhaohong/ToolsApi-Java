
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Series_paid_info {

    private int item_price;
    private int series_paid_status;
    public void setItem_price(int item_price) {
         this.item_price = item_price;
     }
     public int getItem_price() {
         return item_price;
     }

    public void setSeries_paid_status(int series_paid_status) {
         this.series_paid_status = series_paid_status;
     }
     public int getSeries_paid_status() {
         return series_paid_status;
     }

}