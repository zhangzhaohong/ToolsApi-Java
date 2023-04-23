
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Pull_data {

    private Options options;
    private String stream_data;
    public void setOptions(Options options) {
         this.options = options;
     }
     public Options getOptions() {
         return options;
     }

    public void setStream_data(String stream_data) {
         this.stream_data = stream_data;
     }
     public String getStream_data() {
         return stream_data;
     }

}