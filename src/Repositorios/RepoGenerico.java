package Repositorios;

import Modelo.Media;

import java.util.List;

public interface RepoGenerico<T>
{
    void agregar(T elemento);

    void eliminar(int id);

    T buscarPorId(int id);

    List<T> listar();
}
