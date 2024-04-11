/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.voucherservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import modelViews.QlVoucher;

/**
 *
 * @author LENOVO
 */
public interface IVoucherServise{
     ArrayList<QlVoucher> getAllVoucher();
    ArrayList<QlVoucher> getAllVoucherTrangThai012();
     
    ArrayList<QlVoucher> getAllVoucherDeleted();   
    
   
    ArrayList<QlVoucher> getAllVoucherByMa0(String input);
   ArrayList<QlVoucher> getAllVoucherByNameKM0(String input);
    ArrayList<QlVoucher> getAllVoucherByNgay0(Date input);
    
    
    ArrayList<QlVoucher> getAllVoucherByMa3(String input);
     ArrayList<QlVoucher> getAllVoucherByNameKM3(String input);
    ArrayList<QlVoucher> getAllVoucherByNgay3(Date input);
    
    QlVoucher addVoucher(QlVoucher voucher);
    
    QlVoucher updateVoucher(QlVoucher voucher);
    
    QlVoucher updateTrangThai1(QlVoucher voucher, Integer id);
    
    QlVoucher deleteVoucher(QlVoucher voucher, String ma);
    
    QlVoucher khoiPhucVoucher(QlVoucher voucher, Integer id);
    
}
