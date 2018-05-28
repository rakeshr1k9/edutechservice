package in.ogmatech.edutech.edutechservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter{

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(new OAuthRequestMatcher())
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll();
               /* // when restricting access to 'Roles' you must remove "ROLE_" part role
                // for "ROLE_USER" use only "USER"
                .antMatchers("/api/director").access("hasAnyRole('DIRECTOR')")
                .antMatchers("/api/admin").hasRole("ADMIN")
                // restricting all access to /api/** to authenticated users
                .antMatchers("/api/**").authenticated();*/
    }

    private static class OAuthRequestMatcher implements RequestMatcher {
        public boolean matches(HttpServletRequest request) {
            // Determine if the resource called is "/api/**"
            String path = request.getServletPath();
            if(path.length() >= 5){
                path=path.substring(0,5);
                boolean isApi = path.equals("/api/");
                return isApi;
            }
            else return false;
        }
    }


}
