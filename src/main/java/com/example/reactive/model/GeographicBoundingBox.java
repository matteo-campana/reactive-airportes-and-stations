package com.example.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GeographicBoundingBox {
    // Geographic bounding box (lat0, lon0, lat1, lon1)

    private double lat0;
    private double lon0;
    private double lat1;
    private double lon1;
}
