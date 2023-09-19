package pl.training.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.training.camel.as400.As400Component;

//@EnableJpaRepositories
@Configuration
public class CamelConfiguration {

    @Bean
    public CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {
                var jmsComponent = new ActiveMQComponent();
                jmsComponent.setBrokerURL("tcp://46.41.138.121:61616");
                context.addComponent("customJms", jmsComponent);
                //context.addComponent("as400", new As400Component());
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {

            }
        };
    }

    @Bean
    public CsvConverter csvConverter() {
        return new CsvConverter();
    }

}
