package repository.sanphamrepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.SanPhamChiTiet;
import util.DBConnect;

/**
 *
 * @author cuongwf
 */
public class SanPhamChiTietRepository {

    private final Connection conn = DBConnect.getConnection();

    public List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham) {
        String query = """
                       SELECT spct.id, spct.ma,
                       spct.idSanPham, sp.ten,
                       spct.idChatLieu, cl.ten,
                       spct.idChieuDaiTay, cdt.ten,
                       spct.idKichCo, kc.ten,
                       spct.idMauSac, ms.ten,
                       spct.idCoAo, ca.ten,
                       spct.idXuatXu, xx.ten,
                       spct.soLuong, spct.giaBan,
                       spct.moTa, spct.maVach, spct.trangThai
                       FROM SanPhamChiTiet spct
                       JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                       JOIN ChieuDaiTay cdt ON spct.idChieuDaiTay = cdt.id
                       JOIN KichCo kc ON spct.idKichCo = kc.id
                       JOIN MauSac ms ON spct.idMauSac = ms.id
                       JOIN SanPham sp ON spct.idSanPham = sp.id
                       JOIN CoAo ca ON spct.idCoAo = ca.id
                       JOIN XuatXu xx ON spct.idXuatXu = xx.id
                       WHERE sp.ma = ?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ps.setObject(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPhamChiTiet.add(
                        new SanPhamChiTiet(
                                rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getInt(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8),
                                rs.getInt(9), rs.getString(10),
                                rs.getInt(11), rs.getString(12),
                                rs.getInt(13), rs.getString(14),
                                rs.getInt(15), rs.getString(16),
                                rs.getInt(17), rs.getDouble(18),
                                rs.getString(19), rs.getString(20),
                                rs.getBoolean(21)));
            }
            return listSanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham, int offset, int limit) {
        String query = """
                       SELECT spct.id, spct.ma,
                       spct.idSanPham, sp.ten,
                       spct.idChatLieu, cl.ten,
                       spct.idChieuDaiTay, cdt.ten,
                       spct.idKichCo, kc.ten,
                       spct.idMauSac, ms.ten,
                       spct.idCoAo, ca.ten,
                       spct.idXuatXu, xx.ten,
                       spct.soLuong, spct.giaBan,
                       spct.moTa, spct.maVach, spct.trangThai
                       FROM SanPhamChiTiet spct
                       JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                       JOIN ChieuDaiTay cdt ON spct.idChieuDaiTay = cdt.id
                       JOIN KichCo kc ON spct.idKichCo = kc.id
                       JOIN MauSac ms ON spct.idMauSac = ms.id
                       JOIN SanPham sp ON spct.idSanPham = sp.id
                       JOIN CoAo ca ON spct.idCoAo = ca.id
                       JOIN XuatXu xx ON spct.idXuatXu = xx.id
                       WHERE sp.ma = ?
                       ORDER BY spct.ngayTao
                       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ps.setObject(1, maSanPham);
            ps.setObject(2, offset);
            ps.setObject(3, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPhamChiTiet.add(
                        new SanPhamChiTiet(
                                rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getInt(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8),
                                rs.getInt(9), rs.getString(10),
                                rs.getInt(11), rs.getString(12),
                                rs.getInt(13), rs.getString(14),
                                rs.getInt(15), rs.getString(16),
                                rs.getInt(17), rs.getDouble(18),
                                rs.getString(19), rs.getString(20),
                                rs.getBoolean(21)));
            }
            return listSanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public int countSanPhamChiTiet(String maSanPham) {
        String query = """
                       SELECT COUNT(*) FROM SanPhamChiTiet spct 
                       JOIN SanPham sp ON spct.idSanPham = sp.id WHERE sp.ma = ?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return 0;
    }

    public boolean add(SanPhamChiTiet sanPhamChiTiet) {
        int check = 0;
        String query = """
                       INSERT INTO SanPhamChiTiet (ma, idChatLieu, idChieuDaiTay,
                       idKichCo, idMauSac, idSanPham, idCoAo, soLuong, giaBan,
                       moTa, idXuatXu, maVach, trangThai)
                       VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, sanPhamChiTiet.getMa());
            ps.setObject(2, sanPhamChiTiet.getIdChatLieu());
            ps.setObject(3, sanPhamChiTiet.getIdChieuDaiTay());
            ps.setObject(4, sanPhamChiTiet.getIdKichCo());
            ps.setObject(5, sanPhamChiTiet.getIdMauSac());
            ps.setObject(6, sanPhamChiTiet.getIdSanPham());
            ps.setObject(7, sanPhamChiTiet.getIdCoAo());
            ps.setObject(8, sanPhamChiTiet.getSoLuong());
            ps.setObject(9, sanPhamChiTiet.getGiaBan());
            ps.setObject(10, sanPhamChiTiet.getMoTa());
            ps.setObject(11, sanPhamChiTiet.getIdXuatXu());
            ps.setObject(12, sanPhamChiTiet.getMaQR());
            ps.setObject(13, false);
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(SanPhamChiTiet sanPhamChiTiet) {
        int check = 0;
        String query = """
                       UPDATE SanPhamChiTiet SET idChatLieu=?,
                                              idChieuDaiTay=?, idKichCo=?, idMauSac=?,
                                              idCoAo=?, soLuong=?, giaBan=?,
                                              moTa=?, idXuatXu=? WHERE ma=?;
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, sanPhamChiTiet.getIdChatLieu());
            ps.setObject(2, sanPhamChiTiet.getIdChieuDaiTay());
            ps.setObject(3, sanPhamChiTiet.getIdKichCo());
            ps.setObject(4, sanPhamChiTiet.getIdMauSac());
            ps.setObject(5, sanPhamChiTiet.getIdCoAo());
            ps.setObject(6, sanPhamChiTiet.getSoLuong());
            ps.setObject(7, sanPhamChiTiet.getGiaBan());
            ps.setObject(8, sanPhamChiTiet.getMoTa());
            ps.setObject(9, sanPhamChiTiet.getIdXuatXu());
            ps.setObject(10, sanPhamChiTiet.getMa());
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static void main(String[] args) {
        SanPhamChiTietRepository sanPhamChiTietRepository = new SanPhamChiTietRepository();
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet("abc", 1, 1, 1, 1, 1, 1, 1, 123, 123, "abc", "abc");
        System.out.println(sanPhamChiTietRepository.getAllByIdSanPham("aaa"));
    }
}
