
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Status {

    private boolean allow_share;
    private Aweme_edit_info aweme_edit_info;
    private int dont_share_status;
    private boolean in_reviewing;
    private boolean is_delete;
    private boolean is_prohibited;
    private int listen_video_status;
    private int part_see;
    private int private_status;
    private Review_result review_result;
    private int video_hide_search;
    public void setAllow_share(boolean allow_share) {
         this.allow_share = allow_share;
     }
     public boolean getAllow_share() {
         return allow_share;
     }

    public void setAweme_edit_info(Aweme_edit_info aweme_edit_info) {
         this.aweme_edit_info = aweme_edit_info;
     }
     public Aweme_edit_info getAweme_edit_info() {
         return aweme_edit_info;
     }

    public void setDont_share_status(int dont_share_status) {
         this.dont_share_status = dont_share_status;
     }
     public int getDont_share_status() {
         return dont_share_status;
     }

    public void setIn_reviewing(boolean in_reviewing) {
         this.in_reviewing = in_reviewing;
     }
     public boolean getIn_reviewing() {
         return in_reviewing;
     }

    public void setIs_delete(boolean is_delete) {
         this.is_delete = is_delete;
     }
     public boolean getIs_delete() {
         return is_delete;
     }

    public void setIs_prohibited(boolean is_prohibited) {
         this.is_prohibited = is_prohibited;
     }
     public boolean getIs_prohibited() {
         return is_prohibited;
     }

    public void setListen_video_status(int listen_video_status) {
         this.listen_video_status = listen_video_status;
     }
     public int getListen_video_status() {
         return listen_video_status;
     }

    public void setPart_see(int part_see) {
         this.part_see = part_see;
     }
     public int getPart_see() {
         return part_see;
     }

    public void setPrivate_status(int private_status) {
         this.private_status = private_status;
     }
     public int getPrivate_status() {
         return private_status;
     }

    public void setReview_result(Review_result review_result) {
         this.review_result = review_result;
     }
     public Review_result getReview_result() {
         return review_result;
     }

    public void setVideo_hide_search(int video_hide_search) {
         this.video_hide_search = video_hide_search;
     }
     public int getVideo_hide_search() {
         return video_hide_search;
     }

}