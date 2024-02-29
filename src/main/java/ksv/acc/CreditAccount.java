package ksv.acc;

public class CreditAccount extends Account{
    private static final double LIMIT = 100000;
    public CreditAccount(String name) {
        super(name, LIMIT);

    }

    @Override
    public void  deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Нельзя положить отрицательную сумму");
        }
        if (balance + amount > LIMIT) {
            throw new IllegalArgumentException("Превышен лимит кредитного счета");
        }
        super.deposit(amount);
    }

    @Override
    public String toString() {
        return  super.toString() + String.format("кредитный, лимит=%.2f] ", LIMIT);
    }
}
