package Modelo;

public abstract class Media implements Comparable<Media>
{
    private int id;
    private String titulo;
    private String creador;
    private String genero;

    public Media(int id, String titulo, String creador, String genero)
    {
        this.id = id;
        this.titulo = titulo;
        this.creador = creador;
        this.genero = genero;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getCreador()
    {
        return creador;
    }

    public void setCreador(String creador)
    {
        this.creador = creador;
    }

    public String getGenero()
    {
        return genero;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }
}
