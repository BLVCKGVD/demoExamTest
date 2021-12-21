package ru.angen.final_test.ui;

import ru.angen.final_test.entity.ServiceEntity;
import ru.angen.final_test.manager.ServiceManager;
import ru.angen.final_test.ui.ServiceTableForm;
import ru.angen.final_test.util.BaseForm;
import ru.angen.final_test.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class CreateServiceForm extends BaseForm {
    private JPanel panel1;
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField photoField;
    private JSpinner costSpinner;
    private JSpinner durationSpinner;
    private JSpinner disountSpinner;
    private JButton saveButton;



    public CreateServiceForm(){

        super(800,600);

        setContentPane(mainPanel);

        initButtons();
        setVisible(true);

    }

    public void initButtons()
    {
        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            if (title.isEmpty() || title.length()>100)
            {
                DialogUtil.showError(this,"Ошибка в названии");
                return;
            }
            String photo = photoField.getText();
            if (photo.isEmpty() || photo.length()>1000)
            {
                DialogUtil.showError(this,"Ошибка в фото");
                return;
            }
            int cost = (Integer) costSpinner.getValue();
            if (cost<=0)
            {
                DialogUtil.showError(this,"Ошибка в цене");
                return;
            }
            int duration = (Integer) durationSpinner.getValue();
            if (duration<=0)
            {
                DialogUtil.showError(this,"Ошибка в длительности");
                return;
            }
            int discount = (Integer) disountSpinner.getValue();
            if (discount<0 || discount>=100)
            {
                DialogUtil.showError(this,"Ошибка в скидке");
                return;
            }

            ServiceEntity serviceEntity = new ServiceEntity(title,photo,cost,duration,discount);

            try {
                ServiceManager.insert(serviceEntity);
                DialogUtil.showInfo(this,"Успешно сохранено");
                dispose();
                new ServiceTableForm();
            } catch (SQLException ex) {
                DialogUtil.showError(this,"Сохранение ОШИБКА");
            }

        });



    }

}
