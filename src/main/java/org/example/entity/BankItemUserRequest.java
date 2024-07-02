package org.example.entity;

public class BankItemUserRequest
{
    private BankItem bankItem;
    private User user;
    public BankItem getBankItem() {
        return bankItem;
    }

    public void setBankItem(BankItem bankItem) {
        this.bankItem = bankItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
