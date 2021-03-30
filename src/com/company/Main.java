package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String args[]) {

        String fileName = "alunos.csv";
        String fileTemp = "updated_alunos.csv";
        Scanner scan = new Scanner(System.in);

        System.out.println("Por favor, insira seu número de matrícula: ");

        String mat = scan.nextLine();
        Optional<String[]> catcher = Optional.empty();

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName));
             PrintWriter output = new PrintWriter(fileTemp, "UTF-8")) {
            //0: nome, 1: matricula, 2: telefone, 3: email, 4: uffmail, 5: status

            stream.map(e -> {
                        Aluno tempAluno = new Aluno(e);
                        if (tempAluno.checaMatricula(mat))
                            return tempAluno.atualizaDados().toString();
                        else
                            return e;})
                    .forEach(output::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}