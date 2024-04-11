/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.voucherrepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import model.Voucher;
import modelViews.QlVoucher;

/**
 *
 * @author LENOVO
 */
public interface IVoucherResponsitory {
    
    ArrayList<Voucher> getAll();
    ArrayList<Voucher> getAllTrangThai012();
//    ArrayList<Voucher> getAllByTrangThai0();
//    ArrayList<Voucher> getAllByTrangThai1();
//    ArrayList<Voucher> getAllByTrangThai2();
    ArrayList<Voucher> getAllVoucherDeleted();
   
    ArrayList<Voucher> getAllByMaTrangThai0(String input);
    
   ArrayList<Voucher> getAllBynameKmTrangThai0(String input);
    ArrayList<Voucher> getAllByNgay0(Date input);
    
    ArrayList<Voucher> getAllByMaTrangThai3(String input);
    ArrayList<Voucher> getAllBynameKmTrangThai3(String input);
    ArrayList<Voucher> getAllByNgay3(Date input);
       
    Voucher addVoucher(Voucher voucher);
    
    Voucher updateVoucher(Voucher voucher);
    
    Voucher updateTrangThaiVoucher(Voucher voucher);

     ArrayList<Voucher> selectBySql(String sql, Object... args);
    
}
