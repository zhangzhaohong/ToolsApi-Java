
package com.koala.tools.models.douyin.v1.musicInfo;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Video {

    private String big_thumbs;
    private List<Bit_rate> bit_rate;
    private String bit_rate_audio;
    private Cover cover;
    private int duration;
    private Dynamic_cover dynamic_cover;
    private int height;
    private int is_bytevc1;
    private boolean is_callback;
    private int is_source_HDR;
    private String meta;
    private Origin_cover origin_cover;
    private Play_addr play_addr;
    private Play_addr_265 play_addr_265;
    private Play_addr_h264 play_addr_h264;
    private String ratio;
    private String tags;
    private String video_model;
    private int width;
    public void setBig_thumbs(String big_thumbs) {
         this.big_thumbs = big_thumbs;
     }
     public String getBig_thumbs() {
         return big_thumbs;
     }

    public void setBit_rate(List<Bit_rate> bit_rate) {
         this.bit_rate = bit_rate;
     }
     public List<Bit_rate> getBit_rate() {
         return bit_rate;
     }

    public void setBit_rate_audio(String bit_rate_audio) {
         this.bit_rate_audio = bit_rate_audio;
     }
     public String getBit_rate_audio() {
         return bit_rate_audio;
     }

    public void setCover(Cover cover) {
         this.cover = cover;
     }
     public Cover getCover() {
         return cover;
     }

    public void setDuration(int duration) {
         this.duration = duration;
     }
     public int getDuration() {
         return duration;
     }

    public void setDynamic_cover(Dynamic_cover dynamic_cover) {
         this.dynamic_cover = dynamic_cover;
     }
     public Dynamic_cover getDynamic_cover() {
         return dynamic_cover;
     }

    public void setHeight(int height) {
         this.height = height;
     }
     public int getHeight() {
         return height;
     }

    public void setIs_bytevc1(int is_bytevc1) {
         this.is_bytevc1 = is_bytevc1;
     }
     public int getIs_bytevc1() {
         return is_bytevc1;
     }

    public void setIs_callback(boolean is_callback) {
         this.is_callback = is_callback;
     }
     public boolean getIs_callback() {
         return is_callback;
     }

    public void setIs_source_HDR(int is_source_HDR) {
         this.is_source_HDR = is_source_HDR;
     }
     public int getIs_source_HDR() {
         return is_source_HDR;
     }

    public void setMeta(String meta) {
         this.meta = meta;
     }
     public String getMeta() {
         return meta;
     }

    public void setOrigin_cover(Origin_cover origin_cover) {
         this.origin_cover = origin_cover;
     }
     public Origin_cover getOrigin_cover() {
         return origin_cover;
     }

    public void setPlay_addr(Play_addr play_addr) {
         this.play_addr = play_addr;
     }
     public Play_addr getPlay_addr() {
         return play_addr;
     }

    public void setPlay_addr_265(Play_addr_265 play_addr_265) {
         this.play_addr_265 = play_addr_265;
     }
     public Play_addr_265 getPlay_addr_265() {
         return play_addr_265;
     }

    public void setPlay_addr_h264(Play_addr_h264 play_addr_h264) {
         this.play_addr_h264 = play_addr_h264;
     }
     public Play_addr_h264 getPlay_addr_h264() {
         return play_addr_h264;
     }

    public void setRatio(String ratio) {
         this.ratio = ratio;
     }
     public String getRatio() {
         return ratio;
     }

    public void setTags(String tags) {
         this.tags = tags;
     }
     public String getTags() {
         return tags;
     }

    public void setVideo_model(String video_model) {
         this.video_model = video_model;
     }
     public String getVideo_model() {
         return video_model;
     }

    public void setWidth(int width) {
         this.width = width;
     }
     public int getWidth() {
         return width;
     }

}