package ksv.acc;

public class DebitAccount extends Account{

    public DebitAccount(String name, double balance) {
        super(name, balance);
    }


    @Override
    public String toString() {
        return super.toString() + "Дебитный счет] " ;
    }
}
