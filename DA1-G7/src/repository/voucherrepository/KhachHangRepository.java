/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.voucherrepository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.KhachHang;
import model.Voucher;
import util.DBConnect;

/**
 *
 * @author LENOVO
 */
public class KhachHangRepository implements IKhachhangrepository {

    final String INSERT_SQL = "insert into KhachHang(ma,ten,gioiTinh,sdt,email,diaChi,ngaySinh) values\n"
            + "(?,?,?,?,?,?,?)";
    final String UPDATE_SQL = " update KhachHang\n"
            + " set ten=?,gioiTinh=?,sdt=?,email=?,diaChi=?,ngaySinh=?\n"
            + " where ma=?";
    final String DELETE_BY_MA = "delete from KhachHang where ma=?";
    final String SELECT_BY_SQL = "select * from KhachHang where Ma=?";
    final String SELECT_ALL_SQL = "select ma,ten,gioiTinh,sdt,email,diaChi,ngaySinh \n"
            + " from KhachHang";
    final String SELECT_ALL_SQL_BY_MA_0 = " SELECT id,ma,ten,gioiTinh,sdt,email,diaChi,ngaySinh  FROM KhachHang \n"
            + " WHERE ma=?";
    final String SELECT_ALL_SQL_BY_NAMEKM_0 = "SELECT id,ma,ten,gioiTinh,sdt,email,diaChi,ngaySinh FROM KhachHang  WHERE ten=?";
    private ArrayList<KhachHang> _lstKh = new ArrayList<>();
    private IKhachhangrepository _iKhachhangrepository;

    @Override
    public ArrayList<KhachHang> selectAll() {
        _lstKh = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang insert(KhachHang khachHang) {
        DBConnect.excuteUpdate(INSERT_SQL, khachHang.getMaKh(), khachHang.getTenKh(), khachHang.getGioiTinh(),
                khachHang.getSdt(), khachHang.getEmail(), khachHang.getDiaChi(), new java.util.Date(khachHang.getNgaySinh().getTime())
        );
        return khachHang;
    }

    @Override
    public KhachHang update(KhachHang khachHang) {
        DBConnect.excuteUpdate(UPDATE_SQL, khachHang.getTenKh(), khachHang.getGioiTinh(),
                khachHang.getSdt(), khachHang.getEmail(), khachHang.getDiaChi(),
                new java.util.Date(khachHang.getNgaySinh().getTime()), khachHang.getMaKh()
        );
        return khachHang;
    }

    @Override
    public String delete(String ma) {
        DBConnect.excuteUpdate(DELETE_BY_MA, ma);
        return ma;
    }

    @Override
    public ArrayList<KhachHang> selectById(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<KhachHang> selectBySql(String sql, Object... args) {
        ArrayList<KhachHang> _lstKh = new ArrayList<>();
        try {
            ResultSet rs = DBConnect.excuteQuery(sql, args);
            while (rs.next()) {

                String ma = rs.getString("ma");
                String ten = rs.getString("ten");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                Date ngaySinh = rs.getDate("ngaySinh");
                KhachHang khachHang = new KhachHang();

                khachHang.setMaKh(ma);
                khachHang.setTenKh(ten);
                khachHang.setSdt(sdt);
                khachHang.setEmail(email);
                khachHang.setDiaChi(diaChi);
                khachHang.setGioiTinh(gioiTinh);
                khachHang.setNgaySinh(ngaySinh);
                _lstKh.add(khachHang);
            }
        } catch (Exception e) {
        }
        return _lstKh;
    }

    @Override
    public ArrayList<KhachHang> getAllByMaTrangThai0(String input) {
        _lstKh = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_MA_0, input);
    }

    @Override
    public ArrayList<KhachHang> getAllBynameKmTrangThai0(String input) {
        _lstKh = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_NAMEKM_0, input);
    }
}
