
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class RoomInfoDataRespModel {

    private int status_code;
    private Data data;
    private Extra extra;
    public void setStatus_code(int status_code) {
         this.status_code = status_code;
     }
     public int getStatus_code() {
         return status_code;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setExtra(Extra extra) {
         this.extra = extra;
     }
     public Extra getExtra() {
         return extra;
     }

}