package service.sanphamservice.impl;

import java.util.List;
import model.SanPhamChiTiet;
import repository.sanphamrepository.SanPhamChiTietRepository;
import service.sanphamservice.ISanPhamChiTietService;

/**
 *
 * @author cuongwf
 */
public class SanPhamChiTietService implements ISanPhamChiTietService {

    private final SanPhamChiTietRepository sanPhamChiTietRepository = new SanPhamChiTietRepository();

    @Override
    public List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham) {
        return sanPhamChiTietRepository.getAllByIdSanPham(maSanPham);
    }

    @Override
    public List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham, int offset, int limit) {
        return sanPhamChiTietRepository.getAllByIdSanPham(maSanPham, offset, limit);
    }

    @Override
    public String add(SanPhamChiTiet sanPhamChiTiet) {
        return (sanPhamChiTietRepository.add(sanPhamChiTiet)
                ? "Thêm thành công!" : "Thêm thất bại!");
    }

    @Override
    public String update(SanPhamChiTiet sanPhamChiTiet) {
        return (sanPhamChiTietRepository.update(sanPhamChiTiet)
                ? "Cập nhật thành công!" : "Cập nhật thất bại!");
    }

    @Override
    public int countSanPhamChiTiet(String maSanPham) {
        return sanPhamChiTietRepository.countSanPhamChiTiet(maSanPham);
    }

}
