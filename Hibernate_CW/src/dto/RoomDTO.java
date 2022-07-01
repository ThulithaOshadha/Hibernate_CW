package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    String roomID;
    String type;
    BigDecimal keyMoney;
    int roomQty;
}
