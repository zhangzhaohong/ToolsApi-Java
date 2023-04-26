
package com.koala.tools.models.douyin.v1.musicInfo;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Impression_data {

    private List<String> group_id_list_a;
    private List<String> group_id_list_b;
    private List<Long> group_id_list_c;
    private String similar_id_list_a;
    private List<Long> similar_id_list_b;
    public void setGroup_id_list_a(List<String> group_id_list_a) {
         this.group_id_list_a = group_id_list_a;
     }
     public List<String> getGroup_id_list_a() {
         return group_id_list_a;
     }

    public void setGroup_id_list_b(List<String> group_id_list_b) {
         this.group_id_list_b = group_id_list_b;
     }
     public List<String> getGroup_id_list_b() {
         return group_id_list_b;
     }

    public void setGroup_id_list_c(List<Long> group_id_list_c) {
         this.group_id_list_c = group_id_list_c;
     }
     public List<Long> getGroup_id_list_c() {
         return group_id_list_c;
     }

    public void setSimilar_id_list_a(String similar_id_list_a) {
         this.similar_id_list_a = similar_id_list_a;
     }
     public String getSimilar_id_list_a() {
         return similar_id_list_a;
     }

    public void setSimilar_id_list_b(List<Long> similar_id_list_b) {
         this.similar_id_list_b = similar_id_list_b;
     }
     public List<Long> getSimilar_id_list_b() {
         return similar_id_list_b;
     }

}