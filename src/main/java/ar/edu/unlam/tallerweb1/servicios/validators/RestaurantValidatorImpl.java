package ar.edu.unlam.tallerweb1.servicios.validators;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.util.Arrays;

@Service("restaurantValidator")
public class RestaurantValidatorImpl implements RestaurantValidator {

    @Inject
    private RestaurantService restaurantService;

    public ValidatorResult validarDatosDelRestaurant(Restaurant restaurant, MultipartFile foto){
        ValidatorResult resultado = new ValidatorResult();
        String[] extensiones = {"jpg", "jpeg", "png"};
        String fotoExtension = "";

        if(restaurant.getNombre().length() < 4){
            resultado.agregarError("El nombre debe tener al menos 4 caracteres");
        }

        if(restaurant.getDireccion().length() < 5){
            resultado.agregarError("La direccion debe tener al menos 5 caracteres");
        }

        if(restaurant.getTipo() == null || restaurant.getTipo().length() < 4){
            resultado.agregarError("Debe seleccionar una categoría");
        }

        if(restaurant.getMaximaCantidadDeClientes() == null){
            resultado.agregarError("Ingrese una capacidad valida");
        }

        if(foto.isEmpty()){
            resultado.agregarError("Debe ingresar una foto de su restaurant");
        }else{
            fotoExtension = foto.getOriginalFilename().split("\\.")[1];
        }

        if(!foto.isEmpty() && !Arrays.asList(extensiones).contains(fotoExtension)){
            resultado.agregarError("Solamente se aceptan fotos con extencón .jpg, .jpeg o .png");
        }

        if(resultado.getErrores().size() == 0) resultado.setResultado(true);
        return resultado;
    }
}
