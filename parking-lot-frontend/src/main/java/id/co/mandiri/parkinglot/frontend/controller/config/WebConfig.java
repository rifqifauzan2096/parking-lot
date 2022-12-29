package id.co.mandiri.parkinglot.frontend.controller.config;

import id.co.mandiri.parkinglot.backend.model.system.ApplicationRole;
import id.co.mandiri.parkinglot.backend.model.system.ApplicationUser;
import id.co.mandiri.parkinglot.backend.repository.system.ApplicationRoleRepository;
import id.co.mandiri.parkinglot.backend.repository.system.ApplicationUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static Logger logger = LogManager.getLogger(WebConfig.class.getName());

    @Value("${message.path}")
    private String  messagePath;

    @Autowired
    private ApplicationUserRepository appUserRepository;

    @Autowired
    private ApplicationRoleRepository appRoleRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("loginPage");
    }


    @Bean(name = "messageSource") //set as reloadable
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("file:" + messagePath);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1);
        return messageSource;
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("Interceptor called");
        registry.addInterceptor(localeInterceptor());
    }


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("en")); //in = indonesia, en=english, cn=china, jp=japan
        return sessionLocaleResolver;
    }

    @PostConstruct
    private void createDefaultUser(){
        //check if user exist
        Long totalUser = appUserRepository.count();
        if(totalUser == 0){
            try {
                logger.info("No User in database, creating one");
                ApplicationUser user = new ApplicationUser("administrator",new BCryptPasswordEncoder().encode("admin123"),"admin@admin.com",true);
                user =  appUserRepository.save(user);

                logger.info("Creating Role Default");
                ApplicationRole userRole = new ApplicationRole("ROLE_ADMIN", user.getUsername());
                appRoleRepository.save(userRole);
                userRole = new ApplicationRole("ROLE_USER", user.getUsername());
                appRoleRepository.save(userRole);
            } catch (Exception e) {
                logger.error("Exception occurred! {}", e.getMessage());
                logger.info("Harus tetep jalan masseh");

            }

        }
    }
}
