package service.sanphamservice.impl;

import java.util.List;
import model.XuatXu;
import repository.sanphamrepository.repository.XuatXuRepository;

/**
 *
 * @author cuongwf
 */
public class XuatXuService {

    private final XuatXuRepository xuatXuRepository = new XuatXuRepository();

    public List<XuatXu> getAll() {
        return xuatXuRepository.getAll();
    }
}
