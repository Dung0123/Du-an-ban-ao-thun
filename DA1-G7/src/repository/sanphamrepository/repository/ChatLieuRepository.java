package repository.sanphamrepository.repository;

import java.util.List;
import model.ChatLieu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import util.DBConnect;
import repository.sanphamrepository.IThuocTinhRepository;

/**
 *
 * @author cuongwf
 */
public class ChatLieuRepository implements IThuocTinhRepository<ChatLieu> {

    private final Connection conn = DBConnect.getConnection();

    private LocalDateTime localDateTime;

    @Override
    public List<ChatLieu> getAll() {
        String query = """
                       SELECT id, ten, ngayTao, ngaySua, isDelete
                       FROM ChatLieu
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<ChatLieu> listChatLieu = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listChatLieu.add(new ChatLieu(rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3).toLocalDateTime(),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getBoolean(5)));
            }
            return listChatLieu;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(ChatLieu entity) {
        int check = 0;
        String query = """
                       INSERT INTO ChatLieu
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
    public boolean update(ChatLieu entity) {
        int check = 0;
        String query = """
                       UPDATE ChatLieu SET ten = ?, ngaySua = ? WHERE ma = ?;
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, entity.getTen());
            ps.setObject(2, localDateTime.now());
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

}
