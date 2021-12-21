package ru.angen.final_test.util;

import javax.swing.*;
import java.awt.*;

public class BaseForm extends JFrame {
    static String APP_TITLE = "Service App";
    public BaseForm(int width, int height)
    {

        setMinimumSize(new Dimension(
                width,height
        ));
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width/2 - width/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2 - height/2
        );
        setTitle(APP_TITLE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/school_logo.png"));

    }
}
