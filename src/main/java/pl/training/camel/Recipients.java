package pl.training.camel;

import org.apache.camel.RecipientList;
import org.apache.camel.language.xpath.XPath;
import org.springframework.stereotype.Component;

@Component
public class Recipients {

    @RecipientList
    public String[] recipients(/*@XPath("/order/@premium")*/ String premium) {
        return new String[] {"direct:report"};
    }

    public String accounting() {
        return "direct:report";
    }

}
