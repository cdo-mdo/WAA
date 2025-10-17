package cs545de.lab1servletcalculator;

import cs545de.lab1servletcalculator.servlets.CalculatorServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ServletConfig {
    @Bean
    ServletRegistrationBean<CalculatorServlet> legacyServlet() {
        return new ServletRegistrationBean<>(new CalculatorServlet(), "/legacy/*" );
    }
}
