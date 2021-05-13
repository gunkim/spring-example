package SpringBoot.Security.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 유저 권한 enum
 */
@RequiredArgsConstructor
@Getter
public enum Role {
    USER("유저", "ROLE_USER"),
    GUEST("게스트", "ROLE_GUEST"),
    ADMIN("관리자", "ROLE_ADMIN");

    private final String title;
    private final String key;
}