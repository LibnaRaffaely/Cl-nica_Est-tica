package com.clinicaestetica.Repositories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.clinicaestetica.Models.Pacote;
import com.clinicaestetica.Repositories.Common.Repository;

public class PacoteRepository extends Repository<Pacote> {

    public PacoteRepository() {
        super(Pacote.class);
    }
    
    public Pacote obterPorId(int id) {
        Pacote pacote = null;

        var pacotes = obterTodos();

        for (var pacoteCadastrado : pacotes) {
            if (pacoteCadastrado.getId() == id) {
                pacote = pacoteCadastrado;
                break;
            }
        }

        return pacote;
    }

    public void editar(Pacote pacote) {
        var file = new File(filePath);

        if (file.exists()) {
            var pacotesCadastrados = lerArquivo(file);

            for (var pacoteCadastrado : pacotesCadastrados) {
                if (pacoteCadastrado.getId() == pacote.getId()) {

                    pacoteCadastrado.setSessoes(pacote.getSessoes());
                    pacoteCadastrado.setTipoPacote(pacote.getTipoPacote());
                    break;
                }
            }

            try (var objOutput = new ObjectOutputStream(new FileOutputStream(file))) {
                objOutput.writeObject(pacotesCadastrados);
                objOutput.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
