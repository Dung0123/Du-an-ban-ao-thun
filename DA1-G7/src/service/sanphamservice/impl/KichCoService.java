package service.sanphamservice.impl;

import java.util.List;
import model.KichCo;
import repository.sanphamrepository.IThuocTinhRepository;
import repository.sanphamrepository.repository.KichCoRepository;
import service.sanphamservice.IThuocTinhService;

/**
 *
 * @author cuongwf
 */
public class KichCoService implements IThuocTinhService<KichCo> {

    private final IThuocTinhRepository<KichCo> kichCoRepository
            = new KichCoRepository();

    @Override
    public List<KichCo> getAll() {
        return kichCoRepository.getAll();
    }

    @Override
    public String add(KichCo entity) {
        return (kichCoRepository.add(entity))
                ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public String update(KichCo entity) {
        return (kichCoRepository.update(entity))
                ? "Sửa thành công!" : "Sửa thất bại!";
    }

}
