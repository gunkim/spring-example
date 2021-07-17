package dev.gunlog.domain;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Integer age;

    @Builder
    public Member(Long id, @NotNull String username, @NotNull String password, @NotNull Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }
}