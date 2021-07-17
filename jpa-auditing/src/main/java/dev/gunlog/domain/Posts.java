package dev.gunlog.domain;

import dev.gunlog.domain.common.BaseEntity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Posts extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @Builder
    public Posts(Long id, @NotNull String title, @NotNull String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }
}