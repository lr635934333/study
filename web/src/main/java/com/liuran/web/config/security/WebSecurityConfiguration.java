package com.liuran.web.config.security;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired(required = false)
    private SecurityProperties casSecurityConfig;

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties sp = new ServiceProperties();
        sp.setService(casSecurityConfig.getClientSecurity());
        sp.setSendRenew(false);
        return sp;
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
        casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
        return casAuthenticationProvider;
    }

    @Bean
    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
        return new UserDetailsService();
    }

    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator(casSecurityConfig.getServer());
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        casAuthenticationFilter.setFilterProcessesUrl("/j_spring_cas_security_check");
        return casAuthenticationFilter;
    }

    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(casSecurityConfig.getServerLogin());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        singleSignOutFilter.setCasServerUrlPrefix(casSecurityConfig.getServer());
        return singleSignOutFilter;
    }

    @Bean
    public LogoutFilter requestCasGlobalLogoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(casSecurityConfig.getServerLogout() + "?service="
                + casSecurityConfig.getClient(), new SecurityContextLogoutHandler());
        logoutFilter.setLogoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"));
        return logoutFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(casAuthenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(casAuthenticationFilter());
        http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint());
        http.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class)
                .addFilterBefore(requestCasGlobalLogoutFilter(), LogoutFilter.class);

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers(casSecurityConfig.getIgnore()).permitAll()
                .anyRequest().authenticated();

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
}
