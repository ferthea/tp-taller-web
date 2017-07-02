package ar.edu.unlam.tallerweb1.servicios.validators;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by fthea on 7/1/17.
 */
public interface RestaurantValidator {

    ValidatorResult validarDatosDelRestaurant(Restaurant restaurant, MultipartFile foto);
}
