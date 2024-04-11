package service.sanphamservice.impl;

import java.util.List;
import model.DanhMuc;
import repository.sanphamrepository.IThuocTinhRepository;
import repository.sanphamrepository.repository.DanhMucRepository;
import service.sanphamservice.IThuocTinhService;

/**
 *
 * @author cuongwf
 */
public class DanhMucService implements IThuocTinhService<DanhMuc> {

    private final IThuocTinhRepository<DanhMuc> danhMucRepository
            = new DanhMucRepository();

    @Override
    public List<DanhMuc> getAll() {
        return danhMucRepository.getAll();
    }

    @Override
    public String add(DanhMuc entity) {
        return (danhMucRepository.add(entity))
                ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public String update(DanhMuc entity) {
        return (danhMucRepository.update(entity))
                ? "Sửa thành công!" : "Sửa thất bại!";
    }

}
