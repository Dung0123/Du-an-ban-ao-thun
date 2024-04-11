package model;

import java.time.LocalDateTime;

/**
 *
 * @author cuongwf
 */
public class SanPham {

    private int id;
    private String ma;
    private String ten;
    private int soLuong;
    private int idThuongHieu;
    private String tenThuongHieu;
    private int idDanhMuc;
    private String tenDanhMuc;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;

    private boolean isDelete;

    public SanPham() {
    }

    public SanPham(int id, String ma, String ten, int idThuongHieu, String tenThuongHieu, int idDanhMuc, String tenDanhMuc, LocalDateTime ngayTao, LocalDateTime ngaySua, boolean isDelete) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.idThuongHieu = idThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
        this.idDanhMuc = idDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.isDelete = isDelete;
    }

    public SanPham(int id, String ma, String ten, int idThuongHieu, String tenThuongHieu, int idDanhMuc, String tenDanhMuc, LocalDateTime ngayTao, LocalDateTime ngaySua, boolean isDelete, int soLuong) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.idThuongHieu = idThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
        this.idDanhMuc = idDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.isDelete = isDelete;
        this.soLuong = soLuong;
    }

//    public SanPham(int id, String ma, String ten, ThuongHieu thuongHieu, DanhMuc danhMuc, LocalDateTime ngaySua, boolean isDelete) {
//        this.id = id;
//        this.ma = ma;
//        this.ten = ten;
//        this.thuongHieu = thuongHieu;
//        this.danhMuc = danhMuc;
//        this.ngaySua = ngaySua;
//        this.isDelete = isDelete;
//    }
    public SanPham(String ma, String ten, int idThuongHieu, int idDanhMuc) {
        this.ma = ma;
        this.ten = ten;
        this.idThuongHieu = idThuongHieu;
        this.idDanhMuc = idDanhMuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public LocalDateTime getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(LocalDateTime ngaySua) {
        this.ngaySua = ngaySua;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return ten;
    }

}
