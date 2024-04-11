/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanphamservice.impl;

import java.util.List;
import model.SanPham;
import repository.sanphamrepository.SanPhamRepository;
import service.sanphamservice.ISanPhamService;

/**
 *
 * @author cuongwf
 */
public class SanPhamService implements ISanPhamService {

    private final SanPhamRepository sanPhamRepository = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.getAll();
    }

    @Override
    public String add(SanPham sanPham) {
        return (sanPhamRepository.add(sanPham))
                ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public List<SanPham> getAll(int offset, int limit) {
        return sanPhamRepository.getAllWithPage(offset, limit);
    }

    @Override
    public int countSanPham() {
        return sanPhamRepository.countSanPham();
    }

}
