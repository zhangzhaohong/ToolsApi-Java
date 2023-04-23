
package com.koala.tools.models.douyin.v1.roomInfoData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Appointment_info {

    private int appointment_id;
    private boolean is_subscribe;
    public void setAppointment_id(int appointment_id) {
         this.appointment_id = appointment_id;
     }
     public int getAppointment_id() {
         return appointment_id;
     }

    public void setIs_subscribe(boolean is_subscribe) {
         this.is_subscribe = is_subscribe;
     }
     public boolean getIs_subscribe() {
         return is_subscribe;
     }

}