package SpringBoot.Security.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 컨트롤러로 구현해도 되지만 간단하게 테스트하기 위해서 WebMvcConfigurer를 통해 구현
 */
@Configuration
class MvcConfigimplements implements WebMvcConfigurer {
    /**
     * URL매핑
     * @param registry
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/security/public");
        registry.addViewController("/security/guest1");
        registry.addViewController("/security/guest2");
        registry.addViewController("/security/user1");
        registry.addViewController("/security/admin1");
        registry.addViewController("/security/admin2");
        registry.addViewController("/index");
        registry.addViewController("/list");
        registry.addViewController("/login");
        registry.addViewController("/logout");
        registry.addViewController("/authError");
    }

    /**
     * 403 오류가 났을 경우 리다이렉트 URL 설정
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/authError"));
        };
    }
}
