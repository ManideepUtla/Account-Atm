package com.example.fullstackdemo310824.service;

import com.example.fullstackdemo310824.exception.AccountcreationFailedException;
import com.example.fullstackdemo310824.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.UUID;


public class AccountService {
    @Autowired
    private DBConnection dbConnection;

    public String createAccount(Account account) throws Exception{
        String accountNumber=null;
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();//statement will excute the queries

            accountNumber = UUID.randomUUID().toString();

            String query = "insert into bank.account values(" + "'" +accountNumber+ "'" + ","
                    + "'" + account.getName() + "'"
                    + ","
                    + "'" + account.getPan() + "'"
                    + ","
                    + "'" + account.getMobileNumber() + "'"
                    + ","
                    + account.getBalance() + ")"; // this query creating 4 obj (single quote, comma, paranthesis,insert into bank.account values)

            int status = stmt.executeUpdate(query);

            if (status==1) {
                System.out.println("Account is created" + accountNumber);

            } else {
                //it will throw new exception
                throw new AccountcreationFailedException("Account Creation is failed " + account.getPan());

            }
        } catch(Exception ex) {
            System.out.println("Exception Occured "+ex);
             throw ex;//rethrowing the existing exception using throws.
            // throw-> it will throw the exception and it will create new exception and it will rethrow the exception even checked or unchecked or custom exceptions
            //throws-> to handle the re-throw the Exception.
        }
        return accountNumber;
    }

    public Account withdraw(String accountnumber, double amount) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnection();
            connection.setAutoCommit(false);


            String selectQuery = "SELECT balance FROM bank.account WHERE accountnumber = ?";
            stmt = connection.prepareStatement(selectQuery);
            stmt.setString(1, accountnumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");

                if (currentBalance < amount) {
                    throw new Exception("Insufficient balance");
                }


                String updateQuery = "UPDATE bank.account SET balance = balance - ? WHERE accountnumber = ?";
                stmt = connection.prepareStatement(updateQuery);
                stmt.setDouble(1, amount);
                stmt.setString(2, accountnumber);
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated == 1) {
                    connection.commit();


                    String fetchQuery = "SELECT * FROM bank.account WHERE accountnumber = ?";
                    stmt = connection.prepareStatement(fetchQuery);
                    stmt.setString(1, accountnumber);
                    rs = stmt.executeQuery();

                    if (rs.next()) {
                        Account updatedAccount = new Account();
                        updatedAccount.setAccountNumber(rs.getString("accountnumber"));
                        updatedAccount.setName(rs.getString("name"));
                        updatedAccount.setPan(rs.getString("pan"));
                        updatedAccount.setMobileNumber(rs.getString("mobileNumber"));
                        updatedAccount.setBalance(rs.getDouble("balance"));

                        return updatedAccount;
                    } else {
                        throw new Exception("Account not found");
                    }
                } else {
                    throw new Exception("Withdrawal failed");
                }
            } else {
                throw new Exception("Account not found");
            }
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throw new Exception("Database error: " + ex.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (connection != null) try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

}
