package one.goranson.codegenerationissue;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ComponentScan(MyConfiguration.BASE_PACKAGE)
@EnableAutoConfiguration
public class MyConfiguration extends WebSecurityConfigurerAdapter {
  public static final String BASE_PACKAGE = "one.goranson.codegenerationissue";
}
