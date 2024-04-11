package service.sanphamservice.impl;

import java.util.List;
import model.ChieuDaiTay;
import repository.sanphamrepository.IThuocTinhRepository;
import repository.sanphamrepository.repository.ChieuDaiTayRepository;
import service.sanphamservice.IThuocTinhService;

/**
 *
 * @author cuongwf
 */
public class ChieuDaiTayService implements IThuocTinhService<ChieuDaiTay> {

    private final IThuocTinhRepository<ChieuDaiTay> chieuDaiTayRepository
            = new ChieuDaiTayRepository();

    @Override
    public List<ChieuDaiTay> getAll() {
        return chieuDaiTayRepository.getAll();
    }

    @Override
    public String add(ChieuDaiTay entity) {
        return (chieuDaiTayRepository.add(entity))
                ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public String update(ChieuDaiTay entity) {
        return (chieuDaiTayRepository.update(entity))
                ? "Sửa thành công!" : "Sửa thất bại!";
    }

}
