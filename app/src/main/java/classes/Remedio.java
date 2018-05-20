package classes;

public class Remedio {
    private String nome,nomeDroga,efeito,viaDeAdministracao,info,efeitoColateral;
    private double dosagem;
    private String cod;
    public Remedio(String nome, double dosagem,String cod) {
        this.nome = nome;
        this.dosagem = dosagem;
        this.cod = cod;
    }

    public Remedio(String nome, String nomeDroga, String efeito, String viaDeAdministracao,String efeitoColateral, String info) {
        this.nome = nome;
        this.nomeDroga = nomeDroga;
        this.efeito = efeito;
        this.viaDeAdministracao = viaDeAdministracao;
        this.info = info;
        this.efeitoColateral = efeitoColateral;
    }

    public String getEfeitoColateral() {
        return efeitoColateral;
    }

    public void setEfeitoColateral(String efeitoColateral) {
        this.efeitoColateral = efeitoColateral;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getDosagem() {

        return dosagem;
    }

    public void setDosagem(double dosagem) {
        this.dosagem = dosagem;
    }

    public String getNomeDroga() {
        return nomeDroga;
    }

    public void setNomeDroga(String nomeDroga) {
        this.nomeDroga = nomeDroga;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public String getViaDeAdministracao() {
        return viaDeAdministracao;
    }

    public void setViaDeAdministracao(String viaDeAdministracao) {
        this.viaDeAdministracao = viaDeAdministracao;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return nome;
    }
}
