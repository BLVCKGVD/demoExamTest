package ru.angen.final_test.util;

import ru.angen.final_test.entity.ServiceEntity;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class CustomTableModel<T> extends AbstractTableModel {
    private Class<T> cls;
    private String[] columnNames;
    private List<T> rows;

    public CustomTableModel(Class<T> cls, String[] columnNames, List<T> rows) {
        this.cls = cls;
        this.columnNames = columnNames;
        this.rows = rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cls.getDeclaredFields().length;
    }


    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T t = rows.get(rowIndex);
        Field field = cls.getDeclaredFields()[columnIndex];
        field.setAccessible(true);
        try{
            return field.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "err";
        }

    }

    public List<T> getRows() {
        return rows;
    }
}
