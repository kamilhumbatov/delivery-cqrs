package com.delivery.commands;

public class ChangeOrderCoordinateCommand extends BaseCommand<String> {

    public String latitude;
    public String longitude;

    public ChangeOrderCoordinateCommand(String id, String latitude, String longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
