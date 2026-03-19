package Excepciones;

public class IdInvalidoException extends RuntimeException
{
    public IdInvalidoException()
    {
        super("El ID no puede ser negativo ni igual a cero.");
    }
}
