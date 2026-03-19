package Excepciones;

public class FechaInvalidaException extends RuntimeException
{
    public FechaInvalidaException()
    {
        super("La fecha de lanzamiento no puede ser nula.");
    }
}
