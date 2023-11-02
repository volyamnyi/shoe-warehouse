package dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shoe {
    private Long id;
    private String brand;
    private String model;
    private Integer size;
    private Integer stock;
    private Warehouse warehouse;
}