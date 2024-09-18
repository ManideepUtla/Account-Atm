package com.example.fullstackdemo310824.service;

import com.example.fullstackdemo310824.exception.AccountcreationFailedException;
import com.example.fullstackdemo310824.hibernate.HibernateUtils;
import com.example.fullstackdemo310824.model.*;
import com.example.fullstackdemo310824.model.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class AccountService {

    public Account searchAccountByJpa(String accountNumber){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaDemo");
        EntityManager entityManager=emf.createEntityManager();
        entityManager.getTransaction().begin();
        jakarta.persistence.Query query= entityManager.createQuery("select a from AccountEntity a where a.accountNumber=:inputAccountNumber ");
        query.setParameter("inputAccountNumber",accountNumber);

        List<AccountEntity> accountEntities = query.getResultList();
        AccountEntity accountEntity=accountEntities.get(0);
        Account account= Account.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .mobileNumber(accountEntity.getMobileNumber())
                .pan(accountEntity.getPan())
                .balance(accountEntity.getBalance())
                .name(accountEntity.getName())
                .build();
        List<AccountAddressEntity> accountAddressEntityList=
                accountEntity.getAccountAddressEntityList();

        if (Objects.nonNull(accountAddressEntityList)&& accountAddressEntityList.size()>0){

            AccountAddressEntity accountAddressEntity=accountAddressEntityList.get(0);
            System.out.println("AccountAddressEntity is Loaded");
            Address address=new Address();
            address.setAdd1(accountAddressEntity.getAddress1());
            address.setAdd2(accountAddressEntity.getAddress2());
            address.setPincode(accountAddressEntity.getPincode());
            address.setCity(accountAddressEntity.getCity());
            address.setState(accountAddressEntity.getState());
            account.setAddress(address);


        }
        entityManager.getTransaction().commit();
        return account;

    }

    public Account searchAccount(String accountNumber) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query<AccountEntity> query = session.createQuery("From AccountEntity a where a.accountNumber=:inputAccountNumber");
        query.setParameter("inputAccountNumber", accountNumber);
        AccountEntity accountEntity = query.list().get(0);

        System.out.println("Account is Loaded");
        Account account = Account.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .name(accountEntity.getName())
                .mobileNumber(accountEntity.getMobileNumber())
                .balance(accountEntity.getBalance())
                .pan(accountEntity.getPan())
                /*.address(
                        Address.builder()
                                .add1(accountEntity.getAccountAddressEntityList().get(0).getAddress1())
                                .add2(accountEntity.getAccountAddressEntityList().get(0).getAddress2())
                                .pincode(accountEntity.getAccountAddressEntityList().get(0).getPincode())
                                .city(accountEntity.getAccountAddressEntityList().get(0).getCity())
                                .state(accountEntity.getAccountAddressEntityList().get(0).getState()).build()

                        )*/
                .build();



        List<AccountAddressEntity> accountAddressEntityList=
                accountEntity.getAccountAddressEntityList();
        AccountAddressEntity accountAddressEntity=accountAddressEntityList.get(0);
        System.out.println("AccountAddressEntity is Loaded");
        Address address=new Address();
        address.setAdd1(accountAddressEntity.getAddress1());
        address.setAdd2(accountAddressEntity.getAddress2());
        address.setPincode(accountAddressEntity.getPincode());
        address.setCity(accountAddressEntity.getCity());
        address.setState(accountAddressEntity.getState());
        account.setAddress(address);

        System.out.println("Address is Loaded");




        return account;

    }

    public String OneToManyUsingHibernateFromUI(Account account) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNumber(account.getMobileNumber());

        List<AccountAddressEntity> addressEntityList = new ArrayList<>();
        AccountAddressEntity accountAddressEntity = new AccountAddressEntity();

        accountAddressEntity.setAddress1(account.getAddress().getAdd1());
        accountAddressEntity.setAddress2(account.getAddress().getAdd2());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setStatus(1);
        accountAddressEntity.setAccountEntity(accountEntity);
        addressEntityList.add(accountAddressEntity);
        accountEntity.setAccountAddressEntityList(addressEntityList);

        session.persist(accountEntity);
        transaction.commit();
        return accountEntity.getAccountNumber();

    }


    public String oneTOManyUsingHibernate(Account account) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AadharEntity aadharEntity = new AadharEntity();
        aadharEntity.setName(account.getName());

        List<AddressEntity> addressEntityList = new ArrayList<>();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setState(account.getMobileNumber());
        addressEntity.setMyMappedByTestEntity(aadharEntity);
        addressEntityList.add(addressEntity);
        aadharEntity.setAddressEntityList(addressEntityList);


        session.persist(aadharEntity);
        transaction.commit();
        return String.valueOf((aadharEntity.aadharNumber));

    }


    public String createAccountUsingHibernate(Account account) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNumber(account.getMobileNumber());

        session.persist(accountEntity);
        transaction.commit();
        return accountEntity.getAccountNumber();

    }


    @Autowired
    private DBConnection dbConnection;

    public String createAccount(Account account) throws Exception {
        String accountNumber = null;
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();//statement will excute the queries

            accountNumber = UUID.randomUUID().toString();
            String query = "insert into bank.account values(" + "'" + accountNumber + "'" + ","
                    + "'" + account.getName() + "'"
                    + ","
                    + "'" + account.getPan() + "'"
                    + ","
                    + "'" + account.getMobileNumber() + "'"
                    + ","
                    + account.getBalance() + ")"; // this query creating 4 obj (single quote, comma, paranthesis,insert into bank.account values)

            int status = stmt.executeUpdate(query);

            if (status == 1) {
                System.out.println("Account is created" + accountNumber);

            } else {
                //it will throw new exception
                throw new AccountcreationFailedException("Account Creation is failed " + account.getPan());

            }
        } catch (Exception ex) {
            System.out.println("Exception Occured " + ex);
            throw ex;//rethrowing the existing exception using throws.
            // throw-> it will throw the exception and it will create new exception and it will rethrow the exception even checked or unchecked or custom exceptions
            //throws-> to handle the re-throw the Exception.
        }
        return accountNumber;
    }

   public Account withdraw(WithdrawalRequest withdrawalRequest) throws Exception {
        Connection connection1 = null;
        PreparedStatement selectStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        try {
             connection1 = DBConnection.getConnection();
            Statement stmt = connection1.createStatement();
            connection1.setAutoCommit(false); // Begin transaction

            // Step 1: Check the current balance
            String selectQuery = "SELECT balance FROM bank.account WHERE accountnumber = ?";
            selectStmt = connection1.prepareStatement(selectQuery);
            selectStmt.setString(1, withdrawalRequest.getAccountnumber());
            rs = selectStmt.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");

                // Step 2: Check if the balance is sufficient
                if (currentBalance < withdrawalRequest.getAmount()) {
                    throw new Exception("Insufficient balance");
                }

                // Step 3: Update the balance
                String updateQuery = "UPDATE bank.account SET balance = balance - ? WHERE accountnumber = ?";
                updateStmt = connection1.prepareStatement(updateQuery);
                updateStmt.setDouble(1, withdrawalRequest.getAmount());
                updateStmt.setString(2, withdrawalRequest.getAccountnumber());

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated == 1) {
                    connection1.commit(); // Commit transaction

                    // Step 4: Fetch the updated account details
                    String fetchQuery = "SELECT * FROM bank.account WHERE accountnumber = ?";
                    selectStmt = connection1.prepareStatement(fetchQuery);
                    selectStmt.setString(1, withdrawalRequest.getAccountnumber());
                    rs = selectStmt.executeQuery();

                    if (rs.next()) {
                        Account updatedAccount = Account.builder().build();
                        updatedAccount.setAccountNumber(rs.getString("accountnumber"));
                        updatedAccount.setName(rs.getString("name"));
                        updatedAccount.setPan(rs.getString("pan"));
                        updatedAccount.setMobileNumber(rs.getString("mobileNumber"));
                        updatedAccount.setBalance(rs.getDouble("balance"));

                        return updatedAccount;
                    } else {
                        throw new Exception("Account not found after update");
                    }
                } else {
                    throw new Exception("Withdrawal failed, no rows updated");
                }
            } else {
                throw new Exception("Account not found");
            }
        } catch (SQLException ex) {
            if (connection1 != null) {
                try {
                    connection1.rollback(); // Rollback transaction on failure
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throw new Exception("Database error: " + ex.getMessage());
        } finally {
            // Close resources in reverse order of their creation
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (selectStmt != null) try {
                selectStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (updateStmt != null) try {
                updateStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection1 != null) try {
                connection1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}