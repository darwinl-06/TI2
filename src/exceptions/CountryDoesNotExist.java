package exceptions;

public class CountryDoesNotExist extends Exception {
    private static final long serialVersionUID = 1L;

    public CountryDoesNotExist(){
        super("ERROR EL PAIS NO EXISTE");
    }
}
