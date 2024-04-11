package repository.sanphamrepository.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.ChieuDaiTay;
import util.DBConnect;
import repository.sanphamrepository.IThuocTinhRepository;

/**
 *
 * @author cuongwf
 */
public class ChieuDaiTayRepository implements IThuocTinhRepository<ChieuDaiTay> {

    private final Connection conn = DBConnect.getConnection();

    private LocalDateTime localDateTime;

    @Override
    public List<ChieuDaiTay> getAll() {
        String query = """
                       SELECT id, ten, ngayTao, ngaySua, isDelete
                       FROM ChieuDaiTay
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<ChieuDaiTay> listChieuDaiTay = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listChieuDaiTay.add(new ChieuDaiTay(rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3).toLocalDateTime(),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getBoolean(5)));
            }
            return listChieuDaiTay;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(ChieuDaiTay entity) {
        int check = 0;
        String query = """
                       INSERT INTO ChieuDaiTay
                       (ten, ngayTao, ngaySua, isDelete) VALUES(?, ?, ?, 0);
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, entity.getTen());
            ps.setObject(2, localDateTime.now());
            ps.setObject(3, localDateTime.now());
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(ChieuDaiTay entity) {
        int check = 0;
        String query = """
                       UPDATE ChieuDaiTay SET ten = ?, ngaySua = ? WHERE id = ?;
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, entity.getTen());
            ps.setObject(2, localDateTime.now());
            ps.setObject(3, entity.getId());
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

}
