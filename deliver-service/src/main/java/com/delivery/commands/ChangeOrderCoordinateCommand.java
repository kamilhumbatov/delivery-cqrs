package com.delivery.commands;

public class ChangeOrderCoordinateCommand extends BaseCommand<Long> {

    public String latitude;
    public String longitude;

    public ChangeOrderCoordinateCommand(Long id, String latitude, String longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
