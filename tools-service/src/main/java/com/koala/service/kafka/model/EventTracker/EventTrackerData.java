package com.koala.service.kafka.model.EventTracker;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/1 12:01
 * @description
 */
@Data
@AllArgsConstructor
public class EventTrackerData<Object extends Serializable> implements Serializable {
    private String event;
    private String sid;
    @SerializedName("event_data")
    private Object eventData;
}
