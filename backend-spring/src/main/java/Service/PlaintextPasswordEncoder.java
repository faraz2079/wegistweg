package Service;

import org.springframework.stereotype.Service;

/**
 * TODO Replace with proper implementation if necessary.
 * The project was not compiling, because the class `org.springframework.security.crypto.password.PasswordEncoder` was not in the classpath.
 * I have created this simple implementation to make the project compile again.
 */
@Service
public class PlaintextPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String password) {
        return password;
    }
}
