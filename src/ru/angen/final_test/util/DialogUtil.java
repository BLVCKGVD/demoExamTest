package ru.angen.final_test.util;

import javax.swing.*;
import java.awt.*;

public class DialogUtil {
    public static void showError(Component parentComponent,String text)
    {
        JOptionPane.showMessageDialog(null,text,"Ошибка", JOptionPane.ERROR_MESSAGE);
    }
    public static void showError(String text)
    {
        showError(null,text);
    }
    public static void showInfo(Component parentComponent,String text)
    {
        JOptionPane.showMessageDialog(null,text,"Информация", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void showInfo(String text)
    {
        showError(null,text);
    }
}
