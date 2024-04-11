/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.voucherservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import model.Voucher;
import modelViews.QlVoucher;
import repository.voucherrepository.IVoucherResponsitory;
import repository.voucherrepository.Voucherrespository;

/**
 *
 * @author LENOVO
 */
public class VoucherService implements IVoucherServise {

    private IVoucherResponsitory iVoucherResponsitory = new Voucherrespository();
    private ArrayList<QlVoucher> _listVoucher;

    public VoucherService() {
        _listVoucher = new ArrayList<>();
    }

    @Override
    public ArrayList<QlVoucher> getAllVoucher() {

        _listVoucher = new ArrayList<>();
        ArrayList<Voucher> lstVCDomain = iVoucherResponsitory.getAll();
        for (Voucher voucher : lstVCDomain) {
            _listVoucher.add(new QlVoucher(voucher.getId(),
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QlVoucher> getAllVoucherTrangThai012() {
        _listVoucher = new ArrayList<>();
        ArrayList<Voucher> lstVCDomain = iVoucherResponsitory.getAllTrangThai012();
        for (Voucher voucher : lstVCDomain) {
            _listVoucher.add(new QlVoucher(
                    voucher.getId(),
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QlVoucher> getAllVoucherDeleted() {
        _listVoucher = new ArrayList<>();
        ArrayList<Voucher> lstVCDomain = iVoucherResponsitory.getAllVoucherDeleted();
        for (Voucher voucher : lstVCDomain) {
            _listVoucher.add(new QlVoucher(
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QlVoucher> getAllVoucherByMa0(String input) {
        _listVoucher = new ArrayList<>();
        ArrayList<Voucher> lstVoucherDomain = iVoucherResponsitory.getAllByMaTrangThai0(input);
        for (Voucher voucher : lstVoucherDomain) {
            _listVoucher.add(new QlVoucher(
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QlVoucher> getAllVoucherByNgay0(Date input) {
        _listVoucher = new ArrayList<>();
        ArrayList<Voucher> lstVoucherDomain = iVoucherResponsitory.getAllByNgay0(input);
        for (Voucher voucher : lstVoucherDomain) {
            _listVoucher.add(new QlVoucher(
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    new java.sql.Date(voucher.getNgayKetThuc().getTime()),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai()));
        }
        return _listVoucher;
    }

    @Override
    public QlVoucher addVoucher(QlVoucher voucher) {
        Voucher voucherDomain = new Voucher(voucher.getMa(),
                voucher.getTen(),
                voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(),
                voucher.isLoaiGiamGia(),
                voucher.getGiaTriGiam(),
                voucher.getSoLuong(),
                voucher.getGiaTriDHTT(),
                voucher.getTrangThai());
        if (iVoucherResponsitory.addVoucher(voucherDomain) != null) {
            return voucher = new QlVoucher(
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai());
        } else {
            return null;
        }
    }

    @Override
    public QlVoucher updateVoucher(QlVoucher voucher) {
        var x = iVoucherResponsitory
                .updateVoucher(new Voucher(
                        voucher.getMa(),
                        voucher.getTen(),
                        voucher.getNgayBatDau(),
                        voucher.getNgayKetThuc(),
                        voucher.isLoaiGiamGia(),
                        voucher.getGiaTriGiam(),
                        voucher.getSoLuong(),
                        voucher.getGiaTriDHTT(),
                        voucher.getTrangThai()
                ));

        return voucher = new QlVoucher(
                voucher.getMa(),
                voucher.getTen(),
                voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(),
                voucher.isLoaiGiamGia(),
                voucher.getGiaTriGiam(),
                voucher.getSoLuong(),
                voucher.getGiaTriDHTT(),
                voucher.getTrangThai()
        );

    }

    @Override
    public QlVoucher updateTrangThai1(QlVoucher voucher, Integer id) {

        Voucher voucherDomain = new Voucher(id,
                voucher.getMa(),
                voucher.getTen(),
                voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(),
                voucher.isLoaiGiamGia(),
                voucher.getGiaTriGiam(),
                voucher.getSoLuong(),
                voucher.getGiaTriDHTT(),
                1);
        if (iVoucherResponsitory.updateTrangThaiVoucher(voucherDomain) != null) {
            return voucher = new QlVoucher(voucher.getId(),
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai());
        } else {
            return null;
        }
    }

    @Override
    public QlVoucher deleteVoucher(QlVoucher voucher, String ma) {
        Voucher voucherDomain = new Voucher(
                ma,
                voucher.getTen(),
                voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(),
                voucher.isLoaiGiamGia(),
                voucher.getGiaTriGiam(),
                voucher.getSoLuong(),
                voucher.getGiaTriDHTT(),
                3);
        if (iVoucherResponsitory.updateTrangThaiVoucher(voucherDomain) != null) {
            return voucher = new QlVoucher(
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    3);
        } else {
            return null;
        }
    }

    @Override
    public QlVoucher khoiPhucVoucher(QlVoucher voucher, Integer id) {
        Voucher voucherDomain = new Voucher(id,
                voucher.getMa(),
                voucher.getTen(),
                voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(),
                voucher.isLoaiGiamGia(),
                voucher.getGiaTriGiam(),
                voucher.getSoLuong(),
                voucher.getGiaTriDHTT(),
                1);
        if (iVoucherResponsitory.updateTrangThaiVoucher(voucherDomain) != null) {
            return voucher = new QlVoucher(
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai());
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<QlVoucher> getAllVoucherByNameKM0(String input) {
        _listVoucher = new ArrayList<>();
        ArrayList<Voucher> lstVoucherDomain = iVoucherResponsitory.getAllBynameKmTrangThai0(input);
        for (Voucher voucher : lstVoucherDomain) {
            _listVoucher.add(new QlVoucher(
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QlVoucher> getAllVoucherByMa3(String input) {
        _listVoucher = new ArrayList<>();
        ArrayList<Voucher> lstVoucherDomain = iVoucherResponsitory.getAllByMaTrangThai3(input);
        for (Voucher voucher : lstVoucherDomain) {
            _listVoucher.add(new QlVoucher(
                    voucher.getId(),
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QlVoucher> getAllVoucherByNgay3(Date input) {
        _listVoucher = new ArrayList<>();
        ArrayList<Voucher> lstVoucherDomain = iVoucherResponsitory.getAllByNgay3(input);
        for (Voucher voucher : lstVoucherDomain) {
            _listVoucher.add(new QlVoucher(
                    voucher.getId(),
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QlVoucher> getAllVoucherByNameKM3(String input) {
        _listVoucher = new ArrayList<>();
        ArrayList<Voucher> lstVoucherDomain = iVoucherResponsitory.getAllBynameKmTrangThai3(input);
        for (Voucher voucher : lstVoucherDomain) {
            _listVoucher.add(new QlVoucher(
                    voucher.getMa(),
                    voucher.getTen(),
                    voucher.getNgayBatDau(),
                    voucher.getNgayKetThuc(),
                    voucher.isLoaiGiamGia(),
                    voucher.getGiaTriGiam(),
                    voucher.getSoLuong(),
                    voucher.getGiaTriDHTT(),
                    voucher.getTrangThai()));
        }
        return _listVoucher;
    }

}
