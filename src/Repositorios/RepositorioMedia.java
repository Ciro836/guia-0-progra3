package Repositorios;

import Excepciones.IdentificadorDuplicadoException;
import Modelo.Media;

import java.util.*;

public class RepositorioMedia<T extends Media> implements RepoGenerico<T>
{
    private final Map<Integer, T> mapa = new HashMap<>();

    @Override
    public void agregar(T elemento) throws IdentificadorDuplicadoException
    {
        if (mapa.containsKey(elemento.getId()))
        {
            throw new IdentificadorDuplicadoException("El ID " + elemento.getId() + " ya está en el sistema.");
        }
        else
        {
            mapa.put(elemento.getId(), elemento);
        }
    }

    @Override
    public void eliminar(int id)
    {
        mapa.remove(id);
    }

    @Override
    public T buscarPorId(int id)
    {
        return mapa.get(id);
    }

    @Override
    public void mostrar()
    {
        List<T> lista = new ArrayList<>(mapa.values());

        lista.sort(Comparator.comparing(T::getTitulo));

        lista.forEach(System.out::println);
    }

    @Override
    public List<T> filtrarPorGenero(String genero)
    {
        List<T> lista = new ArrayList<>(mapa.values());

        List<T> listaFiltrado = new ArrayList<>();

        for (T elemento : lista)
        {
            if (elemento.getGenero().equals(genero))
            {
                listaFiltrado.add(elemento);
            }
        }

        return listaFiltrado;
    }
}
