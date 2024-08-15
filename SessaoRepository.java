package com.clinicaestetica.Repositories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.clinicaestetica.Models.Sessao;
import com.clinicaestetica.Repositories.Common.Repository;

public class SessaoRepository extends Repository<Sessao> {

    public SessaoRepository() {
        super(Sessao.class);
    }
    
    public Sessao obterPorId(int id) {
        Sessao sessao = null;

        var clinicas = obterTodos();

        for (var sessaoCadastrada : clinicas) {
            if (sessaoCadastrada.getId() == id) {
                sessao = sessaoCadastrada;
                break;
            }
        }

        return sessao;
    }

    public void editar(Sessao sessao) {
        var file = new File(filePath);

        if (file.exists()) {
            var sessoesCadastradas = lerArquivo(file);

            for (var sessaoCadastrada : sessoesCadastradas) {
                if (sessaoCadastrada.getId() == sessao.getId()) {

                    sessaoCadastrada.setCliente(sessao.getCliente());
                    sessaoCadastrada.setClinica(sessao.getClinica());
                    sessaoCadastrada.setProfissional(sessao.getProfissional());
                    sessaoCadastrada.setValor(sessao.getValor());
                    //aqui
                    //sessaoCadastrada.alterarStatus(sessao.getStatus(), sessao.getData());
                    sessaoCadastrada.setStatus(sessao.getStatus());
                    sessaoCadastrada.setPagamento(sessao.getPagamento());
                    break;
                }
            }

            try (var objOutput = new ObjectOutputStream(new FileOutputStream(file))) {
                objOutput.writeObject(sessoesCadastradas);
                objOutput.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
