
package com.koala.tools.models.douyin.v1.musicInfo;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Extra {

    private List<String> fatal_item_ids;
    private String logid;
    private long now;
    public void setFatal_item_ids(List<String> fatal_item_ids) {
         this.fatal_item_ids = fatal_item_ids;
     }
     public List<String> getFatal_item_ids() {
         return fatal_item_ids;
     }

    public void setLogid(String logid) {
         this.logid = logid;
     }
     public String getLogid() {
         return logid;
     }

    public void setNow(long now) {
         this.now = now;
     }
     public long getNow() {
         return now;
     }

}