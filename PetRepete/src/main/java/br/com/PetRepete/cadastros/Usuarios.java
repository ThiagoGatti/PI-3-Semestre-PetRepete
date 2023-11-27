package br.com.PetRepete.cadastros;

import java.util.Scanner;

public class Usuarios {

    private int id_Usuario;
    private String nome;
    private String numeroTelefone;
    private String email;
    private String endereco, cep, rua, numeroEndereco, complemento;

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep(String s) {
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

        System.out.print("Endereço: ");
        usuario.setEndereco(sc.nextLine());

        System.out.print("CEP: ");
        usuario.setCep(sc.nextLine());

        System.out.print("Rua: ");
        usuario.setRua(sc.nextLine());

        System.out.print("Número de Endereço: ");
        usuario.setNumeroEndereco(sc.nextLine());

        System.out.print("Complemento: ");
        usuario.setComplemento(sc.nextLine());

        System.out.println("Cadastro Realizado");
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "nome='" + nome + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numeroEndereco='" + numeroEndereco + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
