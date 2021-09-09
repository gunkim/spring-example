package dev.gunlog.app.dto;

import dev.gunlog.app.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoResponseDto {
    private String name;
    private int age;

    @Builder
    public UserInfoResponseDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public UserInfoResponseDto(User user) {
        this.name = user.getName();
        this.age = user.getAge();
    }
}
