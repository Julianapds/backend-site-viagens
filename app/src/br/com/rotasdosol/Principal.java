package br.com.rotasdosol;

import br.com.rotasdosol.entidades.Cliente;
import br.com.rotasdosol.repositorios.ClienteRepositorio;

import java.util.List;

public class Principal {
    public static void main(String[] args) {
        ClienteRepositorio clienteRepositorio=new ClienteRepositorio();
        List<Cliente> clientes=clienteRepositorio.listar();
        System.out.println(clientes);


    }
}
