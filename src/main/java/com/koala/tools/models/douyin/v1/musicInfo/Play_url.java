
package com.koala.tools.models.douyin.v1.musicInfo;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Play_url {

    private int height;
    private String uri;
    private String url_key;
    private List<String> url_list;
    private int width;
    public void setHeight(int height) {
         this.height = height;
     }
     public int getHeight() {
         return height;
     }

    public void setUri(String uri) {
         this.uri = uri;
     }
     public String getUri() {
         return uri;
     }

    public void setUrl_key(String url_key) {
         this.url_key = url_key;
     }
     public String getUrl_key() {
         return url_key;
     }

    public void setUrl_list(List<String> url_list) {
         this.url_list = url_list;
     }
     public List<String> getUrl_list() {
         return url_list;
     }

    public void setWidth(int width) {
         this.width = width;
     }
     public int getWidth() {
         return width;
     }

}