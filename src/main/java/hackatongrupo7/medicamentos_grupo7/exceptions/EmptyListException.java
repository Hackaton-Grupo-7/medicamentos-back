package hackatongrupo7.medicamentos_grupo7.exceptions;

public class EmptyListException extends AppException {
    public EmptyListException() {
        super("The list is empty");
    }
}
