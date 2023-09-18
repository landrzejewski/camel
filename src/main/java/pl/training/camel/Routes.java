package pl.training.camel;

import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:data/input?noop=true&delay=1000")
                .to("file:data/output");
    }

}
