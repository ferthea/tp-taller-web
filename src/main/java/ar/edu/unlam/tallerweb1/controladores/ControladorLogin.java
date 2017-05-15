package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.exceptions.UserAlreadyExistsException;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller
public class ControladorLogin {

	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioRegistro servicioRegistro;
	
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
		List<String> errores = new ArrayList<String>();
		
		if(user.getNombre().length() < 3){
			errores.add("El campo Nombre debe tener al menos 3 caracteres.");
		}
		
		if(user.getApellido().length() < 3){
			errores.add("El campo Apellido debe tener al menos 3 caracteres.");
		}
		
		if(user.getPassword().length() < 6){
			errores.add("La contraseña debe tener al menos 6 caracteres.");
		}
		
		if(errores.size() == 0){
			try{
				servicioRegistro.registrarUser(user);
				model.put("registrado", "true");
			}
			catch(UserAlreadyExistsException e){
				errores.add("Ya existe un usuario con ese email");
			}
		}
		
		model.put("user", user);
		model.put("errores", errores);
		return new ModelAndView("registro", model);
	}
	
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("login", modelo);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario) {
		ModelMap model = new ModelMap();

		if (servicioLogin.consultarUsuario(usuario) != null) {
			return new ModelAndView("redirect:/home");
		} else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}
	
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home");
	}
	
	/*@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}*/
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("index");
	}
}
