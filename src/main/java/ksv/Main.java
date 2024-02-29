package ksv;

import ksv.acc.Account;
import ksv.acc.CreditAccount;
import ksv.acc.DebitAccount;

import java.util.Random;
import java.util.Scanner;

/**
 * 1. Создать программу управления банковским счетом (Account).
 * <p>
 * Программа должна позволять пользователю вводить начальный баланс счета, сумму депозита и сумму снятия средств.
 * При этом она должна обрабатывать следующие исключительные ситуации:
 * <p>
 * Попытка создать счет с отрицательным начальным балансом должна вызывать исключение IllegalArgumentException с соответствующим сообщением.
 * Попытка внести депозит с отрицательной суммой должна вызывать исключение IllegalArgumentException с соответствующим сообщением.
 * Попытка снять средства, сумма которых превышает текущий баланс,
 * должна вызывать исключение InsufficientFundsException с сообщением о недостаточных средствах и текущим балансом.
 * <p>
 * Продемонстрируйте работу вашего приложения:
 * Программа должна обрабатывать все исключения с помощью конструкции try-catch, выводя соответствующие сообщения об ошибках.
 * <p>
 * 2*.
 * Создать несколько типов счетов, унаследованных от Account, например: CreditAcciunt, DebitAccount.
 * Создать класс (Transaction), позволяющий проводить транзакции между счетами (переводить сумму с одного счета на другой)
 * <p>
 * Класс Transaction должен возбуждать исключение в случае неудачной попытки перевести деньги с одного счета на другой.
 * <p>
 * Продемонстрируйте работу вашего приложения:
 * Программа должна обрабатывать все исключения с помощью конструкции try-catch, выводя соответствующие сообщения об ошибках.
 */
public class Main {
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static Bank bank = new Bank();
    static Account currentAccount;

    public static void main(String[] args) {
        System.out.println("Hello world!");

        while (true) {

            System.out.println("""
                    1. Создать счет
                    2. Пополнить счет
                    3. Снять средства
                    4. Перевести средства
                    5. Выход
                    """);
            int i = 0;
            try {
                i = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            switch (i) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transfer();
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static void transfer() {
        while (true) {
            System.out.println("Выберите получателя");
            System.out.println("Счета для перевода");
            bank.viewRecipients();

            int id = 0;
            try {
                System.out.println("Введите id счета для перевода");
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            double amount = 0;
            try {
                System.out.println("Введите сумму перевода");
                amount = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            try {
                bank.transfer(amount, currentAccount, bank.getAccount(id));
                return;
            } catch (InsufficientFundsException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void withdraw() {
        double amount = 0;
        try {
            System.out.println("Введите сумму снятия");
            amount = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            bank.withdraw(currentAccount.getId(), amount);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deposit() {
        try {
            System.out.println("Введите сумму депозита");
            double amount = scanner.nextDouble();
            bank.deposit(currentAccount.getId(), amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createAccount() {
        boolean flag = true;
        String name = "Гость";

        while (flag) {
            System.out.println("Введите имя");
            name = scanner.nextLine();
            if (name.length() < 3) {
                System.out.println("Имя должно содержать не менее 3 символов");
                continue;
            }
            if (name.length() > 20) {
                System.out.println("Имя должно содержать не более 20 символов");
                continue;
            }
            if (!name.matches("[a-zA-Zа-яА-Я]+")) {
                System.out.println("Имя должно содержать только буквы");
                continue;
            }
            flag = false;
        }
        flag = true;
        while (flag) {
            int type = 0;
            try {
                System.out.println("""
                        Выберите тип счета
                        1. Кредитный
                        2. Дебетный
                        """);
                type = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            Account account;
            if (type == 1) {
                account = new CreditAccount(name);
                bank.addAccount(account);
                currentAccount = account;
                flag = false;
            } else if (type == 2) {
                double balance = 0;
                while (flag) {
                    try {
                        System.out.println("Введите начальный баланс");
                        balance = scanner.nextDouble();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    try {
                        account = new DebitAccount(name, balance);
                        currentAccount = account;
                        bank.addAccount(account);
                        flag = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

            } else {
                throw new IllegalArgumentException("Такого типа счета не существует");
            }
        }


        flag = true;

    }

}