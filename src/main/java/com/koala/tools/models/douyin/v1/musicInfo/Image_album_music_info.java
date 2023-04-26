
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Image_album_music_info {

    private int begin_time;
    private int end_time;
    private int volume;
    public void setBegin_time(int begin_time) {
         this.begin_time = begin_time;
     }
     public int getBegin_time() {
         return begin_time;
     }

    public void setEnd_time(int end_time) {
         this.end_time = end_time;
     }
     public int getEnd_time() {
         return end_time;
     }

    public void setVolume(int volume) {
         this.volume = volume;
     }
     public int getVolume() {
         return volume;
     }

}