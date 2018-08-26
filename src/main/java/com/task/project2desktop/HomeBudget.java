package com.task.project2desktop;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeBudget{
    Scanner scanner = new Scanner(System.in);
    ExpenseDao expenseDao = new ExpenseDao();


    public static void main(String[] args){
        HomeBudget homeBudget = new HomeBudget();
        homeBudget.showOptions();
    }


    void showOptions(){
        System.out.println("1 - dodaj");
        System.out.println("2 - wczytaj przychody");
        System.out.println("3 - wczytaj wydatki");
        System.out.println("4 - wczytaj wydatki/przychody z okresu");
        System.out.println("5 - wczytaj wydatki/przychody powyżej kwoty");
        System.out.println("Wybierz opcję: 1, 2, 3, 4 lub 5:");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1: save();
            break;
            case 2: read(true);
            break;
            case 3: read(false);
            break;
            case 4: readBetweenDates();
            break;
            case 5: readAboveAmount();
            break;
        }
        expenseDao.close();
    }


    void save(){
        Expense expense = new Expense();
        readExpanseData(expense);
        expenseDao.save(expense);
    }


    void read(boolean positive){
        ArrayList<Expense> expenses = expenseDao.read(positive);
        for(Expense e : expenses)
            System.out.println(e);
    }


    void readBetweenDates(){
        System.out.println("Podaj pierwszą datę");
        Date date1 = readDate();
        System.out.println("Podaj drugą datę");
        Date date2 = readDate();
        ArrayList<Expense> expenses = expenseDao.readBetweenDates(date1, date2);
        for(Expense e : expenses)
            System.out.println(e);
    }


    void readAboveAmount(){
        System.out.println("Podaj kwotę");
        int amount = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Expense> expenses = expenseDao.readAboveAmount(amount);
        for(Expense e : expenses)
            System.out.println(e);
    }


    void readExpanseData(Expense expense) {
        System.out.println("Podaj typ wpisu (tj. wydatek lub przychód)");
        expense.setType(scanner.nextLine());
        System.out.println("Podaj opis transakcji");
        expense.setDescription(scanner.nextLine());
        System.out.println("Podaj kwotę transakcji");
        expense.setAmount(scanner.nextInt());
        scanner.nextLine();
        Date date = readDate();
        expense.setDate(date);
    }


    Date readDate(){
        System.out.println("Podaj rok transakcji");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj miesiąc transakcji");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj dzień transakcji");
        int day = scanner.nextInt();
        scanner.nextLine();
        return new Date(year-1900, month-1, day);
    }
}
