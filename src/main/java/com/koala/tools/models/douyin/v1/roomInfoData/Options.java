
package com.koala.tools.models.douyin.v1.roomInfoData;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
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