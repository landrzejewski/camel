package pl.training.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // env:VARIABLE_NAME // using ENV
        // RAW(value) // escaped uri value
        // #name // accessing registered element from context
        from("file:data/input?noop=true&delay={{file.interval}}&filter=#ymlFilter")
                //.filter(new FileTypePredicate())
                .process(new LoggingProcessor())
                .to("file:data/output");

        from("ftp://localhost?delay=5000&delete=true&passiveMode=true&username=user&password=123")
                .to("activemq:orders");
                //.toD("jms:queue:${header.finalDestination}"); // dynamic destination

        from("activemq:orders")
                .log("Message received: ${header.CamelFileName}")
                .to("log:pl.training.camel");
    }

}
