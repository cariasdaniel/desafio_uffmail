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
            Scanner scan = new Scanner(System.in);
            System.out.println("Email não existe, vamos criar um novo :)");

            String[] r = new GeradorSugestao(nome).gerar(5);
            int alt;

            while(true){
                for (int i = 0; i < r.length; i++) {
                    System.out.println((i+1) + " - " + r[i]);
                }

                System.out.println("Das sugestões oferecidas, qual é a de sua preferência?");
                alt = scan.nextInt() - 1;

                if (alt < 0 || alt >= r.length)
                    System.out.println("Por favor, insira uma das " + r.length + " opções válidas acima.");
                else break;

            }

            System.out.println("A criação de seu e-mail (" + r[alt] + ") será feita nos próximos minutos.");
            System.out.println("Um SMS foi enviado para " + telefone + " com a sua senha de acesso.");
            uffmail = r[alt];
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

class GeradorSugestao {
    private int n_nomes;
    private String[] nomes;

    public GeradorSugestao(int n, String[] nomes){
        n_nomes = n;
        this.nomes = nomes;
    }

    public GeradorSugestao(String nomes){
        this.nomes = nomes.toLowerCase().split(" ");
        n_nomes = this.nomes.length;
    }

    public String[] gerar(int n){
        String[] r = new String[n];
        r[0] = nomes[0] + "_" + nomes[1] + "@id.uff.br";
        r[1] = nomes[0] + nomes[1].charAt(0) + nomes[nomes.length - 1].charAt(0) + "@id.uff.br";
        r[2] = nomes[0] + nomes[nomes.length - 1] + "@id.uff.br";
        r[3] = nomes[0] + "." + nomes[nomes.length - 1] + "@id.uff.br";
        r[4] = nomes[0].charAt(0) + nomes[1] + nomes[nomes.length - 1] + "@id.uff.br";
        return r;
    }
}