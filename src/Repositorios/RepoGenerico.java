package Repositorios;

import java.util.List;

public interface RepoGenerico<T>
{
    void agregar(T elemento);

    void eliminar(int id);

    T buscarPorId(int id);

    void mostrar();

    List<T> filtrarPorGenero(String genero);
}
