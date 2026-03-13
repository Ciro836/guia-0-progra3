package Modelo;

import java.time.LocalDateTime;

public class Expansion extends Media
{
    private LocalDateTime fecha;

    public Expansion(int id, String titulo, String creador, String genero, LocalDateTime fecha)
    {
        super(id, titulo, creador, genero);
        this.fecha = fecha;
    }

    public LocalDateTime getFecha()
    {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha)
    {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Media o)
    {
        return 0;
    }
}
