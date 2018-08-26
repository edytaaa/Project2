package com.task.project2desktop;

import java.sql.*;
import java.util.ArrayList;

public class ExpenseDao{
        private static final String URL = "jdbc:mysql://localhost:3306/home";
        private static final String USER = "abcde";
        private static final String PASS = "abcde";
        private Connection connection;

        public ExpenseDao(){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (ClassNotFoundException e){
                System.out.println("No driver found");
            } catch (SQLException e){
                System.out.println("Could not establish connection");
            }
        }


        public void close(){
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }


    public void save(Expense expense){
        final String sql = "insert into home_budget(type, description, amount, date) values(?, ?, ?, ?)";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, expense.getType());
            prepStmt.setString(2, expense.getDescription());
            prepStmt.setDouble(3, expense.getAmount());
            prepStmt.setDate(4, expense.getDate());
            prepStmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Could not save record");
            e.printStackTrace();
        }
    }


    public ArrayList<Expense> read(boolean positive){
        String sql;
        if(positive){
            sql = "select * from home_budget where type='przychód'";
        }else{
            sql = "select * from home_budget where type='wydatek'";
        }
        try{
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            return readAll(prepStmt);
        }catch (SQLException e){
            System.out.println("Could not get data");
            return new ArrayList<>();
        }
    }


    public ArrayList<Expense> readBetweenDates(Date date1, Date date2){
        String sql = "select * from home_budget where date >= ? and date <= ?";
        try{
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setDate(1, date1 );
            prepStmt.setDate(2, date2 );
            return readAll(prepStmt);
        }catch (SQLException e){
            System.out.println("Could not get data");
            return new ArrayList<>();
        }
    }


    public ArrayList<Expense> readAboveAmount(double amount){
        String sql = "select * from home_budget where amount > ?";
        try{
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setDouble(1, amount );
            return readAll(prepStmt);
        }catch (SQLException e){
            System.out.println("Could not get data");
            return new ArrayList<>();
        }
    }


    public ArrayList<Expense> readAll(PreparedStatement prepStmt){
        ArrayList<Expense> expenses = new ArrayList<>();
        try{
            ResultSet result = prepStmt.executeQuery();
            while (result.next()){
                Expense expense = new Expense();
                expense.setId(result.getInt("id"));
                expense.setType(result.getString("type"));
                expense.setDescription(result.getString("description"));
                expense.setAmount(result.getDouble("amount"));
                expense.setDate(result.getDate("date"));
                expenses.add(expense);
            }
        }catch (SQLException e){
            System.out.println("Could not get data");
        }
        return expenses;
    }
}
