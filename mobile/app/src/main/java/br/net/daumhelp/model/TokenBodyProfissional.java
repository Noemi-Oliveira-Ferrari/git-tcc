package br.net.daumhelp.model;


public class TokenBodyProfissional {
    private Profissional profissional;
    private String sub;
    private Integer iat;
    private Integer exp;

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Integer getIat() {
        return iat;
    }

    public void setIat(Integer iat) {
        this.iat = iat;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "TokenBodyProfissional{" +
                "profissional=" + profissional +
                ", sub='" + sub + '\'' +
                ", iat=" + iat +
                ", exp=" + exp +
                '}';
    }
}
