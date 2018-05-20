package classes;

import java.io.Serializable;

import classes.Medico;

public class Paciente implements Serializable{
    private Pessoa medico;
    private String nome;
    private String sobrenome;

    public Paciente(Pessoa   medico, String nome, String sobrenome) {
        this.medico = medico;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }


    public String getMedico() {
        return medico.getUsuario();
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
}
