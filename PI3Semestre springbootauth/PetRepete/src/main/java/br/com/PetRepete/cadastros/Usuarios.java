package br.com.PetRepete.cadastros;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;
import br.com.PetRepete.conexao.Conexao;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.PreparedStatement;

public class Usuarios {

    private String nome;
    private String senha;
    private boolean senhaConfirmada;
    private String numeroTelefone;
    private String email;
    private String cep, rua, numeroEndereco, complemento;

    public boolean isSenhaConfirmada() {
        return senhaConfirmada;
    }
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


    public String getSenha() {
        return senha;
    }
    public void confirmarSenha() {
        this.senhaConfirmada = true;
    }
    public void setSenha(String senha) {
        this.senha = senha;
        this.senhaConfirmada = false;
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

        do {
            System.out.print("Senha: ");
            usuario.setSenha(sc.nextLine());

            System.out.print("Confirme sua senha: ");
            String senha2 = sc.nextLine();

            if (!senha2.equals(usuario.getSenha())) {
                System.out.println("As senhas não coincidem, tente novamente");
            } else {
                usuario.confirmarSenha();
            }

        } while (!usuario.isSenhaConfirmada());

        System.out.println("Senha confirmada com sucesso!");


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

    public static void inserirUsuarioBanco(Usuarios usuario) throws SQLException, NoSuchAlgorithmException {
        String sql = "INSERT INTO Usuarios (nome, numeroTelefone, senha, email, cep, rua, numeroEndereco, complemento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = Conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getNumeroTelefone());
            preparedStatement.setString(3, encriptarSenha(usuario.getSenha()));
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getCep());
            preparedStatement.setString(6, usuario.getRua());
            preparedStatement.setString(7, usuario.getNumeroEndereco());
            preparedStatement.setString(8, usuario.getComplemento());

            preparedStatement.executeUpdate();
        }
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

    public static Usuarios buscarUsuarioPorEmailESenha(String email, String senha) throws SQLException, NoSuchAlgorithmException {
        String sql = "SELECT * FROM Usuarios WHERE email = ? AND senha = ?";

        try (PreparedStatement preparedStatement = Conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, encriptarSenha(senha));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Usuarios usuario = new Usuarios();
                    usuario.setNome(resultSet.getString("nome"));
                    usuario.setNumeroTelefone(resultSet.getString("numeroTelefone"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setCep(resultSet.getString("cep"));
                    usuario.setRua(resultSet.getString("rua"));
                    usuario.setNumeroEndereco(resultSet.getString("numeroEndereco"));
                    usuario.setComplemento(resultSet.getString("complemento"));
                    return usuario;
                }
            }
        }

        return null;
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
