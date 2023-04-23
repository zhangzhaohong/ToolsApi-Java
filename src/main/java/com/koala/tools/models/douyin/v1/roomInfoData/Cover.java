
package com.koala.tools.models.douyin.v1.roomInfoData;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Cover {

    private List<String> url_list;
    public void setUrl_list(List<String> url_list) {
         this.url_list = url_list;
     }
     public List<String> getUrl_list() {
         return url_list;
     }

}