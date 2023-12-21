package ru.sadykoff.skillbox.app.services;

import org.apache.log4j.Logger;
import ru.sadykoff.skillbox.web.dto.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);

    public boolean authenticate(LoginForm loginFrom) {
        logger.info("delete a book by scale: " + loginFrom);
        return loginFrom.getUsername().equals("root") && loginFrom.getPassword().equals("12345");
    }
}
