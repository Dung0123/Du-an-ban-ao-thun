
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.voucherrepository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import model.Voucher;
import util.DBConnect;

/**
 *
 * @author LENOVO
 */
public class Voucherrespository implements IVoucherResponsitory {

    private ArrayList<Voucher> _listVoucher;
    final String SELECT_ALL_SQL = "SELECT ten,ma,ngayBatDau,ngayKetThuc,loaiGiamGia,giaTriGiam\n"
            + ",soLuong,GiaTriDhToiThieu,trangThai FROM \n"
            + "Voucher ORDER BY NgayKetThuc DESC , TrangThai ASC";

    final String SELECT_ALL_SQL012 = "SELECT ten,ma,ngayBatDau,ngayKetThuc,loaiGiamGia,giaTriGiam,\n"
            + "soLuong,GiaTriDhToiThieu,trangThai FROM Voucher WHERE TrangThai = 0 OR TrangThai = 1 \n"
            + "OR TrangThai = 2 ORDER BY trangThai ASC ,ma ASC ,ngayKetThuc DESC";
    final String INSERT_SQL = "INSERT INTO Voucher(ma,ten,ngayBatDau,ngayKetThuc,loaiGiamGia,giaTriGiam,trangThai,soLuong,GiaTriDhToiThieu) VALUES (?,?,?,?,?,?,?,?,?);";
    final String UPDATE_SQL = "UPDATE Voucher "
            + "SET ten = ?, "
            + "ngayBatDau = ?,"
            + " ngayKetThuc = ?,\n"
            + "loaiGiamGia = ?, "
            + "giaTriGiam = ?,"
            + "soLuong=?,"
            + "GiaTriDhToiThieu = ?,"
            + "trangThai= ?"
            + " WHERE ma = ?";
    final String SELECT_ALL_SQL_BY_MA_0 = "SELECT * FROM Voucher\n"
            + "WHERE Ma = ? AND (TrangThai = 0 OR TrangThai = 1 OR TrangThai = 2) ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NAMEKM_0 = "SELECT * FROM Voucher \n"
            + "WHERE ten=? AND (TrangThai = 0 OR\n"
            + "TrangThai = 1 OR TrangThai = 2)\n"
            + "ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NGAY_0 = "SELECT *\n"
            + "FROM Voucher\n"
            + "WHERE ( CONVERT(DATE, ngayKetThuc,103) >= CONVERT(DATE,?,103))\n"
            + "  AND (TrangThai = 0 OR TrangThai = 1 OR TrangThai = 2)";
    final String UPDATE_SoLuong_SQL = "UPDATE Voucher\n"
            + "SET SoLuong = ? - 1\n"
            + "WHERE Id = ? AND TrangThai = 0";
//
//    final String SELECT_ALL_SQL_BY_TRANGTHAI0 = "SELECT * FROM Voucher\n"
//            + "WHERE TrangThai = 0 ORDER BY NgayKetThuc DESC";
//    final String SELECT_ALL_SQL_BY_TRANGTHAI1 = "SELECT * FROM Voucher\n"
//            + "WHERE TrangThai = 1 ORDER BY NgayKetThuc DESC";
//    final String SELECT_ALL_SQL_BY_TRANGTHAI2 = "SELECT * FROM Voucher\n"
//            + "WHERE TrangThai = 2 ORDER BY NgayKetThuc DESC";

    final String SELECT_ALL_SQL_BY_MA_3 = "SELECT * FROM Voucher\n"
            + "WHERE Ma = ? AND TrangThai = 3 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NGAY_3 = "SELECT * FROM Voucher \n"
            + "WHERE (NgayKetThuc <= ?) AND TrangThai = 3 \n"
            + "ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_TRANGTHAI3 = "SELECT * FROM Voucher\n"
            + "WHERE TrangThai = 3 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_Name_3 = "SELECT * FROM Voucher WHERE ten = ? AND "
            + "TrangThai = 3 ORDER BY ngayKetThuc DESC";
    final String UPDATE_TRANGTHAI_SQL = "UPDATE Voucher\n"
            + "SET TrangThai = ?\n"
            + "WHERE ma = ?";

    public Voucherrespository() {
        _listVoucher = new ArrayList<>();
    }

    @Override
    public ArrayList<Voucher> getAll() {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ArrayList<Voucher> selectBySql(String sql, Object... args) {
        try {
            ResultSet rs = DBConnect.excuteQuery(sql, args);
            while (rs.next()) {
                _listVoucher.add(new Voucher(
                        rs.getString("ma"),
                        rs.getString("ten"),
                        rs.getDate("ngayBatDau"),
                        rs.getDate("ngayKetThuc"),
                        rs.getBoolean("loaiGiamGia"),
                        rs.getInt("giaTriGiam"),
                        rs.getInt("soLuong"),
                        rs.getBigDecimal("GiaTriDhToiThieu"),
                        rs.getInt("trangThai")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<Voucher> getAllTrangThai012() {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL012);
    }

    @Override
    public Voucher addVoucher(Voucher voucher) {
        DBConnect.excuteUpdate(INSERT_SQL, voucher.getMa(),
                voucher.getTen(),
                voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(),
                voucher.isLoaiGiamGia(),
                voucher.getGiaTriGiam(),
                voucher.getTrangThai(),
                voucher.getSoLuong(),
                voucher.getGiaTriDHTT());
        return voucher;
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
        DBConnect.excuteUpdate(UPDATE_SQL,
                voucher.getTen(),
                voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(),
                voucher.isLoaiGiamGia(),
                voucher.getGiaTriGiam(),
                voucher.getSoLuong(),
                voucher.getGiaTriDHTT(),
                voucher.getTrangThai(),
                voucher.getMa()
        );
        return voucher;
    }

    @Override
    public ArrayList<Voucher> getAllByMaTrangThai0(String input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_MA_0, input);
    }

    @Override
    public ArrayList<Voucher> getAllByNgay0(Date input) {
        _listVoucher = new ArrayList<>();
        System.out.println("SQL Query: " + SELECT_ALL_SQL_BY_NGAY_0);

        return selectBySql(SELECT_ALL_SQL_BY_NGAY_0, input);
    }

    @Override
    public ArrayList<Voucher> getAllVoucherDeleted() {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_TRANGTHAI3);
    }

    @Override
    public Voucher updateTrangThaiVoucher(Voucher voucher) {
        DBConnect.excuteUpdate(UPDATE_TRANGTHAI_SQL,
                voucher.getTrangThai(),
                voucher.getMa());
        return voucher;
    }

    @Override
    public ArrayList<Voucher> getAllBynameKmTrangThai0(String input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_NAMEKM_0, input);
    }

//    @Override
//    public ArrayList<Voucher> getAllByTrangThai0() {
//        _listVoucher = new ArrayList<>();
//        return selectBySql(SELECT_ALL_SQL_BY_TRANGTHAI0);
//    }
//
//    @Override
//    public ArrayList<Voucher> getAllByTrangThai1() {
//        _listVoucher = new ArrayList<>();
//        return selectBySql(SELECT_ALL_SQL_BY_TRANGTHAI1);
//    }
//
//    @Override
//    public ArrayList<Voucher> getAllByTrangThai2() {
//        _listVoucher = new ArrayList<>();
//        return selectBySql(SELECT_ALL_SQL_BY_TRANGTHAI2);
//    }
    @Override
    public ArrayList<Voucher> getAllByMaTrangThai3(String input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_MA_3, input);
    }

    @Override
    public ArrayList<Voucher> getAllByNgay3(Date input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_NGAY_3, input);
    }

    @Override
    public ArrayList<Voucher> getAllBynameKmTrangThai3(String input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_Name_3, input);
    }

}
