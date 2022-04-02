package com.delivery.deliver.commands;

import javax.validation.constraints.NotBlank;

public class CreateOrderCommand extends BaseCommand<Long> {

    @NotBlank
    public String owner;

    @NotBlank
    public String latitude;

    @NotBlank
    public String longitude;

    public CreateOrderCommand(Long id, String latitude, String longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
