package com.delivery.events;

public class DeliverOrderCoordinateChangedEvent extends BaseEvent<String> {

    public String latitude;
    public String longitude;

    public DeliverOrderCoordinateChangedEvent(String id, String latitude, String longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
