package com.delivery.deliver.events;

public class DeliverOrderCreatedEvent extends BaseEvent<String> {

    public String owner;
    public String latitude;
    public String longitude;

    public DeliverOrderCreatedEvent(String id, String owner, String latitude, String longitude) {
        super(id);
        this.owner = owner;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
