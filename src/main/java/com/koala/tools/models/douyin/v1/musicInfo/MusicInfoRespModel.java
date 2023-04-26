
package com.koala.tools.models.douyin.v1.musicInfo;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class MusicInfoRespModel {

    private List<Aweme_list> aweme_list;
    private int cursor;
    private Extra extra;
    private int has_more;
    private Log_pb log_pb;
    private int status_code;
    public void setAweme_list(List<Aweme_list> aweme_list) {
         this.aweme_list = aweme_list;
     }
     public List<Aweme_list> getAweme_list() {
         return aweme_list;
     }

    public void setCursor(int cursor) {
         this.cursor = cursor;
     }
     public int getCursor() {
         return cursor;
     }

    public void setExtra(Extra extra) {
         this.extra = extra;
     }
     public Extra getExtra() {
         return extra;
     }

    public void setHas_more(int has_more) {
         this.has_more = has_more;
     }
     public int getHas_more() {
         return has_more;
     }

    public void setLog_pb(Log_pb log_pb) {
         this.log_pb = log_pb;
     }
     public Log_pb getLog_pb() {
         return log_pb;
     }

    public void setStatus_code(int status_code) {
         this.status_code = status_code;
     }
     public int getStatus_code() {
         return status_code;
     }

}