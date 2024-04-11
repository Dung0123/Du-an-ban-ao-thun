package service.sanphamservice.impl;

import java.util.List;
import model.ThuongHieu;
import repository.sanphamrepository.IThuocTinhRepository;
import repository.sanphamrepository.repository.ThuongHieuRepository;
import service.sanphamservice.IThuocTinhService;

/**
 *
 * @author cuongwf
 */
public class ThuongHieuService implements IThuocTinhService<ThuongHieu> {

    private final IThuocTinhRepository<ThuongHieu> thuongHieuRepository
            = new ThuongHieuRepository();

    @Override
    public List<ThuongHieu> getAll() {
        return thuongHieuRepository.getAll();
    }

    @Override
    public String add(ThuongHieu entity) {
        return (thuongHieuRepository.add(entity))
                ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public String update(ThuongHieu entity) {
        return (thuongHieuRepository.update(entity))
                ? "Sửa thành công!" : "Sửa thất bại!";
    }

}
