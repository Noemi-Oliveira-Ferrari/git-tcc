package br.net.daumhelp.model;

import java.util.List;

public class Plano {
    private Integer amount;
    private String days;
    private String name;
    private Integer trial_days;
    private List<String> payment_methods;
    private String color;
    private String charges;
    private String installments;
    private Integer invoice_reminder;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTrial_days() {
        return trial_days;
    }

    public void setTrial_days(Integer trial_days) {
        this.trial_days = trial_days;
    }

    public List<String> getPayment_methods() {
        return payment_methods;
    }

    public void setPayment_methods(List<String> payment_methods) {
        this.payment_methods = payment_methods;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
    }

    public Integer getInvoice_reminder() {
        return invoice_reminder;
    }

    public void setInvoice_reminder(Integer invoice_reminder) {
        this.invoice_reminder = invoice_reminder;
    }

    @Override
    public String toString() {
        return "Plano{" +
                "amount=" + amount +
                ", days='" + days + '\'' +
                ", name='" + name + '\'' +
                ", trial_days=" + trial_days +
                ", payment_methods=" + payment_methods +
                ", color='" + color + '\'' +
                ", charges='" + charges + '\'' +
                ", installments='" + installments + '\'' +
                ", invoice_reminder=" + invoice_reminder +
                '}';
    }
}
