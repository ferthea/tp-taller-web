package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.UserDaoImpl;
import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginServiceImpl loginService = new LoginServiceImpl();

    @Mock
    private UserDaoImpl userDao;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test(expected = UserNotFoundException.class)
    public void testearQueTireExcepcionCuandoNoEncuentraUnUsuario() throws UserNotFoundException{
        User user = new User();
        user.setEmail("nombre@correo.com");
        user.setPassword("123456");

        when(loginService.consultarUsuario(user)).thenThrow(UserNotFoundException.class);
        loginService.consultarUsuario(user);
    }
}
