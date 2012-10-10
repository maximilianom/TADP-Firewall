package utn.frba.tadp.firewall.impl.model.exceptions;

public class BloqueaRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BloqueaRequestException(String message) {
		super(message);
	}
}
