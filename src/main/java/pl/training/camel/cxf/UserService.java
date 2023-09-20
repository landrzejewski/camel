package pl.training.camel.cxf;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@WebService(targetNamespace = "http://training.pl/users")
public class UserService {

    private Map<String, User> users = new HashMap<>();

    public void add(User user) {
        users.put(user.getLastName(), user);
    }

    public User getByLastName(String lastName) {
        return users.get(lastName);
    }

}
