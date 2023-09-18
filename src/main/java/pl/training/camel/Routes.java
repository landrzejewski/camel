package pl.training.camel;

import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
                                                  //env:OS_VARIABLE
        from("file:data/input?noop=true&delay={{file.interval}}")
                .filter(new FileTypePredicate())
                .process(new LoggingProcessor())
                .to("file:data/output");
    }

}
