package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "student")
public class Student {

    @Id
    private String Sid;
    private String name;
    private String address;
    private String ContactNo;
    private String dob;
    private String gender;
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList();

    public Student(String sid, String name, String address, String contactNo, String dob, String gender) {
        Sid = sid;
        this.name = name;
        this.address = address;
        ContactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
    }
}

