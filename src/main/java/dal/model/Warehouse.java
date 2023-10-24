package dal.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Warehouse {
    private int id;
    private String name;
    private String location;
}