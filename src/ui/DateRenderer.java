package ui;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Copyright (c) Anton on 05.02.2018.
 */
public class DateRenderer extends DefaultTableCellRenderer {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    public void setValue(Object value) {
        if (value != null)
            value = formatter.format((LocalDate)value);
        super.setValue(value);
    }
}

