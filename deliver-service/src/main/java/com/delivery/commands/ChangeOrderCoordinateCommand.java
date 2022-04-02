package com.delivery.commands;

import javax.validation.constraints.NotBlank;

public class ChangeOrderCoordinateCommand extends BaseCommand<Long> {

    @NotBlank
    public String latitude;

    @NotBlank
    public String longitude;

    public ChangeOrderCoordinateCommand(Long id, String latitude, String longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
