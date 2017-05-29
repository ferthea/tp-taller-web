package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.UserDao;
import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Inject
    private UserDao loginServiceDao;

    @Override
    public User consultarUsuario(User user) throws UserNotFoundException {
        return loginServiceDao.consultarUsuario(user);
    }
}
