package ru.angen.final_test;

import ru.angen.final_test.ui.ServiceTableForm;
import ru.angen.final_test.util.BaseForm;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new ServiceTableForm();
    }
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1/service", "root", "");
    }

}
