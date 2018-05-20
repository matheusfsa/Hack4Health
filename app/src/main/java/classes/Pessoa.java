package classes;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private String usuario;
    private String senha;
    private int funcao;

    public Pessoa(String usuario, String senha, int funcao) {
        this.usuario = usuario;
        this.senha = senha;
        this.funcao = funcao;
    }

    public Pessoa(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getFuncao() {
        return funcao;
    }

    public void setFuncao(int funcao) {
        this.funcao = funcao;
    }
}
