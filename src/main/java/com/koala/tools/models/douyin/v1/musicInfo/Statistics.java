
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Statistics {

    private long collect_count;
    private long comment_count;
    private String digest;
    private long digg_count;
    private int exposure_count;
    private int live_watch_count;
    private int play_count;
    private long share_count;
    public void setCollect_count(long collect_count) {
         this.collect_count = collect_count;
     }
     public long getCollect_count() {
         return collect_count;
     }

    public void setComment_count(long comment_count) {
         this.comment_count = comment_count;
     }
     public long getComment_count() {
         return comment_count;
     }

    public void setDigest(String digest) {
         this.digest = digest;
     }
     public String getDigest() {
         return digest;
     }

    public void setDigg_count(long digg_count) {
         this.digg_count = digg_count;
     }
     public long getDigg_count() {
         return digg_count;
     }

    public void setExposure_count(int exposure_count) {
         this.exposure_count = exposure_count;
     }
     public int getExposure_count() {
         return exposure_count;
     }

    public void setLive_watch_count(int live_watch_count) {
         this.live_watch_count = live_watch_count;
     }
     public int getLive_watch_count() {
         return live_watch_count;
     }

    public void setPlay_count(int play_count) {
         this.play_count = play_count;
     }
     public int getPlay_count() {
         return play_count;
     }

    public void setShare_count(long share_count) {
         this.share_count = share_count;
     }
     public long getShare_count() {
         return share_count;
     }

}