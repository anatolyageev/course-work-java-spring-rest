package com.itstep.java.ageev.courseworkjavaspringrest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
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
public class User implements Serializable {
    @Id
    @JsonView(Views.IdName.class)
    String id;
    @JsonView(Views.IdName.class)
    String name;
    @JsonView(Views.IdName.class)
    String userPic;
    String email;
    String gender;
    String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime lastVisit;
}
