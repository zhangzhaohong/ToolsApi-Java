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
public class Linker_detail {

    private List<Integer> linker_play_modes;
    private int big_party_layout_config_version;
    private boolean accept_audience_pre_apply;
    private int linker_ui_layout;
    private int enable_audience_linkmic;
    private String function_type;
    private Linker_map_str linker_map_str;
    private String ktv_lyric_mode;
    private String init_source;
    private boolean forbid_apply_from_other;
    private int ktv_exhibit_mode;
    private int enlarge_guest_turn_on_source;
    private Playmode_detail playmode_detail;
    public void setLinker_play_modes(List<Integer> linker_play_modes) {
         this.linker_play_modes = linker_play_modes;
     }
     public List<Integer> getLinker_play_modes() {
         return linker_play_modes;
     }

    public void setBig_party_layout_config_version(int big_party_layout_config_version) {
         this.big_party_layout_config_version = big_party_layout_config_version;
     }
     public int getBig_party_layout_config_version() {
         return big_party_layout_config_version;
     }

    public void setAccept_audience_pre_apply(boolean accept_audience_pre_apply) {
         this.accept_audience_pre_apply = accept_audience_pre_apply;
     }
     public boolean getAccept_audience_pre_apply() {
         return accept_audience_pre_apply;
     }

    public void setLinker_ui_layout(int linker_ui_layout) {
         this.linker_ui_layout = linker_ui_layout;
     }
     public int getLinker_ui_layout() {
         return linker_ui_layout;
     }

    public void setEnable_audience_linkmic(int enable_audience_linkmic) {
         this.enable_audience_linkmic = enable_audience_linkmic;
     }
     public int getEnable_audience_linkmic() {
         return enable_audience_linkmic;
     }

    public void setFunction_type(String function_type) {
         this.function_type = function_type;
     }
     public String getFunction_type() {
         return function_type;
     }

    public void setLinker_map_str(Linker_map_str linker_map_str) {
         this.linker_map_str = linker_map_str;
     }
     public Linker_map_str getLinker_map_str() {
         return linker_map_str;
     }

    public void setKtv_lyric_mode(String ktv_lyric_mode) {
         this.ktv_lyric_mode = ktv_lyric_mode;
     }
     public String getKtv_lyric_mode() {
         return ktv_lyric_mode;
     }

    public void setInit_source(String init_source) {
         this.init_source = init_source;
     }
     public String getInit_source() {
         return init_source;
     }

    public void setForbid_apply_from_other(boolean forbid_apply_from_other) {
         this.forbid_apply_from_other = forbid_apply_from_other;
     }
     public boolean getForbid_apply_from_other() {
         return forbid_apply_from_other;
     }

    public void setKtv_exhibit_mode(int ktv_exhibit_mode) {
         this.ktv_exhibit_mode = ktv_exhibit_mode;
     }
     public int getKtv_exhibit_mode() {
         return ktv_exhibit_mode;
     }

    public void setEnlarge_guest_turn_on_source(int enlarge_guest_turn_on_source) {
         this.enlarge_guest_turn_on_source = enlarge_guest_turn_on_source;
     }
     public int getEnlarge_guest_turn_on_source() {
         return enlarge_guest_turn_on_source;
     }

    public void setPlaymode_detail(Playmode_detail playmode_detail) {
         this.playmode_detail = playmode_detail;
     }
     public Playmode_detail getPlaymode_detail() {
         return playmode_detail;
     }

}