package Clases;

public class Juego implements Comparable<Juego>
{
    private int id;
    private String titulo;
    private String creador;
    private String genero;
    private int numVersion;

    public Juego(int id, String titulo, String creador, String genero, int numVersion)
    {
        this.id = id;
        this.titulo = titulo;
        this.creador = creador;
        this.genero = genero;
        this.numVersion = numVersion;
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

    public int getNumVersion()
    {
        return numVersion;
    }

    public void setNumVersion(int numVersion)
    {
        this.numVersion = numVersion;
    }


    @Override
    public int compareTo(Juego j)
    {
        return 0;
    }
}
