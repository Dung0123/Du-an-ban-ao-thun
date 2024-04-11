/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Oanhbvb
 */
public class Uhelper {

    public static Boolean checkNull(JTextField txt, String mess) {

        if (txt.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }

    public static Boolean checkKyTuDacBiet(JTextField txt, String mess) {

        if (txt.getText().matches("[@#$%^&*()_+=|<>?{}\\[\\]~`-[!]]+")) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }

    public static Boolean checkText(JTextField txt, String mess) {

        if (txt.getText().trim().matches("[@#$%^&*()_+=|<>?{}\\[\\]~`-[!]]+")) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }

    public static Boolean checkSDT(JTextField txt, String mess) {

        if (!txt.getText().trim().matches("[0-9]{10}")) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }

    public static Boolean checkDate(JDateChooser date, String mess) {

        if (date.equals(null)) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }

    public static Boolean checkTime(Date date1, Date date2, String mess) {

        if (!((date1.before(date2)) || (date1.equals(date2)))) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }

        return false;
    }

    public static Boolean checkNumber(JTextField txt, String mess) {

        if (!txt.getText().matches("[0-9]+") || txt.getText().matches("[\s]+")) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }

    public static Boolean checkSoLuong(JTextField txt, String mess) {

        if (Integer.parseInt(txt.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }

    public static Boolean checkgiatrigiam(JTextField txt, String mess) {

        if ((Integer.parseInt(txt.getText()) < 0) || (Integer.parseInt(txt.getText()) > 50)) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }

    public static Boolean checkKiTuDacBiet(JTextField txt, String mess) {
        if (!txt.getText().trim().matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }

    public static Boolean checkEmail(JTextField txt, String mess) {
        if (!txt.getText().trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, mess);
            return true;
        }
        return false;
    }
}
