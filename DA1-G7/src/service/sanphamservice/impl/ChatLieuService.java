package service.sanphamservice.impl;

import java.util.List;
import model.ChatLieu;
import repository.sanphamrepository.IThuocTinhRepository;
import repository.sanphamrepository.repository.ChatLieuRepository;
import service.sanphamservice.IThuocTinhService;

/**
 *
 * @author cuongwf
 */
public class ChatLieuService implements IThuocTinhService<ChatLieu> {

    private final IThuocTinhRepository<ChatLieu> chatLieuRepository
            = new ChatLieuRepository();

    @Override
    public List<ChatLieu> getAll() {
        return chatLieuRepository.getAll();
    }

    @Override
    public String add(ChatLieu entity) {
        return (chatLieuRepository.add(entity))
                ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public String update(ChatLieu entity) {
        return (chatLieuRepository.update(entity))
                ? "Sửa thành công!" : "Sửa thất bại!";
    }
}
