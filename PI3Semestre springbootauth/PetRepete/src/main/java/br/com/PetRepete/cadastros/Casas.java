package br.com.PetRepete.cadastros;

import br.com.PetRepete.conexao.Conexao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Casas {

    private String nome;
    private boolean senhaConfirmada;
    private String senha;
    private String email;
    private String numeroTelefone;
    private String cep, rua, numeroEndereco, complemento;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void confirmarSenha() {
        this.senhaConfirmada = true;
    }

    public boolean isSenhaConfirmada() {
        return senhaConfirmada;
    }
    public void setSenha(String senha) {
        this.senha = senha;
        senhaConfirmada = false;
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

        do {
            System.out.print("Senha: ");
            casa.setSenha(sc.nextLine());

            System.out.print("Confirme sua senha: ");
            String senha2 = sc.nextLine();

            if (!senha2.equals(casa.getSenha())) {
                System.out.println("As senhas não coincidem, tente novamente");
            } else {
                casa.confirmarSenha();
            }

        } while (!casa.isSenhaConfirmada());

        System.out.println("Senha confirmada com sucesso!");
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
        String sql = "INSERT INTO Casas (nome, email, senha, numeroTelefone, cep, rua, numeroEndereco, complemento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = Conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, casas.getNome());
            preparedStatement.setString(4, casas.getNumeroTelefone());
            preparedStatement.setString(3, encriptarSenha(casas.getSenha()));
            preparedStatement.setString(2, casas.getEmail());
            preparedStatement.setString(5, casas.getCep());
            preparedStatement.setString(6, casas.getRua());
            preparedStatement.setString(7, casas.getNumeroEndereco());
            preparedStatement.setString(8, casas.getComplemento());

            preparedStatement.executeUpdate();
        }
    }

    public static Casas buscarCasaPorEmailESenha(String email, String senha) throws SQLException, NoSuchAlgorithmException {
        String sql = "SELECT * FROM Casas WHERE email = ? AND senha = ?";

        try (PreparedStatement preparedStatement = Conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, encriptarSenha(senha));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Casas casas = new Casas();
                    casas.setNome(resultSet.getString("nome"));
                    casas.setNumeroTelefone(resultSet.getString("numeroTelefone"));
                    casas.setEmail(resultSet.getString("email"));
                    casas.setCep(resultSet.getString("cep"));
                    casas.setRua(resultSet.getString("rua"));
                    casas.setNumeroEndereco(resultSet.getString("numeroEndereco"));
                    casas.setComplemento(resultSet.getString("complemento"));
                    return casas;
                }
            }
        }

        return null;
    }
    public static String encriptarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(senha.getBytes());
            byte[] digest = md.digest();
            return String.format("%064x", new BigInteger(1, digest));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo de hash não disponível", e);
        }
    }

    private static final MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean verificarSenha(String senhaFornecida, String senhaArmazenada) throws NoSuchAlgorithmException {
        String senhaFornecidaEncriptada = encriptarSenha(senhaFornecida);
        md.update(senhaFornecidaEncriptada.getBytes());
        byte[] digest = md.digest();
        String senhaArmazenadaEncriptada = new BigInteger(1, digest).toString(16);
        return senhaFornecidaEncriptada.equals(senhaArmazenadaEncriptada);
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
