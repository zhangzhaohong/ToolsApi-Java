
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Room_cart {

    private boolean contain_cart;
    private int total;
    private int flash_total;
    private String cart_icon;
    private int show_cart;
    public void setContain_cart(boolean contain_cart) {
         this.contain_cart = contain_cart;
     }
     public boolean getContain_cart() {
         return contain_cart;
     }

    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    public void setFlash_total(int flash_total) {
         this.flash_total = flash_total;
     }
     public int getFlash_total() {
         return flash_total;
     }

    public void setCart_icon(String cart_icon) {
         this.cart_icon = cart_icon;
     }
     public String getCart_icon() {
         return cart_icon;
     }

    public void setShow_cart(int show_cart) {
         this.show_cart = show_cart;
     }
     public int getShow_cart() {
         return show_cart;
     }

}