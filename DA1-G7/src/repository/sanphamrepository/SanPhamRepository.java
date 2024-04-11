package repository.sanphamrepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;
import util.DBConnect;

/**
 *
 * @author cuongwf
 */
public class SanPhamRepository {

    private final Connection conn = DBConnect.getConnection();

    private LocalDateTime localDateTime;

    public List<SanPham> getAll() {
        String query = """
                       SELECT sp.id, sp.ma, sp.ten, idThuongHieu,
                       th.ten, idDanhMuc, dm.ten, sp.ngayTao,
                       sp.ngaySua, sp.isDelete, sumSoLuong
                       FROM SanPham sp JOIN DanhMuc dm ON sp.idDanhMuc = dm.id 
                       JOIN ThuongHieu th ON sp.idThuongHieu = th.id 
                       LEFT JOIN (SELECT idSanPham, SUM(soLuong) as sumSoLuong
                       FROM SanPhamChiTiet GROUP BY idSanPham) as sub ON sp.id = sub.idSanPham
                       ORDER BY sp.ngayTao DESC
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<SanPham> listSanPham = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPham.add(new SanPham(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getString(7),
                        rs.getTimestamp(8).toLocalDateTime(),
                        rs.getTimestamp(9).toLocalDateTime(),
                        rs.getBoolean(10),
                        rs.getInt(11)));
            }
            return listSanPham;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<SanPham> getAllWithPage(int offset, int limit) {
        String query = """
                       SELECT sp.id, sp.ma, sp.ten, idThuongHieu,
                                              th.ten, idDanhMuc, dm.ten, sp.ngayTao,
                                              sp.ngaySua, sp.isDelete, sumSoLuong
                                              FROM SanPham sp JOIN DanhMuc dm ON sp.idDanhMuc = dm.id 
                                              JOIN ThuongHieu th ON sp.idThuongHieu = th.id 
                                              LEFT JOIN (SELECT idSanPham, SUM(soLuong) as sumSoLuong
                                              FROM SanPhamChiTiet GROUP BY idSanPham) as sub ON sp.id = sub.idSanPham
                                              ORDER BY sp.ngayTao DESC
                                              OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<SanPham> listSanPham = new ArrayList<>();
            ps.setObject(1, offset);
            ps.setObject(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPham.add(new SanPham(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getString(7),
                        rs.getTimestamp(8).toLocalDateTime(),
                        rs.getTimestamp(9).toLocalDateTime(),
                        rs.getBoolean(10),
                        rs.getInt(11)));
            }
            return listSanPham;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public int countSanPham() {

        String query = """
                       SELECT COUNT(*) FROM SanPham
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {

        }
        return 0;
    }

    public boolean add(SanPham sanPham) {
        int check = 0;
        String query = """
                       INSERT INTO SanPham (ma, ten, ngayTao, ngaySua, isDelete,
                       idDanhMuc, idThuongHieu) VALUES(?, ?, ?, ?, ?, ?, ?)
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, sanPham.getMa());
            ps.setObject(2, sanPham.getTen());
            ps.setObject(3, localDateTime.now());
            ps.setObject(4, localDateTime.now());
            ps.setObject(5, false);
            ps.setObject(6, sanPham.getIdDanhMuc());
            ps.setObject(7, sanPham.getIdThuongHieu());
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static void main(String[] args) {

        SanPhamRepository sanPhamRepository = new SanPhamRepository();
        System.out.println(sanPhamRepository.getAll());
//        SanPham sanPham = new SanPham("234", "234", 1, 1);
//        sanPhamRepository.add(sanPham);
    }

}
