package ru.angen.final_test.ui;

import ru.angen.final_test.entity.ServiceEntity;
import ru.angen.final_test.manager.ServiceManager;
import ru.angen.final_test.util.BaseForm;
import ru.angen.final_test.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class EditServiceForm extends BaseForm {
    private JPanel panel1;
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField photoField;
    private JSpinner costSpinner;
    private JSpinner durationSpinner;
    private JSpinner disountSpinner;
    private JSpinner idField;
    private JButton saveButton;
    private JButton backButton;
    private JButton deleteButton;
    ServiceEntity serviceEntity;

    public EditServiceForm(ServiceEntity serviceEntity){

        super(800,600);
        this.serviceEntity = serviceEntity;
        setContentPane(mainPanel);
        initFields();
        initButtons();
        setVisible(true);

    }
    public void initFields()
    {
        idField.setValue(serviceEntity.getId());
        titleField.setText(serviceEntity.getTitle());
        photoField.setText(serviceEntity.getImagePath());
        costSpinner.setValue(serviceEntity.getCost());
        durationSpinner.setValue(serviceEntity.getDuration());
        disountSpinner.setValue(serviceEntity.getDiscount());
    }
    public void initButtons()
    {
        int id = (Integer) idField.getValue();
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

            ServiceEntity serviceEntity = new ServiceEntity(id,title,photo,cost,duration,discount);

            try {
                ServiceManager.edit(serviceEntity);
                DialogUtil.showInfo(this,"Успешно сохранено");
                dispose();
                new ServiceTableForm();
            } catch (SQLException ex) {
                DialogUtil.showError(this,"Сохранение ОШИБКА"+id);
            }

        });
        deleteButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,"Вы дейстительно хотите удалить эту услугу?"+
                    serviceEntity.getId(),"Удаление",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            {
                try {
                    ServiceManager.delete(serviceEntity);
                    DialogUtil.showInfo(this,"Услуга успешно удалена");
                    dispose();
                    new ServiceTableForm();
                } catch (SQLException ex) {
                    DialogUtil.showError(this,"Ошибка удаления");
                }
            }
        });
        backButton.addActionListener(e ->
        {
            if (JOptionPane.showConfirmDialog(this,"Вы действительно хотите выйти из режима редактирования?",
                    "Переход назад", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            {
                dispose();
                new ServiceTableForm();
            }
        });

    }

}
