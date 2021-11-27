package one.goranson.codegenerationissue;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args);
    log.info("Running Spring version: {}", SpringVersion.getVersion());
    log.info("Here we go");
  }

  @Bean
  public ServletRegistrationBean<DispatcherServlet> my(ApplicationContext parent) {
    return createChildServlet(
        List.of("/my/*"),
        MyConfiguration.class,
        "my",
        parent);
  }

  private ServletRegistrationBean<DispatcherServlet> createChildServlet(List<String> urlMappings,
      Class<?> configuration, String servletRegistrationBeanName, ApplicationContext parent) {

    var applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.register(configuration);
    applicationContext.setParent(parent);
    var dispatcherServlet = new DispatcherServlet(applicationContext);
    var servletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet,
        urlMappings.toArray(new String[] {}));
    servletRegistrationBean.setName(servletRegistrationBeanName);
    servletRegistrationBean.setLoadOnStartup(1);
    return servletRegistrationBean;
  }

}
