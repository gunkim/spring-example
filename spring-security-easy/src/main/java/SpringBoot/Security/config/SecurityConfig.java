package SpringBoot.Security.config;

import SpringBoot.Security.config.Service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * 시큐리티 설정
 */
@Component
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * URL에 관한 권한 설정
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/security/guest1").hasAnyRole(Role.GUEST.name(), Role.USER.name(), Role.ADMIN.name())
                .antMatchers("/security/guest2").hasAnyRole(Role.GUEST.name(), Role.USER.name(), Role.ADMIN.name())
                .antMatchers("/security/user1").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .antMatchers("/security/admin1").hasRole(Role.ADMIN.name())
                .antMatchers("/security/admin2").hasRole(Role.ADMIN.name())
              .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("id")
                .passwordParameter("pw")
              .and()
                .logout()
                .logoutSuccessUrl("/login")
                .logoutUrl("/logout");
    }

    /**
     * 자원 리소스 파일들 권한 예외
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    /**
     * 간단 테스트를 위해 로그인 계정 등록
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build();
        UserDetails guest =
                User.withDefaultPasswordEncoder()
                        .username("guest")
                        .password("guest")
                        .roles("GUEST")
                        .build();
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user, guest, admin);
    }
}