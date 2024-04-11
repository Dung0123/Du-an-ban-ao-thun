package service.sanphamservice;

import java.util.List;
import model.SanPhamChiTiet;

/**
 *
 * @author cuongwf
 */
public interface ISanPhamChiTietService {

    List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham);

    List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham, int offset, int limit);

    int countSanPhamChiTiet(String maSanPham);

    String add(SanPhamChiTiet sanPhamChiTiet);

    String update(SanPhamChiTiet sanPhamChiTiet);

}
