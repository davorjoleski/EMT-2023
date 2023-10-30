package mk.ukim.finki.wp.eshop.service.impl;

import mk.ukim.finki.wp.eshop.model.User;
import mk.ukim.finki.wp.eshop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.eshop.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.eshop.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.eshop.repository.InMemoryUserRepository;
import mk.ukim.finki.wp.eshop.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final InMemoryUserRepository inMemoryUserRepository;

    public AuthServiceImpl(InMemoryUserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return inMemoryUserRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        User user = new User(username,password,name,surname);
        return inMemoryUserRepository.saveOrUpdate(user);

    }
}
