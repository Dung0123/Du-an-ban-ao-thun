package repository.sanphamrepository.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.CoAo;
import util.DBConnect;
import repository.sanphamrepository.IThuocTinhRepository;

/**
 *
 * @author cuongwf
 */
public class CoAoRepository implements IThuocTinhRepository<CoAo> {

    private final Connection conn = DBConnect.getConnection();

    private LocalDateTime localDateTime;

    @Override
    public List<CoAo> getAll() {
        String query = """
                       SELECT id, ten, ngayTao, ngaySua, isDelete FROM CoAo
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<CoAo> listCoAo = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listCoAo.add(new CoAo(rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3).toLocalDateTime(),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getBoolean(5)));
            }
            return listCoAo;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(CoAo entity) {
        int check = 0;
        String query = """
                       INSERT INTO CoAo
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
    public boolean update(CoAo entity) {
        int check = 0;
        String query = """
                       UPDATE CoAo SET ten = ?, ngaySua = ? WHERE id = ?;
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
