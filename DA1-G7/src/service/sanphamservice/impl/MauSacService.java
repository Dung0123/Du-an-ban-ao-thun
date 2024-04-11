package service.sanphamservice.impl;

import java.util.List;
import model.MauSac;
import repository.sanphamrepository.IThuocTinhRepository;
import repository.sanphamrepository.repository.MauSacRepository;
import service.sanphamservice.IThuocTinhService;

/**
 *
 * @author cuongwf
 */
public class MauSacService implements IThuocTinhService<MauSac> {

    private final IThuocTinhRepository<MauSac> mauSacRepository
            = new MauSacRepository();

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.getAll();
    }

    @Override
    public String add(MauSac entity) {
        return (mauSacRepository.add(entity))
                ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public String update(MauSac entity) {
        return (mauSacRepository.update(entity))
                ? "Sửa thành công!" : "Sửa thất bại!";
    }

}
