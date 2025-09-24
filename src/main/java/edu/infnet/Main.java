package edu.infnet;


import edu.infnet.controller.Controller;
import edu.infnet.repository.PersistCSV;
import io.javalin.Javalin;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        var app = Javalin.create().start(8080);
//        Controller.config(app);
        PersistCSV.save("lohran fellipe mendes mendes; 12; 12345678900");
        List<String[]> list = PersistCSV.read();

        // Acessar campos individuais como strings
        String[] primeiraLinha = list.get(8);
        System.out.println("Nome: " + primeiraLinha[0]);
        System.out.println("Idade: " + primeiraLinha[1]);
        System.out.println("CPF: " + primeiraLinha[2]);

        // Ou imprimir toda a linha usando Arrays.toString()
        System.out.println("Linha completa: " + java.util.Arrays.toString(primeiraLinha));

        // Ou percorrer todos os campos
        for (int i = 0; i < primeiraLinha.length; i++) {
            System.out.println("Campo " + i + ": " + primeiraLinha[i]);
        }

    }
}