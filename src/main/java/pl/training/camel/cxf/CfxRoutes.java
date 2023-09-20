package pl.training.camel.cxf;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CfxRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("cxf://UserService?serviceClass=pl.training.camel.cxf.UserService"
        + "&serviceName=Users")
                .to("log:${body}");
    }

}
