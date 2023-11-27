package br.com.PetRepete.cadastros;

import br.com.PetRepete.conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Casas {

    private String nome;
    private String email;
    private String numeroTelefone;
    private String cep, rua, numeroEndereco, complemento;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numeroEndereco;
    }

    public void setNumero(String numero) {
        this.numeroEndereco = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void preencherCasa(Casas casa, Scanner sc) {
        System.out.print("Nome: ");
        casa.setNome(sc.nextLine());

        System.out.print("Email: ");
        casa.setEmail(sc.nextLine());

        System.out.print("Número de Telefone: ");
        casa.setNumeroTelefone(sc.nextLine());

        System.out.print("CEP: ");
        casa.setCep(sc.nextLine());

        System.out.print("Rua: ");
        casa.setRua(sc.nextLine());

        System.out.print("Número de Endereço: ");
        casa.setNumeroEndereco(sc.nextLine());

        System.out.print("Complemento (pressione Enter se não aplicável): ");
        String complemento = sc.nextLine();

        if (!complemento.isEmpty()) {
            casa.setComplemento(complemento);
        } else {
            casa.setComplemento(null);
        }
        System.out.println("Cadastro Realizado");
    }

    public static void inserirCasasBanco(Casas casas) throws SQLException {
        String sql = "INSERT INTO Casas (nome, numeroTelefone, email, cep, rua, numeroEndereco, complemento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = Conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, casas.getNome());
            preparedStatement.setString(2, casas.getNumeroTelefone());
            preparedStatement.setString(3, casas.getEmail());
            preparedStatement.setString(4, casas.getCep());
            preparedStatement.setString(5, casas.getRua());
            preparedStatement.setString(6, casas.getNumeroEndereco());
            preparedStatement.setString(7, casas.getComplemento());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "Casas{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numeroEndereco='" + numeroEndereco + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
