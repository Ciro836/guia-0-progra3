package Excepciones;

public class IdentificadorDuplicadoException extends RuntimeException
{
    public IdentificadorDuplicadoException(String message)
    {
        super(message);
    }

    public IdentificadorDuplicadoException()
    {
        super("Identificador duplicado. Intente con otro id...");
    }
}
