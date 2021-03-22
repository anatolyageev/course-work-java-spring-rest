package com.itstep.java.ageev.courseworkjavaspringrest.domain;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    @JsonView(Views.IdName.class)
    private Long id;
    @JsonView(Views.IdName.class)
    private String text;
    @ManyToOne
    @JoinColumn(name = "message_id")
    @JsonView(Views.FullComment.class)
    private Message message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,updatable = false)
    @JsonView(Views.IdName.class)
    private User author;

}
