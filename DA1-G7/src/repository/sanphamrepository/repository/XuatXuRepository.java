package repository.sanphamrepository.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.XuatXu;
import util.DBConnect;

/**
 *
 * @author cuongwf
 */
public class XuatXuRepository {

    private final Connection conn = DBConnect.getConnection();

    private LocalDateTime localDateTime;

    public List<XuatXu> getAll() {
        String query = """
                       SELECT id, ten, ngayTao, ngaySua, isDelete FROM XuatXu
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<XuatXu> listXuatXu = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listXuatXu.add(new XuatXu(rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3).toLocalDateTime(),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getBoolean(5)));
            }
            return listXuatXu;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

}
