
package com.koala.tools.models.douyin.v1.musicInfo;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Song {

    private String artists;
    private String chorus_v3_infos;
    private long id;
    private String id_str;
    public void setArtists(String artists) {
         this.artists = artists;
     }
     public String getArtists() {
         return artists;
     }

    public void setChorus_v3_infos(String chorus_v3_infos) {
         this.chorus_v3_infos = chorus_v3_infos;
     }
     public String getChorus_v3_infos() {
         return chorus_v3_infos;
     }

    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setId_str(String id_str) {
         this.id_str = id_str;
     }
     public String getId_str() {
         return id_str;
     }

}