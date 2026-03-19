package Excepciones;

public class NumVersionInvalidoException extends RuntimeException
{
    public NumVersionInvalidoException()
    {
        super("El numero de versión no puede ser menor que 0.");
    }
}
