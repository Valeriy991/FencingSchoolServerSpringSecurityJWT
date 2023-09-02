package com.valeriygulin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    @JsonIgnore
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime regDate = LocalDateTime.now();

    /*public User(@NonNull String login, @NonNull String password, @NonNull String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }*/
}
