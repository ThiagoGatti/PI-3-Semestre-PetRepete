package br.com.PetRepete.cadastros;

import br.com.PetRepete.conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Animais {

    private String idade;
    private int id_Animal;
    private int id_Casa;
    private String nome, raça, mensagem, especie;


    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getId_Casa() {
        return id_Casa;
    }

    public void setId_Casa(int id_Casa) {
        this.id_Casa = id_Casa;
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
        System.out.println("ID da Casa Responsavel: ");
        try {
            animal.setId_Casa(Integer.parseInt(sc.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, digite um número válido.");
            return;
        }
        System.out.print("Nome: ");
        animal.setNome(sc.nextLine());

        System.out.print("Idade: ");
        animal.setIdade(sc.nextLine());
        System.out.print("Espécie: ");
        animal.setEspecie(sc.nextLine());
        System.out.print("Raça: ");
        animal.setRaça(sc.nextLine());
        System.out.println("Mensagem (Caso não queria adicionar mensagem, deixe em branco): ");
        animal.setMensagem(sc.nextLine());
        if (!mensagem.isEmpty()) {
            animal.setMensagem(mensagem);
        } else {
            animal.setMensagem(null);
        }
        System.out.println("Cadastro Realizado");
    }


    public static void inserirAnimalBanco(Animais animal) throws SQLException {
        String sql = "INSERT INTO Animais (nome, idade, especie, raça, id_casa, mensagem) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = Conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, animal.getNome());
            preparedStatement.setString(2, animal.getIdade());
            preparedStatement.setString(3, animal.getEspecie());
            preparedStatement.setInt(5, animal.getId_Casa());
            preparedStatement.setString(4, animal.getRaça());
            preparedStatement.setString(6, animal.getMensagem());

            preparedStatement.executeUpdate();
        }

    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Animais{" +
                "idade='" + idade + '\'' +
                ", id_Animal=" + id_Animal +
                ", id_Casa=" + id_Casa +
                ", nome='" + nome + '\'' +
                ", raça='" + raça + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", especie='" + especie + '\'' +
                '}';
    }
}


