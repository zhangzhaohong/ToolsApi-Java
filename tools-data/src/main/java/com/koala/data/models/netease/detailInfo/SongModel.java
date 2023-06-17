package com.koala.data.models.netease.detailInfo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class SongModel implements Serializable {
    private String name;
    private Long id;
    private Integer pst;
    private Integer t;
    private List<ArModel> ar;
    private List<String> alia;
    private Integer pop;
    private Integer st;
    private String rt;
    private Integer fee;
    private Integer v;
    private String crbt;
    private String cf;
    private AlModel al;
    private Long dt;
    private MediaModel h;
    private MediaModel m;
    private MediaModel l;
    private MediaModel sq;
    private MediaModel hr;
    private String a;
    private String cd;
    private Integer no;
    private String rtUrl;
    private Integer ftype;
    private List<String> rtUrls;
    private Integer djId;
    private Integer copyright;
    private Integer s_id;
    private Long mark;
    private Integer originCoverType;
    private String originSongSimpleData;
    private String tagPicList;
    private Boolean resourceState;
    private Integer version;
    private String songJumpInfo;
    private String entertainmentTags;
    private String awardTags;
    private Integer single;
    private String noCopyrightRcmd;
    private Integer mv;
    private Integer rtype;
    private String rurl;
    private Integer mst;
    private Integer cp;
    private Integer publishTime;
}