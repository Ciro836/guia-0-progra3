package Repositorios;

import java.util.List;

public interface RepoGenerico<T>
{
    void agregar(T elemento);

    void eliminar(String id);

    T buscarPorId(String id);

    List<T> listar();
}
