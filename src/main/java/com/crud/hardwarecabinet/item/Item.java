package com.crud.hardwarecabinet.item;

import org.springframework.data.annotation.Id;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.*;

public class Item {
    private Long id;

    //Product name parameters: name cannot be empty and must be a human readable string
    @NotNull(message = "name is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "name must be a string")
    private String name;

    //Product price parameters: price cannot be empty and must be a positive number
    @NotNull(message = "price is required")
    @Positive(message = "price must be positive")
    private Long price;

    //Product description parameters: description cannot be empty and must be a human readable string
    @NotNull(message = "description is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "description must be a string")
    private String description;

    //Product image parameters: required, must be a URL
    @NotNull(message = "image is required")
    @URL(message = "image must be a URL")
    private String image;

    public Item() {

    }

    public Item(
            Long id,
            String name,
            Long price,
            String description,
            String image
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Item updateWith(Item item) {
        return new Item(
                this.id,
                item.name,
                item.price,
                item.description,
                item.image
        );
    }
}
