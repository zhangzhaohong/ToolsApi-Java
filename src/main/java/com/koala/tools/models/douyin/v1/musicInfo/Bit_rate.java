
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Bit_rate {

    private int FPS;
    private String HDR_bit;
    private String HDR_type;
    private long bit_rate;
    private String gear_name;
    private int is_bytevc1;
    private int is_h265;
    private Play_addr play_addr;
    private int quality_type;
    private String video_extra;
    public void setFPS(int FPS) {
         this.FPS = FPS;
     }
     public int getFPS() {
         return FPS;
     }

    public void setHDR_bit(String HDR_bit) {
         this.HDR_bit = HDR_bit;
     }
     public String getHDR_bit() {
         return HDR_bit;
     }

    public void setHDR_type(String HDR_type) {
         this.HDR_type = HDR_type;
     }
     public String getHDR_type() {
         return HDR_type;
     }

    public void setBit_rate(long bit_rate) {
         this.bit_rate = bit_rate;
     }
     public long getBit_rate() {
         return bit_rate;
     }

    public void setGear_name(String gear_name) {
         this.gear_name = gear_name;
     }
     public String getGear_name() {
         return gear_name;
     }

    public void setIs_bytevc1(int is_bytevc1) {
         this.is_bytevc1 = is_bytevc1;
     }
     public int getIs_bytevc1() {
         return is_bytevc1;
     }

    public void setIs_h265(int is_h265) {
         this.is_h265 = is_h265;
     }
     public int getIs_h265() {
         return is_h265;
     }

    public void setPlay_addr(Play_addr play_addr) {
         this.play_addr = play_addr;
     }
     public Play_addr getPlay_addr() {
         return play_addr;
     }

    public void setQuality_type(int quality_type) {
         this.quality_type = quality_type;
     }
     public int getQuality_type() {
         return quality_type;
     }

    public void setVideo_extra(String video_extra) {
         this.video_extra = video_extra;
     }
     public String getVideo_extra() {
         return video_extra;
     }

}