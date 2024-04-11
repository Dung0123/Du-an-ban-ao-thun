/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.voucherservice;

import java.util.ArrayList;
import model.KhachHang;
import modelViews.QlKhachHang;
import modelViews.QlVoucher;

/**
 *
 * @author LENOVO
 */
public interface IKhachHangServise {

    ArrayList<QlKhachHang> selectAll();

    ArrayList<QlKhachHang> getAllKhByMa0(String input);

    ArrayList<QlKhachHang> getAllKhByNameKM0(String input);

    QlKhachHang insert(QlKhachHang qlKhachHang);

    QlKhachHang update(QlKhachHang qlKhachHang);

    String delete(String ma);

    ArrayList<KhachHang> selectById(String ma);

}
