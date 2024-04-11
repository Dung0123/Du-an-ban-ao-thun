package model;

import java.time.LocalDateTime;

/**
 *
 * @author cuongwf
 */
public class ChatLieu {

    private int id;
    private String ten;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private boolean isDelete;

    public ChatLieu() {
    }

    public ChatLieu(int id, String ten, LocalDateTime ngayTao, LocalDateTime ngaySua, boolean isDelete) {
        this.id = id;
        this.ten = ten;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.isDelete = isDelete;
    }
    
    public ChatLieu(String ten) {
        this.ten = ten;
    }
    
    public ChatLieu(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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
