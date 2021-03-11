package com.itstep.java.ageev.courseworkjavaspringrest.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name ="users_app")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {
    @Id
    String id;
    String name;
    String userPic;
    String email;
    String gender;
    String locale;
    LocalDateTime lastVisit;
}
