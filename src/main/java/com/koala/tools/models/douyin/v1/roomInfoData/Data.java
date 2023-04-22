/**
  * Copyright 2023 json.cn 
  */
package com.koala.tools.models.douyin.v1.roomInfoData;
import java.util.List;

/**
 * Auto-generated: 2023-04-22 21:51:0
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Data {

    private List<Data> data;
    private String enter_room_id;
    private Extra extra;
    private User user;
    private String qrcode_url;
    private int enter_mode;
    private int room_status;
    private Partition_road_map partition_road_map;
    private List<String> similar_rooms;
    private String shark_decision_conf;
    private Web_stream_url web_stream_url;
    private Login_lead login_lead;
    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setEnter_room_id(String enter_room_id) {
         this.enter_room_id = enter_room_id;
     }
     public String getEnter_room_id() {
         return enter_room_id;
     }

    public void setExtra(Extra extra) {
         this.extra = extra;
     }
     public Extra getExtra() {
         return extra;
     }

    public void setUser(User user) {
         this.user = user;
     }
     public User getUser() {
         return user;
     }

    public void setQrcode_url(String qrcode_url) {
         this.qrcode_url = qrcode_url;
     }
     public String getQrcode_url() {
         return qrcode_url;
     }

    public void setEnter_mode(int enter_mode) {
         this.enter_mode = enter_mode;
     }
     public int getEnter_mode() {
         return enter_mode;
     }

    public void setRoom_status(int room_status) {
         this.room_status = room_status;
     }
     public int getRoom_status() {
         return room_status;
     }

    public void setPartition_road_map(Partition_road_map partition_road_map) {
         this.partition_road_map = partition_road_map;
     }
     public Partition_road_map getPartition_road_map() {
         return partition_road_map;
     }

    public void setSimilar_rooms(List<String> similar_rooms) {
         this.similar_rooms = similar_rooms;
     }
     public List<String> getSimilar_rooms() {
         return similar_rooms;
     }

    public void setShark_decision_conf(String shark_decision_conf) {
         this.shark_decision_conf = shark_decision_conf;
     }
     public String getShark_decision_conf() {
         return shark_decision_conf;
     }

    public void setWeb_stream_url(Web_stream_url web_stream_url) {
         this.web_stream_url = web_stream_url;
     }
     public Web_stream_url getWeb_stream_url() {
         return web_stream_url;
     }

    public void setLogin_lead(Login_lead login_lead) {
         this.login_lead = login_lead;
     }
     public Login_lead getLogin_lead() {
         return login_lead;
     }

}