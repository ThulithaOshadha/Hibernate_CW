package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reservation")
public class Reservation {
    @Id
    private String resID;
    private String date;
    private String Sid;
    private String roomID;
    private String status;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Room room;

    public Reservation(String resID, String date, String sid, String roomID, String status) {
        this.resID = resID;
        this.date = date;
        this.Sid = sid;
        this.roomID = roomID;
        this.status = status;
    }
}
