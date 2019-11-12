package br.net.daumhelp.model;

public class TokenBodyCliente {

    private Cliente cliente;
    private String sub;
    private Integer iat;
    private Integer exp;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        return "TokenBodyCliente{" +
                "cliente=" + cliente +
                ", sub='" + sub + '\'' +
                ", iat=" + iat +
                ", exp=" + exp +
                '}';
    }
}
