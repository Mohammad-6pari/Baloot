package Baloot.Business.DTOs;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class BuyListItemDTO {
    @NonNull
    public String username;
    @NonNull
    public Integer commodityId;
}
