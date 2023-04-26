
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Share_info {

    private String share_desc_info;
    private String share_link_desc;
    private String share_url;
    public void setShare_desc_info(String share_desc_info) {
         this.share_desc_info = share_desc_info;
     }
     public String getShare_desc_info() {
         return share_desc_info;
     }

    public void setShare_link_desc(String share_link_desc) {
         this.share_link_desc = share_link_desc;
     }
     public String getShare_link_desc() {
         return share_link_desc;
     }

    public void setShare_url(String share_url) {
         this.share_url = share_url;
     }
     public String getShare_url() {
         return share_url;
     }

}