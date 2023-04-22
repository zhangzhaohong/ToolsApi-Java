/**
  * Copyright 2023 json.cn 
  */
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * Auto-generated: 2023-04-22 21:51:0
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Web_stream_url {

    private Flv_pull_url flv_pull_url;
    private String default_resolution;
    private Hls_pull_url_map hls_pull_url_map;
    private String hls_pull_url;
    private int stream_orientation;
    private Pull_datas pull_datas;
    public void setFlv_pull_url(Flv_pull_url flv_pull_url) {
         this.flv_pull_url = flv_pull_url;
     }
     public Flv_pull_url getFlv_pull_url() {
         return flv_pull_url;
     }

    public void setDefault_resolution(String default_resolution) {
         this.default_resolution = default_resolution;
     }
     public String getDefault_resolution() {
         return default_resolution;
     }

    public void setHls_pull_url_map(Hls_pull_url_map hls_pull_url_map) {
         this.hls_pull_url_map = hls_pull_url_map;
     }
     public Hls_pull_url_map getHls_pull_url_map() {
         return hls_pull_url_map;
     }

    public void setHls_pull_url(String hls_pull_url) {
         this.hls_pull_url = hls_pull_url;
     }
     public String getHls_pull_url() {
         return hls_pull_url;
     }

    public void setStream_orientation(int stream_orientation) {
         this.stream_orientation = stream_orientation;
     }
     public int getStream_orientation() {
         return stream_orientation;
     }

    public void setPull_datas(Pull_datas pull_datas) {
         this.pull_datas = pull_datas;
     }
     public Pull_datas getPull_datas() {
         return pull_datas;
     }

}