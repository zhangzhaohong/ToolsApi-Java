
package com.koala.data.models.netease.mvInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class DataModel implements Serializable {
    private Long id;
    private String name;
    private Long artistId;
    private String artistName;
    private String briefDesc;
    private String desc;
    private String cover;
    private Long coverId;
    private Long playCount;
    private Integer subCount;
    private Integer shareCount;
    private Integer likeCount;
    private Integer commentCount;
    private Long duration;
    private Integer nType;
    private Date publishTime;
    private BrsModel brs;
    private List<ArtistModel> artists;
    private Boolean isReward;
    private String commentThreadId;
}