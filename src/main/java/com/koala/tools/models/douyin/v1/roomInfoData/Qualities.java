
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Qualities {

    private String name;
    private String sdk_key;
    private String v_codec;
    private String resolution;
    private int level;
    private long v_bit_rate;
    private String additional_content;
    private int fps;
    private int disable;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setSdk_key(String sdk_key) {
         this.sdk_key = sdk_key;
     }
     public String getSdk_key() {
         return sdk_key;
     }

    public void setV_codec(String v_codec) {
         this.v_codec = v_codec;
     }
     public String getV_codec() {
         return v_codec;
     }

    public void setResolution(String resolution) {
         this.resolution = resolution;
     }
     public String getResolution() {
         return resolution;
     }

    public void setLevel(int level) {
         this.level = level;
     }
     public int getLevel() {
         return level;
     }

    public void setV_bit_rate(long v_bit_rate) {
         this.v_bit_rate = v_bit_rate;
     }
     public long getV_bit_rate() {
         return v_bit_rate;
     }

    public void setAdditional_content(String additional_content) {
         this.additional_content = additional_content;
     }
     public String getAdditional_content() {
         return additional_content;
     }

    public void setFps(int fps) {
         this.fps = fps;
     }
     public int getFps() {
         return fps;
     }

    public void setDisable(int disable) {
         this.disable = disable;
     }
     public int getDisable() {
         return disable;
     }

}