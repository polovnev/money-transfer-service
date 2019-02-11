package com.polovnev.model;

public class MoneyTransfer {

    private long id;
    private Account sourceAccount;
    private Account destinationAccount;
    private double sum;
    private MoneyTransferStatus moneyTransferStatus;

    public MoneyTransfer(long id, Account sourceAccount, Account destinationAccount, double sum) {
        this.id = id;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.sum = sum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public MoneyTransferStatus getMoneyTransferStatus() {
        return moneyTransferStatus;
    }

    public void setMoneyTransferStatus(MoneyTransferStatus moneyTransferStatus) {
        this.moneyTransferStatus = moneyTransferStatus;
    }
}
