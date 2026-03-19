package Excepciones;

public class NumVersionInvalidoException extends RuntimeException
{
    public NumVersionInvalidoException()
    {
        super("El numero de versión no puede ser menor o igual que 0. Debe ser positivo.");
    }
}
