package Excepciones;

public class StringVacioException extends RuntimeException
{
    public StringVacioException(String nombreCampo)
    {
        super("❌ Error: El " + nombreCampo + " no puede estar vacío.");
    }
}
