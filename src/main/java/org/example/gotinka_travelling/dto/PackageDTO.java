package org.example.gotinka_travelling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageDTO {
    private int packageId;

    private String packageName;
    private String destination;
    private Double price;
    private String description;
    private Integer duration; // Days
    private Boolean active;
    private String imagePath;
    private boolean isDeleted = false;
}
