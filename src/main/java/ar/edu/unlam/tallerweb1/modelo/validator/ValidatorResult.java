package ar.edu.unlam.tallerweb1.modelo.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorResult {

    private Boolean resultado = false;
    private List<String> errores = new ArrayList<>();

    public Boolean getResultado(){
        return resultado;
    }

    public void setResultado(Boolean resultado){
        this.resultado = resultado;
    }

    public List<String> getErrores(){
        return this.errores;
    }

    public void setErrores(List<String> errores){
        this.errores = errores;
    }

    public void agregarError(String error){
        this.errores.add(error);
    }
}