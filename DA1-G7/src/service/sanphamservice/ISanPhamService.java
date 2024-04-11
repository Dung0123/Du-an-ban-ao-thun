package service.sanphamservice;

import java.util.List;
import model.SanPham;

/**
 *
 * @author cuongwf
 */
public interface ISanPhamService {

    List<SanPham> getAll();

    List<SanPham> getAll(int offset, int limit);

    String add(SanPham sanPham);

    int countSanPham();
}
