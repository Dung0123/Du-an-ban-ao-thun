/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelViews;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class QlVoucher {

    private Integer id;
    private String ma;
    private String ten;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private boolean loaiGiamGia;
    private int giaTriGiam;
    private int soLuong;
    private BigDecimal giaTriDHTT;
    private int trangThai;

    public QlVoucher() {
    }

    public QlVoucher(Integer id, String ma, String ten, Date ngayBatDau, Date ngayKetThuc, boolean loaiGiamGia, int giaTriGiam, int soLuong, BigDecimal giaTriDHTT, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.loaiGiamGia = loaiGiamGia;
        this.giaTriGiam = giaTriGiam;
        this.soLuong = soLuong;
        this.giaTriDHTT = giaTriDHTT;
        this.trangThai = trangThai;
    }

    public QlVoucher(String ma, String ten, Date ngayBatDau, Date ngayKetThuc, boolean loaiGiamGia, int giaTriGiam, int soLuong, BigDecimal giaTriDHTT, int trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.loaiGiamGia = loaiGiamGia;
        this.giaTriGiam = giaTriGiam;
        this.soLuong = soLuong;
        this.giaTriDHTT = giaTriDHTT;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public boolean isLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(boolean loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public int getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(int giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaTriDHTT() {
        return giaTriDHTT;
    }

    public void setGiaTriDHTT(BigDecimal giaTriDHTT) {
        this.giaTriDHTT = giaTriDHTT;
    }

    public String hienThiTrangThai() {
        if (this.getTrangThai() == 0) {
            return "Đang diễn ra";
        } else if (this.getTrangThai() == 2) {
            return "Sắp diễn ra ";
        } else {
            return "Đã kết thúc";
        }
    }
}
