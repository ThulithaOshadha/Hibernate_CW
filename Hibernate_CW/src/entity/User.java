package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    private String userName;
    private String password;
}
