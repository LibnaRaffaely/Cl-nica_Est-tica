package com.clinicaestetica.Repositories.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.clinicaestetica.Models.Cliente;
import com.clinicaestetica.Models.Clinica;
import com.clinicaestetica.Models.Pacote;
import com.clinicaestetica.Models.Profissional;
import com.clinicaestetica.Models.Sessao;

public abstract class Repository<T> {
    protected static String filePath;

    private final static String FilePathClinicas = "Clinicas.bin";
    private final static String FilePathProfissionais = "Profissionais.bin";
    private final static String FilePathSessoes = "Sessoes.bin";
    private final static String FilePathPacotes = "Pacotes.bin";
    private final static String FilePathClientes = "Clientes.bin";

    public Repository(Class<T> classe) {
        if (classe == Clinica.class) {
            filePath = FilePathClinicas;
        } else if (classe == Profissional.class) {
            filePath = FilePathProfissionais;
        } else if (classe == Sessao.class) {
            filePath = FilePathSessoes;
        } else if (classe == Pacote.class) {
            filePath = FilePathPacotes;
        } else if (classe == Cliente.class) {
            filePath = FilePathClientes;
        } else {
            filePath = "Desconhecido.bin";
        }
    }

    public List<T> obterTodos() {
        var objetos = new ArrayList<T>();
        var file = new File(filePath);

        if (file.exists()) {
            var objetosCadastrados = lerArquivo(file);
            objetos.addAll(objetosCadastrados);
        }

        return objetos;
    }

    public void registrar(T objeto) {
        var file = new File(filePath);

        try {
            var objetos = new ArrayList<T>();

            if (file.exists()) {
                var objetosCadastrados = lerArquivo(file);
                objetos.addAll(objetosCadastrados);
            }

            objetos.add(objeto);

            var objOutput = new ObjectOutputStream(new FileOutputStream(file));
            objOutput.writeObject(objetos);
            objOutput.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected List<T> lerArquivo(File arq) {
        var objetos = new ArrayList<T>();
        
        try {
            var objInput = new ObjectInputStream(new FileInputStream(arq));
            objetos = (ArrayList<T>)objInput.readObject();
            objInput.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return objetos;
    }
}
