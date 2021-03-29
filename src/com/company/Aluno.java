package com.company;

import java.util.Scanner;

class Aluno{
    String nome, matricula, telefone, email, uffmail, status;

    public Aluno(String S){//from csv
        String[] dados = S.split(",");
        this.nome = dados[0];
        this.matricula = dados[1];
        this.telefone = dados[2];
        this.email = dados[3];
        this.uffmail = dados[4];
        this.status = dados[5];
    }

    public Aluno(String [] dados){
        this.nome = dados[0];
        this.matricula = dados[1];
        this.telefone = dados[2];
        this.email = dados[3];
        this.uffmail = dados[4];
        this.status = dados[5];
    }

    private void procederCadastroUffmail(){
        if(status.equals("Inativo")){
            System.out.println("Aluno não está com a matrícula ativa");
        } else if (uffmail.isBlank()){
            System.out.println("Email não existe, vamos criar um novo :)");
            Scanner scan = new Scanner(System.in);
            uffmail = scan.nextLine();
        } else {
            System.out.println("Você já tem um email cadastrado no sistema");
        }
    }

    public Aluno atualizaDados(){
        this.procederCadastroUffmail();
        return this;
    }
    public boolean checaMatricula(String mat) {
        return matricula.equals(mat);
    }

    @Override
    public String toString(){
        return nome + "," + matricula + "," + telefone + "," + email + "," + uffmail + "," + status;
    }
}