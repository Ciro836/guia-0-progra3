package Repositorios;

import Modelo.Media;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMedia<T extends Media> implements RepoGenerico<T>
{
    private final List<T> lista = new ArrayList<>();

    @Override
    public void agregar(T elemento)
    {
        lista.add(elemento);
    }

    @Override
    public void eliminar(int id)
    {
        lista.remove(id);
    }

    @Override
    public T buscarPorId(int id)
    {
        return lista.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<T> listar()
    {
        return lista;
    }
}
