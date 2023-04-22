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
public class Options {

    private Default_quality default_quality;
    private List<Qualities> qualities;
    public void setDefault_quality(Default_quality default_quality) {
         this.default_quality = default_quality;
     }
     public Default_quality getDefault_quality() {
         return default_quality;
     }

    public void setQualities(List<Qualities> qualities) {
         this.qualities = qualities;
     }
     public List<Qualities> getQualities() {
         return qualities;
     }

}