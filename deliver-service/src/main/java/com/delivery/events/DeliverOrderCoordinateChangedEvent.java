package com.delivery.events;

public class DeliverOrderCoordinateChangedEvent extends BaseEvent<Long> {

    public String latitude;
    public String longitude;

    public DeliverOrderCoordinateChangedEvent(Long id, String latitude, String longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
