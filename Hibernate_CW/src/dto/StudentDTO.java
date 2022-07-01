package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String Sid;
    private String name;
    private String address;
    private String contactNo;
    private String dob;
    private String gender;

}
