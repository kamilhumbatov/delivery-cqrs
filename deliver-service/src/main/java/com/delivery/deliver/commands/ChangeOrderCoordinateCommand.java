package com.delivery.deliver.commands;

import javax.validation.constraints.NotBlank;

public class ChangeOrderCoordinateCommand extends BaseCommand<String> {

    @NotBlank
    public String latitude;

    @NotBlank
    public String longitude;

    public ChangeOrderCoordinateCommand(String id, String latitude, String longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
