
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Aweme_control {

    private boolean can_comment;
    private boolean can_forward;
    private boolean can_share;
    private boolean can_show_comment;
    public void setCan_comment(boolean can_comment) {
         this.can_comment = can_comment;
     }
     public boolean getCan_comment() {
         return can_comment;
     }

    public void setCan_forward(boolean can_forward) {
         this.can_forward = can_forward;
     }
     public boolean getCan_forward() {
         return can_forward;
     }

    public void setCan_share(boolean can_share) {
         this.can_share = can_share;
     }
     public boolean getCan_share() {
         return can_share;
     }

    public void setCan_show_comment(boolean can_show_comment) {
         this.can_show_comment = can_show_comment;
     }
     public boolean getCan_show_comment() {
         return can_show_comment;
     }

}