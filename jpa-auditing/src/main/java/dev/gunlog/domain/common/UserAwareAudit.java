package dev.gunlog.domain.common;

import dev.gunlog.domain.Member;
import dev.gunlog.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAwareAudit implements AuditorAware<Member> {
    private final MemberRepository memberRepository;

    @NotNull
    @Override
    public Optional<Member> getCurrentAuditor() {
        Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new BadCredentialsException("로그인되지 않았습니다."));
        if (!authentication.isAuthenticated()) {
            return Optional.empty();
        }

        User user = (User) authentication.getPrincipal();
        return memberRepository.findByUsername(user.getUsername());
    }
}
