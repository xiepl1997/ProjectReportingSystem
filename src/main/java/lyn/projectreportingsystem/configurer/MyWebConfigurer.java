package lyn.projectreportingsystem.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {

    /**
     * 配置视图映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
//        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/members.html").setViewName("members");
        registry.addViewController("/projectform.html").setViewName("projectform");
        registry.addViewController("/newteam.html").setViewName("newteam");
        registry.addViewController("/myteam.html").setViewName("myteam");
        registry.addViewController("/projectlist.html").setViewName("projectlist");
        registry.addViewController("/creatproject.html").setViewName("creatproject");
    }

    /**
     * 配置拦截路径，拦截controller中的请求路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注意不拦截css和js
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/register", "static/**", "/bootstrap-3.3.7-dist/**","/css/**",
                        "/images/**","/assets/**", "/js/**", "/templates/**");
    }
}
