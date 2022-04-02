package com.delivery.deliver.commands;

import javax.validation.constraints.NotBlank;

public class CreateOrderCommand extends BaseCommand<String> {

    @NotBlank
    public String owner;

    @NotBlank
    public String latitude;

    @NotBlank
    public String longitude;

    public CreateOrderCommand(String id, @NotBlank String owner, @NotBlank String latitude, @NotBlank String longitude) {
        super(id);
        this.owner = owner;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
