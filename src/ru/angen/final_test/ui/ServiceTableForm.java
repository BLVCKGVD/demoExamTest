package ru.angen.final_test.ui;

import ru.angen.final_test.entity.ServiceEntity;
import ru.angen.final_test.manager.ServiceManager;
import ru.angen.final_test.util.BaseForm;
import ru.angen.final_test.util.CustomTableModel;
import ru.angen.final_test.util.DialogUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;

public class ServiceTableForm extends BaseForm {
    private JPanel panel1;
    private JPanel mainPanel;
    private JTable table;
    private JButton createButton;
    private JButton SortIdButton;
    private JButton SortTitleButton;
    private JButton helpButton;
    private JButton reportButton;

    CustomTableModel<ServiceEntity> model;

    private boolean sortId =false;
    private boolean sortTitle = false;

    public ServiceTableForm()
    {
        super(800,600);
        setContentPane(mainPanel);
        initTables();
        initButtons();
        setVisible(true);

    }

    public void initTables()
    {
        table.getTableHeader().setReorderingAllowed(false);
        try {
            model = new CustomTableModel<>(ServiceEntity.class,
                    new String[]{"#","Наименование услуги","Фото","Цена","Длительность","Скидка в %"},
                    ServiceManager.select());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        table.setModel(model);
    }

    public void initButtons()
    {
        createButton.addActionListener(e -> {
            dispose();
            new CreateServiceForm();
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2)
                {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row!=-1)
                    {
                        dispose();
                        new EditServiceForm(model.getRows().get(row));
                    }
                }
            }
        });

        SortIdButton.addActionListener(e ->
        {
            Collections.sort(model.getRows(), new Comparator<ServiceEntity>() {
                @Override
                public int compare(ServiceEntity o1, ServiceEntity o2) {
                    Integer id1 = o1.getId();
                    Integer id2 = o2.getId();
                    if (sortId) {
                        return id1.compareTo(id2);
                    } else {
                        return id2.compareTo(id1);
                    }
                }

            });
            sortId = !sortId;
            sortTitle = false;
            model.fireTableDataChanged();

        });

        SortTitleButton.addActionListener(e ->
        {
            Collections.sort(model.getRows(), new Comparator<ServiceEntity>() {
                @Override
                public int compare(ServiceEntity o1, ServiceEntity o2) {

                    if (sortTitle) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    } else {
                        return o2.getTitle().compareTo(o1.getTitle());
                    }
                }

            });
            sortTitle = !sortTitle;
            sortId = false;
            model.fireTableDataChanged();



        });
        helpButton.addActionListener(e1 ->
        {
            DialogUtil.showInfo(this,"Двойной клик по элементу в таблице: редактирование" +
                    "\nЕсли есть вопросы, то нажимайте кнопку 'Обратная связь' ");
        });
        reportButton.addActionListener(e1 ->
        {
            DialogUtil.showInfo(this,"По всем вопросам:" +
                    "\nEmail: antondead1337@gmail.com" +
                    "\nТелефон: +79216423639");
        });

    }
}
