package com.clinicaestetica.Repositories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.clinicaestetica.Models.Profissional;
import com.clinicaestetica.Repositories.Common.Repository;

public class ProfissionalRepository extends Repository<Profissional> {

    public ProfissionalRepository() {
        super(Profissional.class);
    }

    public Profissional obterPorId(int id) {
        Profissional profissional = null;

        var profissionaisCadastrados = obterTodos();

        for (var profissionalCadastrado : profissionaisCadastrados) {
            if (profissionalCadastrado.getId() == id) {
                profissional = profissionalCadastrado;
                break;
            }
        }

        return profissional;
    }

    public void editar(Profissional profissional) {
        var file = new File(filePath);

        if (file.exists()) {
            var profissionaisCadastrados = lerArquivo(file);

            for (var profissionalCadastrado : profissionaisCadastrados) {
                if (profissionalCadastrado.getId() == profissional.getId()) {

                    profissionalCadastrado.setContato(profissional.getContato());
                    profissionalCadastrado.setCpf(profissional.getCpf());
                    profissionalCadastrado.setDisponivel(profissional.isDisponivel());
                    profissionalCadastrado.setGenero(profissional.getGenero());
                    profissionalCadastrado.setNomeCompleto(profissional.getNomeCompleto());
                    profissionalCadastrado.setContabAtendimentos(profissional.getContabAtendimentos());
                    break;
                }
            }

            try (var objOutput = new ObjectOutputStream(new FileOutputStream(file))) {
                objOutput.writeObject(profissionaisCadastrados);
                objOutput.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
