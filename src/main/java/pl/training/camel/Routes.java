package pl.training.camel;

import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // env:VARIABLE_NAME // using ENV
        // RAW(value) // escaping invalid uri value
        // #name // accessing registered element from context
        from("file:data/input?noop=true&delay={{file.interval}}&filter=#ymlFilter")
                //.filter(new FileTypePredicate())
                .process(new LoggingProcessor())
                .to("file:data/output");
    }

}
