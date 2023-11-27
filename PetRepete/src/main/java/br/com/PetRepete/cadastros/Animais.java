package br.com.PetRepete.cadastros;

import java.util.Scanner;

public class Animais {

    private int idade;
    private int id_Animal;
    private String nome, raça;

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getId_Animal() {
        return id_Animal;
    }

    public void setId_Animal(int id_Animal) {
        this.id_Animal = id_Animal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaça() {
        return raça;
    }

    public void setRaça(String raça) {
        this.raça = raça;
    }

    public void preencherAnimal(Animais animal, Scanner sc) {
        System.out.print("Nome: ");
        animal.setNome(sc.nextLine());

        System.out.print("Idade: ");
        try {
            animal.setIdade(Integer.parseInt(sc.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Idade inválida. Por favor, digite um número válido.");
            return;
        }

        System.out.print("Raça: ");
        animal.setRaça(sc.nextLine());

        System.out.println("Cadastro Realizado");
    }

    @Override
    public String toString() {
        return "Animais{" +
                "idade=" + idade +
                ", nome='" + nome + '\'' +
                ", raça='" + raça + '\'' +
                '}';
    }
}

