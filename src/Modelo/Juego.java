package Modelo;

public class Juego extends Media
{
    private int numVersion;

    public Juego(int id, String titulo, String creador, String genero, int numVersion)
    {
        super(id, titulo, creador, genero);
        this.numVersion = numVersion;
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
    public int compareTo(Media o)
    {
        return 0;
    }
}
