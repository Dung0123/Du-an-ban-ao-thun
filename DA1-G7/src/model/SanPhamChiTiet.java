package model;

/**
 *
 * @author cuongwf
 */
public class SanPhamChiTiet {

    private int id;
    private String ma;
    private int idSanPham;
    private String tenSanPham;
    private int idChatLieu;
    private String tenChatLieu;
    private int idChieuDaiTay;
    private String tenChieuDaiTay;
    private int idKichCo;
    private String tenKichCo;
    private int idMauSac;
    private String tenMauSac;
    private int idCoAo;
    private String tenCoAo;
    private int idXuatXu;
    private String tenXuatXu;
    private int soLuong;
    private double giaBan;
    private String moTa;
    private String maQR;
    private boolean trangThai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int id, String ma, int idSanPham, String tenSanPham, int idChatLieu, String tenChatLieu, int idChieuDaiTay, String tenChieuDaiTay, int idKichCo, String tenKichCo, int idMauSac, String tenMauSac, int idCoAo, String tenCoAo, int idXuatXu, String tenXuatXu, int soLuong, double giaBan, String moTa, String maQR, boolean trangThai) {
        this.id = id;
        this.ma = ma;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idChatLieu = idChatLieu;
        this.tenChatLieu = tenChatLieu;
        this.idChieuDaiTay = idChieuDaiTay;
        this.tenChieuDaiTay = tenChieuDaiTay;
        this.idKichCo = idKichCo;
        this.tenKichCo = tenKichCo;
        this.idMauSac = idMauSac;
        this.tenMauSac = tenMauSac;
        this.idCoAo = idCoAo;
        this.tenCoAo = tenCoAo;
        this.idXuatXu = idXuatXu;
        this.tenXuatXu = tenXuatXu;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.maQR = maQR;
        this.trangThai = trangThai;
    }

    public SanPhamChiTiet(String ma, int idSanPham, int idChatLieu, int idChieuDaiTay, int idKichCo, int idMauSac, int idCoAo, int idXuatXu, int soLuong, double giaBan, String moTa, String maQR, boolean trangThai) {
        this.ma = ma;
        this.idSanPham = idSanPham;
        this.idChatLieu = idChatLieu;
        this.idChieuDaiTay = idChieuDaiTay;
        this.idKichCo = idKichCo;
        this.idMauSac = idMauSac;
        this.idCoAo = idCoAo;
        this.idXuatXu = idXuatXu;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.maQR = maQR;
        this.trangThai = trangThai;
    }

    public SanPhamChiTiet(String ma, int idSanPham, int idChatLieu, int idChieuDaiTay, int idKichCo, int idMauSac, int idCoAo, int idXuatXu, int soLuong, double giaBan, String moTa, String maQR) {
        this.ma = ma;
        this.idSanPham = idSanPham;
        this.idChatLieu = idChatLieu;
        this.idChieuDaiTay = idChieuDaiTay;
        this.idKichCo = idKichCo;
        this.idMauSac = idMauSac;
        this.idCoAo = idCoAo;
        this.idXuatXu = idXuatXu;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.maQR = maQR;
    }

    public SanPhamChiTiet(int idChatLieu, int idChieuDaiTay, int idKichCo, int idMauSac, int idCoAo, int soLuong, double giaBan, String moTa, int idXuatXu) {
        this.idChatLieu = idChatLieu;
        this.idChieuDaiTay = idChieuDaiTay;
        this.idKichCo = idKichCo;
        this.idMauSac = idMauSac;
        this.idCoAo = idCoAo;
        this.idXuatXu = idXuatXu;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.moTa = moTa;
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

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(int idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public int getIdChieuDaiTay() {
        return idChieuDaiTay;
    }

    public void setIdChieuDaiTay(int idChieuDaiTay) {
        this.idChieuDaiTay = idChieuDaiTay;
    }

    public String getTenChieuDaiTay() {
        return tenChieuDaiTay;
    }

    public void setTenChieuDaiTay(String tenChieuDaiTay) {
        this.tenChieuDaiTay = tenChieuDaiTay;
    }

    public int getIdKichCo() {
        return idKichCo;
    }

    public void setIdKichCo(int idKichCo) {
        this.idKichCo = idKichCo;
    }

    public String getTenKichCo() {
        return tenKichCo;
    }

    public void setTenKichCo(String tenKichCo) {
        this.tenKichCo = tenKichCo;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public int getIdCoAo() {
        return idCoAo;
    }

    public void setIdCoAo(int idCoAo) {
        this.idCoAo = idCoAo;
    }

    public String getTenCoAo() {
        return tenCoAo;
    }

    public void setTenCoAo(String tenCoAo) {
        this.tenCoAo = tenCoAo;
    }

    public int getIdXuatXu() {
        return idXuatXu;
    }

    public void setIdXuatXu(int idXuatXu) {
        this.idXuatXu = idXuatXu;
    }

    public String getTenXuatXu() {
        return tenXuatXu;
    }

    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMaQR() {
        return maQR;
    }

    public void setMaQR(String maQR) {
        this.maQR = maQR;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPhamChiTiet{" + "id=" + id + ", ma=" + ma + ", idSanPham=" + idSanPham + ", tenSanPham=" + tenSanPham + ", idChatLieu=" + idChatLieu + ", tenChatLieu=" + tenChatLieu + ", idChieuDaiTay=" + idChieuDaiTay + ", tenChieuDaiTay=" + tenChieuDaiTay + ", idKichCo=" + idKichCo + ", tenKichCo=" + tenKichCo + ", idMauSac=" + idMauSac + ", tenMauSac=" + tenMauSac + ", idCoAo=" + idCoAo + ", tenCoAo=" + tenCoAo + ", idXuatXu=" + idXuatXu + ", tenXuatXu=" + tenXuatXu + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", moTa=" + moTa + ", maQR=" + maQR + ", trangThai=" + trangThai + '}';
    }

}
