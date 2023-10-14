package br.com.rotasdosol.repositorios;

import java.util.List;

public interface Repositorio<T> {
    void criar(T object);
    List<T>listar();
    Object atualizar (T object);
    void deletar (Integer id);

}
