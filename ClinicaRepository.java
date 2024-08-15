package com.clinicaestetica.Repositories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.clinicaestetica.Models.Clinica;
import com.clinicaestetica.Repositories.Common.Repository;

public class ClinicaRepository extends Repository<Clinica> {

    public ClinicaRepository() {
        super(Clinica.class);
    }
    
    public Clinica obterPorId(int id) {
        Clinica clinica = null;

        var clinicas = obterTodos();

        for (var clienteCadastrado : clinicas) {
            if (clienteCadastrado.getId() == id) {
                clinica = clienteCadastrado;
                break;
            }
        }

        return clinica;
    }

    public void editar(Clinica clinica) {
        var file = new File(filePath);

        if (file.exists()) {
            var clinicasCadastradas = lerArquivo(file);

            for (var clinicaCadastrada : clinicasCadastradas) {
                if (clinicaCadastrada.getId() == clinica.getId()) {

                    clinicaCadastrada.setEndereco(clinica.getEndereco());
                    clinicaCadastrada.setProfissionais(clinica.getProfissionais());
                    clinicaCadastrada.setContabilidadeClinica(clinica.getcontabilidadeClinica());
                    break;
                }
            }

            try (var objOutput = new ObjectOutputStream(new FileOutputStream(file))) {
                objOutput.writeObject(clinicasCadastradas);
                objOutput.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
