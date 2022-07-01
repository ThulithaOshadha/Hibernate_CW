package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Array;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "room")
public class Room {

    @Id
    private String id;
    private String type;
    @Column(columnDefinition = "DECIMAL")
    private BigDecimal keyMoney;
    private int qty;
    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList();

    public Room(String id, String type, BigDecimal keyMoney, int qty) {
        this.id = id;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }
}
