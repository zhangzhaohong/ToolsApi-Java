package com.koala.data.models.netease.mvInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class ArtistModel implements Serializable {
    private Long id;
    private String name;
}