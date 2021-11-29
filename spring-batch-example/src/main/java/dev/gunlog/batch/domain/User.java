package dev.gunlog.batch.domain;

import dev.gunlog.batch.domain.enums.Grade;
import dev.gunlog.batch.domain.enums.SocialType;
import dev.gunlog.batch.domain.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = {"idx", "email"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String name;
    private String password;
    private String email;
    private String principal;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public User(Long idx, String name, String password, String email, String principal, SocialType socialType, UserStatus status, Grade grade, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.idx = idx;
        this.name = name;
        this.password = password;
        this.email = email;
        this.principal = principal;
        this.socialType = socialType;
        this.status = status;
        this.grade = grade;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public User setInactive() {
        status = UserStatus.INACTIVE;
        return this;
    }
}