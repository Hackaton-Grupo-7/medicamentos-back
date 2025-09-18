package hackatongrupo7.medicamentos_grupo7.exceptions;

public class NotFoundException extends AppException {
  public NotFoundException(String item) {
    super(item + " not found!!");
  }}
