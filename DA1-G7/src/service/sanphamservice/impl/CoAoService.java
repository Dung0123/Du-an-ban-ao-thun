package service.sanphamservice.impl;

import java.util.List;
import model.CoAo;
import repository.sanphamrepository.IThuocTinhRepository;
import repository.sanphamrepository.repository.CoAoRepository;
import service.sanphamservice.IThuocTinhService;

/**
 *
 * @author cuongwf
 */
public class CoAoService implements IThuocTinhService<CoAo> {

    private final IThuocTinhRepository<CoAo> coAoRepository
            = new CoAoRepository();

    @Override
    public List<CoAo> getAll() {
        return coAoRepository.getAll();
    }

    @Override
    public String add(CoAo entity) {
        return (coAoRepository.add(entity))
                ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public String update(CoAo entity) {
        return (coAoRepository.update(entity))
                ? "Sửa thành công!" : "Sửa thất bại!";
    }

}
