/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.schule.bank.junit5;


import ch.schule.Bank;

/**
 * @author       Luigi Cavuoti
 * @uml.dependency  supplier="ch.schule.m326.bank.Bank"
 */
public class TestBank {

    public static void main(String[] args)
    {
        Bank ubs = new Bank();
        ubs.createSavingsAccount();
        ubs.deposit("S-1000", 13576, 12000);
        long balance = ubs.getBalance("S-1000");
        System.out.println("Saldo des Kontos S-1000 ist: " + balance +" mmRp." );
        ubs.createSavingsAccount();
        ubs.deposit("S-1001", 13560, 10000);
        System.out.println("Saldo des Kontos S-1001 ist: " + ubs.getBalance("S-1001")+" mmRp." );
        ubs.createSavingsAccount();
        ubs.deposit("S-1002",13576, 8000);
        ubs.deposit("S-1002", 13576, 200);
        System.out.println("Saldo des Kontos S-1002 ist: " + ubs.getBalance("S-1002")+" mmRp." );
        System.out.println("Konti absteigend nach Konstosaldo sortiert");
        ubs.createPromoYouthSavingsAccount();
        System.out.println("saldo de Kontos Y-1003: " + ubs.getBalance("Y-1003")+" mmRp.");
        ubs.deposit("Y-1003", 14000, 50000);
        ubs.printTop5() ;
        System.out.println("Konti aufsteigend nach Konstosaldo sortiert");
        ubs.printBottom5();

        Bank migros = new Bank();
        migros.createPromoYouthSavingsAccount();
        migros.deposit("Y-1000",13456, 24000);
        System.out.println("Saldo des Kontos Y-1000 ist: " + migros.getBalance("Y-1000")+" mmRp." );
        migros.printTop5();
        migros.printBottom5();
    }

}
