package com.clinicaestetica.Repositories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.clinicaestetica.Models.Cliente;
import com.clinicaestetica.Repositories.Common.Repository;

public class ClienteRepository extends Repository<Cliente> {
    
    public ClienteRepository() {
        super(Cliente.class);
    }

    public Cliente obterPorId(int id) {
        Cliente cliente = null;

        var clientesCadastrados = obterTodos();

        for (var clienteCadastrado : clientesCadastrados) {
            if (clienteCadastrado.getId() == id) {
                cliente = clienteCadastrado;
                break;
            }
        }

        return cliente;
    }

    public void editar(Cliente cliente) {
        var file = new File(filePath);

        if (file.exists()) {
            var clientesCadastrados = lerArquivo(file);

            for (var clienteCadastrado : clientesCadastrados) {
                if (clienteCadastrado.getId() == cliente.getId()) {

                    clienteCadastrado.setContato(cliente.getContato());
                    clienteCadastrado.setCpf(cliente.getCpf());
                    clienteCadastrado.setGenero(cliente.getGenero());
                    clienteCadastrado.setNomeCompleto(cliente.getNomeCompleto());
                    clienteCadastrado.setPacotes(cliente.getPacotes());
                    clienteCadastrado.setContabSessoes(cliente.getContabSessoes());
                    break;
                }
            }

            try (var objOutput = new ObjectOutputStream(new FileOutputStream(file))) {
                objOutput.writeObject(clientesCadastrados);
                objOutput.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
