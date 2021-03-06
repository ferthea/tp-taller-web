package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;

import ar.edu.unlam.tallerweb1.enums.TipoDeRestaurant;
import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;
import ar.edu.unlam.tallerweb1.servicios.LoginService;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import ar.edu.unlam.tallerweb1.servicios.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.exceptions.UserAlreadyExistsException;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller
public class ControladorLogin {

	@Inject
	private ServicioRegistro servicioRegistro;

	@Inject
	private LoginService loginService;

	@Inject
	private RestaurantService restaurantService;

	@Inject
	private UserValidator userValidator;


	@RequestMapping("/registro")
	public ModelAndView registro(){
		ModelMap model = new ModelMap();
		User user = new User();
		model.put("user", user);
		return new ModelAndView("registro", model);
	}
	
	@RequestMapping(path = "/registro", method = RequestMethod.POST)
	public ModelAndView validarRegistro(@ModelAttribute("user") User user){
		ModelMap model = new ModelMap();

		ValidatorResult validator = userValidator.validarUsuario(user);

		if(validator.getResultado()){
			try{
				servicioRegistro.registrarUser(user);
				model.put("registrado", "true");
			}
			catch(UserAlreadyExistsException e){
				validator.agregarError("Ya existe un usuario con ese email");
			}
		}
		
		model.put("user", user);
		model.put("errores", validator.getErrores());
		return new ModelAndView("registro", model);
	}

	@RequestMapping("/login")
	public ModelAndView irALogin(){
		ModelMap model = new ModelMap();
		User user = new User();
		model.put("user", user);
		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("user") User user,
									 HttpServletRequest request){
		ModelMap model = new ModelMap();
		List<String> errores = new ArrayList<>();

		if(user.getEmail().length() < 6){
			errores.add("El email debe tener al menos 6 caracteres");
		}
		if(user.getPassword().length() < 6){
			errores.add("La contraseña debe tener al menos 6 caracteres");
		}

		if(errores.size() == 0){
			try{
				User usuario = loginService.consultarUsuario(user);
				if(usuario != null){
					request.getSession().setAttribute("user", usuario);
					return new ModelAndView("redirect:/index");
				}
			}
			catch(UserNotFoundException e){
				errores.add("Datos incorrectos");
			}
		}
		model.put("errores", errores);
		return  new ModelAndView("login", model);
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		request.getSession().invalidate();
		return new ModelAndView("redirect:/index");
	}

	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelMap model = new ModelMap();
		List<Restaurant> restaurantes = restaurantService.obtenerListaDeRestaurants();
		model.put("restaurants", restaurantes);
		model.put("categorias", TipoDeRestaurant.values());
		return new ModelAndView("index", model);
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView home(){
		return new ModelAndView("redirect:/index");
	}

}
