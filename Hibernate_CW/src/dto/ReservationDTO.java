package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String resID;
    private String date;
    private String Sid;
    private String roomID;
    private String status;
}
