package com.itstep.java.ageev.courseworkjavaspringrest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name ="users_app")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@EqualsAndHashCode(of={"id"})
public class User implements Serializable {
    @Id
    @JsonView(Views.IdName.class)
    String id;
    @JsonView(Views.IdName.class)
    String name;
    @JsonView(Views.IdName.class)
    String userPic;
    String email;
    @JsonView(Views.FullProfile.class)
    String gender;
    @JsonView(Views.FullProfile.class)
    String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullProfile.class)
    LocalDateTime lastVisit;

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    @JsonView(Views.FullProfile.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    Set<User> subscriptions = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    @JsonView(Views.FullProfile.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    Set<User> subscribers = new HashSet<>();
}
