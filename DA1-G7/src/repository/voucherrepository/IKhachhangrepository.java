/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.voucherrepository;

import java.util.ArrayList;
import model.KhachHang;
import model.Voucher;

/**
 *
 * @author LENOVO
 */
public interface IKhachhangrepository {

    ArrayList<KhachHang> selectAll();
    ArrayList<KhachHang> getAllByMaTrangThai0(String input);

    ArrayList<KhachHang> getAllBynameKmTrangThai0(String input);

    KhachHang insert(KhachHang khachHang);

    KhachHang update(KhachHang khachHang);

    String delete(String ma);

    ArrayList<KhachHang> selectById(String ma);

    ArrayList<KhachHang> selectBySql(String sql, Object... args);
}
