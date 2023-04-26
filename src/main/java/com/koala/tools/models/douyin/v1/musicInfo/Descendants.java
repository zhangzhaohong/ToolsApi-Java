
package com.koala.tools.models.douyin.v1.musicInfo;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Descendants {

    private String notify_msg;
    private List<String> platforms;
    public void setNotify_msg(String notify_msg) {
         this.notify_msg = notify_msg;
     }
     public String getNotify_msg() {
         return notify_msg;
     }

    public void setPlatforms(List<String> platforms) {
         this.platforms = platforms;
     }
     public List<String> getPlatforms() {
         return platforms;
     }

}