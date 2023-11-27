package br.com.PetRepete.cadastros;

import java.sql.SQLException;
import java.util.Scanner;
import br.com.PetRepete.conexao.Conexao;
import java.sql.PreparedStatement;

public class Usuarios {

    private String nome;
    private String numeroTelefone;
    private String email;
    private String cep, rua, numeroEndereco, complemento;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public void preencherUsuario(Usuarios usuario, Scanner sc) {
        System.out.print("Nome: ");
        usuario.setNome(sc.nextLine());

        System.out.print("Número de Telefone: ");
        usuario.setNumeroTelefone(sc.nextLine());

        System.out.print("Email: ");
        usuario.setEmail(sc.nextLine());

        System.out.print("CEP: ");
        usuario.setCep(sc.nextLine());

        System.out.print("Rua: ");
        usuario.setRua(sc.nextLine());

        System.out.print("Número de Endereço: ");
        usuario.setNumeroEndereco(sc.nextLine());

        System.out.print("Complemento (pressione Enter se não aplicável): ");
        String complemento = sc.nextLine();

        if (!complemento.isEmpty()) {
            usuario.setComplemento(complemento);
        } else {
            usuario.setComplemento(null);
        }
        System.out.println("Cadastro Realizado");
    }

    public static void inserirUsuarioBanco(Usuarios usuario) throws SQLException {
        String sql = "INSERT INTO Usuarios (nome, numeroTelefone, email, cep, rua, numeroEndereco, complemento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = Conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getNumeroTelefone());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getCep());
            preparedStatement.setString(5, usuario.getRua());
            preparedStatement.setString(6, usuario.getNumeroEndereco());
            preparedStatement.setString(7, usuario.getComplemento());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "nome='" + nome + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numeroEndereco='" + numeroEndereco + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
