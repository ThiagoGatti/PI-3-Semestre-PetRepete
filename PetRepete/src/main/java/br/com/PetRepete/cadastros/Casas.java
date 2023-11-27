package br.com.PetRepete.cadastros;

import java.util.Scanner;

public class Casas {

    private int id_casa;
    private String nome;
    private String email;
    private String numeroTelefone;
    private String endereco, cep, rua, numeroEndereco, complemento;

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

    public int getId_casa() {
        return id_casa;
    }

    public void setId_casa(int id_casa) {
        this.id_casa = id_casa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

        System.out.print("Endereço: ");
        casa.setEndereco(sc.nextLine());

        System.out.print("CEP: ");
        casa.setCep(sc.nextLine());

        System.out.print("Rua: ");
        casa.setRua(sc.nextLine());

        System.out.print("Número de Endereço: ");
        casa.setNumeroEndereco(sc.nextLine());

        System.out.print("Complemento: ");
        casa.setComplemento(sc.nextLine());

        System.out.println("Cadastro Realizado");
    }

    @Override
    public String toString() {
        return "Casas{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numeroEndereco='" + numeroEndereco + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
