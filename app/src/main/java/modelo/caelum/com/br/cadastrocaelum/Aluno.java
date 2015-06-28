package modelo.caelum.com.br.cadastrocaelum;

import java.io.Serializable;

/**
 * Created by IT-CPS on 12/06/2015.
 */
public class Aluno implements Serializable{
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private Double nota;
    private String caminhoFoto;
    private String email;

    public Aluno(String nome, String telefone, String endereco, String site, Double nota, String caminhoFoto) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.site = site;
        this.nota = nota;
        this.caminhoFoto = caminhoFoto;
    }

    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }

    public String getEmail() {
        return email;
    }
}
