package view;

import app.view.swing.EventPagination;
import app.view.swing.PaginationItemRenderStyle1;
import view.jdialog.JDialogThemSanPham;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.ChieuDaiTay;
import model.CoAo;
import model.DanhMuc;
import model.KichCo;
import model.MauSac;
import model.SanPham;
import model.SanPhamChiTiet;
import model.ThuongHieu;
import model.XuatXu;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.sanphamservice.ISanPhamChiTietService;
import service.sanphamservice.ISanPhamService;
import service.sanphamservice.IThuocTinhService;
import service.sanphamservice.impl.ChatLieuService;
import service.sanphamservice.impl.ChieuDaiTayService;
import service.sanphamservice.impl.CoAoService;
import service.sanphamservice.impl.DanhMucService;
import service.sanphamservice.impl.KichCoService;
import service.sanphamservice.impl.MauSacService;
import service.sanphamservice.impl.SanPhamChiTietService;
import service.sanphamservice.impl.SanPhamService;
import service.sanphamservice.impl.ThuongHieuService;
import service.sanphamservice.impl.XuatXuService;
import view.jdialog.JDialogSanPhamChiTiet;
import view.jdialog.JDialogThemChatLieu;
import view.jdialog.JDialogThemChieuDaiTay;
import view.jdialog.JDialogThemCoAo;
import view.jdialog.JDialogThemDanhMuc;
import view.jdialog.JDialogThemKichCo;
import view.jdialog.JDialogThemMauSac;
import view.jdialog.JDialogThemThuongHieu;

/**
 *
 * @author bcuon
 */
public class ViewSanPham extends javax.swing.JPanel {

    private JFrame parentFrame = new MainJFrame();

    private DefaultComboBoxModel<ThuongHieu> dcbmThuongHieu = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DanhMuc> dcbmDanhMuc = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<ChatLieu> dcbmChatLieu = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<MauSac> dcbmMauSac = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<KichCo> dcbmKichCo = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<CoAo> dcbmCoAo = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<ChieuDaiTay> dcbmChieuDaiTay = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<XuatXu> dcbmXuatXu = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<SanPham> dcbmSanPham = new DefaultComboBoxModel<>();

    private DefaultComboBoxModel<ThuongHieu> dcbmThuongHieuThemNhanh = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DanhMuc> dcbmDanhMucThemNhanh = new DefaultComboBoxModel<>();

    private DefaultTableModel dtmThuocTinh = new DefaultTableModel();
    private DefaultTableModel dtmSanPham = new DefaultTableModel();
    private DefaultTableModel dtmSanPhamChiTiet = new DefaultTableModel();

    private final IThuocTinhService<ChatLieu> chatLieuService = new ChatLieuService();
    private final IThuocTinhService<ChieuDaiTay> chieuDaiTayService = new ChieuDaiTayService();
    private final IThuocTinhService<CoAo> coAoService = new CoAoService();
    private final IThuocTinhService<DanhMuc> danhMucService = new DanhMucService();
    private final IThuocTinhService<KichCo> kichCoService = new KichCoService();
    private final IThuocTinhService<MauSac> mauSacService = new MauSacService();
    private final IThuocTinhService<ThuongHieu> thuongHieuService = new ThuongHieuService();
    private final XuatXuService xuatXuService = new XuatXuService();
    private final ISanPhamService sanPhamService = new SanPhamService();
    private final ISanPhamChiTietService sanPhamChiTietService = new SanPhamChiTietService();

    private SanPham selectedSanPham;

    private int currentPage = 1;

    public ViewSanPham() {
        initComponents();

    }

    public ViewSanPham(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initComponents();

        dcbmThuongHieu = (DefaultComboBoxModel<ThuongHieu>) cbbThuongHieu.getModel();
        dcbmDanhMuc = (DefaultComboBoxModel<DanhMuc>) cbbDanhMuc.getModel();
        dcbmChatLieu = (DefaultComboBoxModel<ChatLieu>) cbbChatLieu.getModel();
        dcbmMauSac = (DefaultComboBoxModel<MauSac>) cbbMauSac.getModel();
        dcbmKichCo = (DefaultComboBoxModel<KichCo>) cbbKichCo.getModel();
        dcbmCoAo = (DefaultComboBoxModel<CoAo>) cbbCoAo.getModel();
        dcbmChieuDaiTay = (DefaultComboBoxModel<ChieuDaiTay>) cbbChieuDaiTay.getModel();
        dcbmXuatXu = (DefaultComboBoxModel<XuatXu>) cbbXuatXu.getModel();
        dcbmSanPham = (DefaultComboBoxModel<SanPham>) cbbSanPham.getModel();

        dcbmThuongHieuThemNhanh = (DefaultComboBoxModel<ThuongHieu>) cbbThuongHieuThemNhanh.getModel();
        dcbmDanhMucThemNhanh = (DefaultComboBoxModel<DanhMuc>) cbbDanhMucThemNhanh.getModel();

        loadDataComboboxProperties(dcbmThuongHieu, "ThuongHieu");
        loadDataComboboxProperties(dcbmDanhMuc, "DanhMuc");
        loadDataComboboxProperties(dcbmChatLieu, "ChatLieu");
        loadDataComboboxProperties(dcbmMauSac, "MauSac");
        loadDataComboboxProperties(dcbmKichCo, "KichCo");
        loadDataComboboxProperties(dcbmCoAo, "CoAo");
        loadDataComboboxProperties(dcbmChieuDaiTay, "ChieuDaiTay");
        loadDataComboboxProperties(dcbmXuatXu, "XuatXu");
        loadDataComboboxProperties(dcbmSanPham, "SanPham");

        loadDataComboboxThuongHieuThemNhanh();
        loadDataComboboxDanhMucThemNhanh();

        dtmThuocTinh = (DefaultTableModel) tblThuocTinh.getModel();
        dtmSanPham = (DefaultTableModel) tblSanPham.getModel();
        dtmSanPhamChiTiet = (DefaultTableModel) tblSanPhamChiTiet.getModel();

        rdoThuongHieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoDanhMuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoChatLieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoMauSac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoKichCo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoCoAo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoChieuDaiTay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        cbbSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSanPham = (SanPham) cbbSanPham.getSelectedItem();
                loadAndPaginateDataForSanPham(selectedSanPham);
            }
        });

        rdoThuongHieu.setSelected(true);
        showDataProperties();
//        showDataSanPham();

        cbbSanPham.setSelectedIndex(0);

        paginationSanPham.setPaginationItemRender(new PaginationItemRenderStyle1());
        paginationSanPham.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataSanPhamPage(page);
            }
        });
        loadDataSanPhamPage(1);

    }

    private void loadAndPaginateDataForSanPham(SanPham selectedSanPham) {
        loadDataSanPhamChiTietPage(1, selectedSanPham.getMa());
        configurePagination();
    }

    private void configurePagination() {
        paginationSanPhamChiTiet.setPaginationItemRender(new PaginationItemRenderStyle1());
        paginationSanPhamChiTiet.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataSanPhamChiTietPage(page, selectedSanPham.getMa());
            }
        });
    }

    private void loadDataSanPhamPage(int page) {
        int limit = 10;
        int offset = (page - 1) * limit;
        try {
            int rowCount = sanPhamService.countSanPham();
            int total = (int) Math.ceil((double) rowCount / limit);
            showDataSanPhamWithPage(sanPhamService.getAll(offset, limit));
            paginationSanPham.setPagegination(page, total);
            currentPage = page;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataSanPhamChiTietPage(int page, String maSanPham) {
        int limit = 10;
        int offset = (page - 1) * limit;
        try {
            int rowCount = sanPhamChiTietService.countSanPhamChiTiet(maSanPham);
            int total = (int) Math.ceil((double) rowCount / limit);
            showDataSanPhamChiTietWithPage(maSanPham, offset, limit);
            paginationSanPhamChiTiet.setPagegination(page, total);
            currentPage = page;
        } catch (Exception e) {
        }
    }

    private void loadDataComboboxThuongHieuThemNhanh() {
        dcbmThuongHieuThemNhanh.removeAllElements();
        for (ThuongHieu thuongHieu : thuongHieuService.getAll()) {
            dcbmThuongHieuThemNhanh.addElement(thuongHieu);
        }
    }

    private void loadDataComboboxDanhMucThemNhanh() {
        dcbmDanhMucThemNhanh.removeAllElements();
        for (DanhMuc danhMuc : danhMucService.getAll()) {
            dcbmDanhMucThemNhanh.addElement(danhMuc);
        }
    }

    private void loadDataComboboxProperties(DefaultComboBoxModel<?> model, String object) {
        model.removeAllElements();
        switch (object) {
            case "ThuongHieu":
                for (ThuongHieu thuongHieu : thuongHieuService.getAll()) {
                    dcbmThuongHieu.addElement(thuongHieu);
                }
                break;
            case "DanhMuc":
                for (DanhMuc danhMuc : danhMucService.getAll()) {
                    dcbmDanhMuc.addElement(danhMuc);
                }
                break;
            case "ChatLieu":
                for (ChatLieu chatLieu : chatLieuService.getAll()) {
                    dcbmChatLieu.addElement(chatLieu);
                }
                break;
            case "MauSac":
                for (MauSac mauSac : mauSacService.getAll()) {
                    dcbmMauSac.addElement(mauSac);
                }
                break;
            case "KichCo":
                for (KichCo kichCo : kichCoService.getAll()) {
                    dcbmKichCo.addElement(kichCo);
                }
                break;
            case "CoAo":
                for (CoAo coAo : coAoService.getAll()) {
                    dcbmCoAo.addElement(coAo);
                }
                break;
            case "ChieuDaiTay":
                for (ChieuDaiTay chieuDaiTay : chieuDaiTayService.getAll()) {
                    dcbmChieuDaiTay.addElement(chieuDaiTay);
                }
                break;
            case "XuatXu":
                for (XuatXu xuatXu : xuatXuService.getAll()) {
                    dcbmXuatXu.addElement(xuatXu);
                }
                break;
            case "SanPham":
                for (SanPham sanPham : sanPhamService.getAll()) {
                    dcbmSanPham.addElement(sanPham);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void showDataProperties() {
        if (rdoThuongHieu.isSelected()) {
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (ThuongHieu thuongHieu : thuongHieuService.getAll()) {
                dtmThuocTinh.addRow(new Object[]{
                    thuongHieu.getId(),
                    thuongHieu.getTen()
                });
            }
        } else if (rdoDanhMuc.isSelected()) {
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (DanhMuc danhMuc : danhMucService.getAll()) {
                dtmThuocTinh.addRow(new Object[]{
                    danhMuc.getId(),
                    danhMuc.getTen()
                });
            }
        } else if (rdoChatLieu.isSelected()) {
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (ChatLieu chatLieu : chatLieuService.getAll()) {
                dtmThuocTinh.addRow(new Object[]{
                    chatLieu.getId(),
                    chatLieu.getTen()
                });
            }
        } else if (rdoMauSac.isSelected()) {
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (MauSac mauSac : mauSacService.getAll()) {
                dtmThuocTinh.addRow(new Object[]{
                    mauSac.getId(),
                    mauSac.getTen()
                });
            }
        } else if (rdoKichCo.isSelected()) {
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (KichCo kichCo : kichCoService.getAll()) {
                dtmThuocTinh.addRow(new Object[]{
                    kichCo.getId(),
                    kichCo.getTen()
                });
            }
        } else if (rdoCoAo.isSelected()) {
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (CoAo coAo : coAoService.getAll()) {
                dtmThuocTinh.addRow(new Object[]{
                    coAo.getId(),
                    coAo.getTen()
                });
            }
        } else if (rdoChieuDaiTay.isSelected()) {
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (ChieuDaiTay chieuDaiTay : chieuDaiTayService.getAll()) {
                dtmThuocTinh.addRow(new Object[]{
                    chieuDaiTay.getId(),
                    chieuDaiTay.getTen()
                });
            }
        }
    }

    private void showDataSanPham() {
        dtmSanPham.setRowCount(0);
        for (SanPham sanPham : sanPhamService.getAll()) {
            dtmSanPham.addRow(new Object[]{
                sanPham.getMa(),
                sanPham.getTen(),
                sanPham.getSoLuong(),
                sanPham.getTenThuongHieu(),
                sanPham.getTenDanhMuc()
            });
        }
    }

    private void showDataSanPhamWithPage(List<SanPham> listSanPham) {
        dtmSanPham.setRowCount(0);
        for (SanPham sanPham : listSanPham) {
            dtmSanPham.addRow(new Object[]{
                sanPham.getMa(),
                sanPham.getTen(),
                sanPham.getSoLuong(),
                sanPham.getTenThuongHieu(),
                sanPham.getTenDanhMuc()
            });
        }
    }

    private void showDataSanPhamChiTiet(String maSanPham) {
        dtmSanPhamChiTiet.setRowCount(0);
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietService.getAllByIdSanPham(maSanPham)) {
            dtmSanPhamChiTiet.addRow(new Object[]{
                sanPhamChiTiet.getMa(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getTenMauSac(),
                sanPhamChiTiet.getTenKichCo(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenCoAo(),
                sanPhamChiTiet.getTenChieuDaiTay(),
                sanPhamChiTiet.getTenXuatXu(),
                sanPhamChiTiet.getMaQR()
            });
        }
    }

    private void showDataSanPhamChiTietWithPage(String maSanPham, int offset, int limit) {
        dtmSanPhamChiTiet.setRowCount(0);
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietService.getAllByIdSanPham(maSanPham, offset, limit)) {
            dtmSanPhamChiTiet.addRow(new Object[]{
                sanPhamChiTiet.getMa(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getTenMauSac(),
                sanPhamChiTiet.getTenKichCo(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenCoAo(),
                sanPhamChiTiet.getTenChieuDaiTay(),
                sanPhamChiTiet.getTenXuatXu(),
                sanPhamChiTiet.getMaQR()
            });
        }
    }

    private ThuongHieu addAndUpdateThuongHieu() {
        ThuongHieu thuongHieu = null;
        String tenThuocTinh = txtTenThuocTinh.getText();
        thuongHieu = new ThuongHieu(tenThuocTinh);
        return thuongHieu;
    }

    private DanhMuc addAndUpdateDanhMuc() {
        DanhMuc danhMuc = null;
        String tenThuocTinh = txtTenThuocTinh.getText();
        danhMuc = new DanhMuc(tenThuocTinh);
        return danhMuc;
    }

    private ChatLieu addAndUpdateChatLieu() {
        ChatLieu chatLieu = null;
        String tenThuocTinh = txtTenThuocTinh.getText();
        chatLieu = new ChatLieu(tenThuocTinh);
        return chatLieu;
    }

    private MauSac addAndUpdateMauSac() {
        MauSac mauSac = null;
        String tenThuocTinh = txtTenThuocTinh.getText();
        mauSac = new MauSac(tenThuocTinh);
        return mauSac;
    }

    private KichCo addAndUpdateKichCo() {
        KichCo kichCo = null;
        String tenThuocTinh = txtTenThuocTinh.getText();
        kichCo = new KichCo(tenThuocTinh);
        return kichCo;
    }

    private CoAo addAndUpdateCoAo() {
        CoAo coAo = null;
        String tenThuocTinh = txtTenThuocTinh.getText();
        coAo = new CoAo(tenThuocTinh);
        return coAo;
    }

    private ChieuDaiTay addAndUpdateChieuDaiTay() {
        ChieuDaiTay chieuDaiTay = null;
        String tenThuocTinh = txtTenThuocTinh.getText();
        chieuDaiTay = new ChieuDaiTay(tenThuocTinh);
        return chieuDaiTay;
    }

    private SanPham addAndUpdateSanPham() {
        SanPham sanPham = null;
        String ma = txtMaSanPham.getText();
        String ten = txtTenSanPham.getText();
        ThuongHieu thuongHieuSelected = (ThuongHieu) dcbmThuongHieu.getSelectedItem();
        DanhMuc danhMucSelected = (DanhMuc) dcbmDanhMuc.getSelectedItem();
        sanPham = new SanPham(ma, ten, thuongHieuSelected.getId(), danhMucSelected.getId());
        return sanPham;
    }

    private SanPham addNhanhSanPham() {
        SanPham sanPham = null;
        String ma = txtMaSanPhamThemNhanh.getText();
        String ten = txtTenSanPhamThemNhanh.getText();
        ThuongHieu thuongHieuSelected = (ThuongHieu) dcbmThuongHieuThemNhanh.getSelectedItem();
        DanhMuc danhMucSelected = (DanhMuc) dcbmDanhMucThemNhanh.getSelectedItem();
        sanPham = new SanPham(ma, ten, thuongHieuSelected.getId(), danhMucSelected.getId());
        return sanPham;
    }

    private SanPhamChiTiet addSanPhamChiTiet() {
        SanPhamChiTiet sanPhamChiTiet = null;
        SanPham sanPhamSelected = (SanPham) dcbmSanPham.getSelectedItem();
        ChatLieu chatLieuSelected = (ChatLieu) dcbmChatLieu.getSelectedItem();
        CoAo coAoSelected = (CoAo) dcbmCoAo.getSelectedItem();
        MauSac mauSacSelected = (MauSac) dcbmMauSac.getSelectedItem();
        KichCo kichCoSelected = (KichCo) dcbmKichCo.getSelectedItem();
        ChieuDaiTay chieuDaiTaySelected = (ChieuDaiTay) dcbmChieuDaiTay.getSelectedItem();
        XuatXu xuatXu = (XuatXu) dcbmXuatXu.getSelectedItem();
        String maCTSP = txtMaCTSP.getText();
        String maVach = txtMaVach.getText();
        String soLuong = txtSoLuong.getText();
        String giaBan = txtGiaBan.getText();
        String moTa = txtMoTa.getText();
        sanPhamChiTiet = new SanPhamChiTiet(maCTSP,
                sanPhamSelected.getId(),
                chatLieuSelected.getId(),
                chieuDaiTaySelected.getId(),
                kichCoSelected.getId(),
                mauSacSelected.getId(),
                coAoSelected.getId(),
                xuatXu.getId(),
                Integer.parseInt(soLuong),
                Double.parseDouble(giaBan),
                moTa, maVach);
        return sanPhamChiTiet;
    }

    private SanPhamChiTiet updateSanPhamChiTiet() {
        SanPhamChiTiet sanPhamChiTiet = null;
        ChatLieu chatLieuSelected = (ChatLieu) dcbmChatLieu.getSelectedItem();
        CoAo coAoSelected = (CoAo) dcbmCoAo.getSelectedItem();
        MauSac mauSacSelected = (MauSac) dcbmMauSac.getSelectedItem();
        KichCo kichCoSelected = (KichCo) dcbmKichCo.getSelectedItem();
        ChieuDaiTay chieuDaiTaySelected = (ChieuDaiTay) dcbmChieuDaiTay.getSelectedItem();
        XuatXu xuatXu = (XuatXu) dcbmXuatXu.getSelectedItem();
        String soLuong = txtSoLuong.getText();
        String giaBan = txtGiaBan.getText();
        String moTa = txtMoTa.getText();
        sanPhamChiTiet = new SanPhamChiTiet(
                chatLieuSelected.getId(),
                chieuDaiTaySelected.getId(),
                kichCoSelected.getId(),
                mauSacSelected.getId(),
                coAoSelected.getId(),
                Integer.parseInt(soLuong),
                Double.parseDouble(giaBan), moTa, xuatXu.getId());
        return sanPhamChiTiet;
    }

    private void fillDataSanPham(SanPham sanPham) {
        txtMaSanPham.setText(sanPham.getMa());
        txtTenSanPham.setText(sanPham.getTen());
        dcbmDanhMuc.setSelectedItem(sanPham.getTenDanhMuc());
        dcbmThuongHieu.setSelectedItem(sanPham.getTenThuongHieu());
    }

    public <T> T searchPropertiesById(int id, List<T> list) {
        for (T item : list) {
            if (item instanceof SanPham) {
                if (((SanPham) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof ChatLieu) {
                if (((ChatLieu) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof CoAo) {
                if (((CoAo) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof MauSac) {
                if (((MauSac) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof ChieuDaiTay) {
                if (((ChieuDaiTay) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof XuatXu) {
                if (((XuatXu) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof KichCo) {
                if (((KichCo) item).getId() == id) {
                    return item;
                }
            }
        }
        return null;
    }

    public void fillDataSanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet) {
        cbbSanPham.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdSanPham(), sanPhamService.getAll()));
        cbbChatLieu.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdChatLieu(), chatLieuService.getAll()));
        cbbCoAo.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdCoAo(), coAoService.getAll()));
        cbbMauSac.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdMauSac(), mauSacService.getAll()));
        cbbKichCo.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdKichCo(), kichCoService.getAll()));
        cbbChieuDaiTay.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdChieuDaiTay(), chieuDaiTayService.getAll()));
        cbbXuatXu.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdXuatXu(), xuatXuService.getAll()));
        txtMaCTSP.setText(sanPhamChiTiet.getMa());
        txtMaVach.setText(sanPhamChiTiet.getMaQR());
        txtSoLuong.setText(sanPhamChiTiet.getSoLuong() + "");
        txtGiaBan.setText(sanPhamChiTiet.getGiaBan() + "");
        txtMoTa.setText(sanPhamChiTiet.getMoTa());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdoThuocTinh = new javax.swing.ButtonGroup();
        jDialogThemNhanhSanPham = new javax.swing.JDialog();
        jPanel20 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaSanPhamThemNhanh = new textfield.TextField2();
        txtTenSanPhamThemNhanh = new textfield.TextField2();
        jLabel13 = new javax.swing.JLabel();
        cbbThuongHieuThemNhanh = new app.view.swing.ComboBoxSuggestion();
        jLabel14 = new javax.swing.JLabel();
        cbbDanhMucThemNhanh = new app.view.swing.ComboBoxSuggestion();
        btnDialogThemSanPham = new app.view.swing.ButtonGradient();
        buttonGradient3 = new app.view.swing.ButtonGradient();
        jPanel1 = new javax.swing.JPanel();
        tabbedPaneCustom1 = new app.view.swing.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtMaSanPham = new textfield.TextField2();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenSanPham = new textfield.TextField2();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbbThuongHieu = new app.view.swing.ComboBoxSuggestion();
        btnThemNhanhDanhMuc = new app.view.swing.Button();
        btnThemNhanhThuongHieu = new app.view.swing.Button();
        cbbDanhMuc = new app.view.swing.ComboBoxSuggestion();
        jPanel7 = new javax.swing.JPanel();
        btnThemSanPham = new app.view.swing.ButtonGradient();
        btnSuaSanPham = new app.view.swing.ButtonGradient();
        btnLamMoiSanPham = new app.view.swing.ButtonGradient();
        buttonGradient4 = new app.view.swing.ButtonGradient();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        scrollPaneWin111 = new custome_ui.swing.ScrollPaneWin11();
        tblSanPham = new rojeru_san.complementos.RSTableMetro();
        paginationSanPham = new app.view.swing.Pagination();
        jLabel3 = new javax.swing.JLabel();
        textField23 = new textfield.TextField2();
        jPanel18 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        btnSuaSanPhamChiTiet = new app.view.swing.ButtonGradient();
        btnLamMoiSanPhamChiTiet = new app.view.swing.ButtonGradient();
        btnImportExcel = new app.view.swing.ButtonGradient();
        btnTaiExcel = new app.view.swing.ButtonGradient();
        btnExportExcel = new app.view.swing.ButtonGradient();
        jPanel24 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbbSanPham = new app.view.swing.ComboBoxSuggestion();
        txtSoLuong = new textfield.TextField2();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cbbXuatXu = new app.view.swing.ComboBoxSuggestion();
        jLabel30 = new javax.swing.JLabel();
        txtMaCTSP = new textfield.TextField2();
        jLabel26 = new javax.swing.JLabel();
        cbbKichCo = new app.view.swing.ComboBoxSuggestion();
        jLabel22 = new javax.swing.JLabel();
        cbbMauSac = new app.view.swing.ComboBoxSuggestion();
        btnThemNhanhMauSac = new app.view.swing.Button();
        btnThemNhanhKichCo = new app.view.swing.Button();
        jLabel21 = new javax.swing.JLabel();
        cbbChatLieu = new app.view.swing.ComboBoxSuggestion();
        jLabel24 = new javax.swing.JLabel();
        cbbChieuDaiTay = new app.view.swing.ComboBoxSuggestion();
        jLabel31 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtGiaBan = new textfield.TextField2();
        jLabel23 = new javax.swing.JLabel();
        cbbCoAo = new app.view.swing.ComboBoxSuggestion();
        btnThemNhanhChatLieu = new app.view.swing.Button();
        btnThemNhanhCoAo = new app.view.swing.Button();
        btnThemNhanhChieuDaiTay = new app.view.swing.Button();
        jLabel32 = new javax.swing.JLabel();
        txtMaVach = new textfield.TextField2();
        txtMoTa = new textfield.TextField2();
        btnThemSanPhamChiTiet = new app.view.swing.ButtonGradient();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        scrollPaneWin113 = new custome_ui.swing.ScrollPaneWin11();
        tblSanPhamChiTiet = new rojeru_san.complementos.RSTableMetro();
        textField24 = new textfield.TextField2();
        jLabel6 = new javax.swing.JLabel();
        paginationSanPhamChiTiet = new app.view.swing.Pagination();
        jPanel19 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        txtMaThuocTinh = new textfield.TextField2();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTenThuocTinh = new textfield.TextField2();
        jPanel22 = new javax.swing.JPanel();
        rdoThuongHieu = new radio_button.RadioButtonCustom();
        rdoDanhMuc = new radio_button.RadioButtonCustom();
        rdoChatLieu = new radio_button.RadioButtonCustom();
        rdoMauSac = new radio_button.RadioButtonCustom();
        rdoKichCo = new radio_button.RadioButtonCustom();
        rdoCoAo = new radio_button.RadioButtonCustom();
        rdoChieuDaiTay = new radio_button.RadioButtonCustom();
        jPanel23 = new javax.swing.JPanel();
        btnLamMoiThuocTinh = new app.view.swing.ButtonGradient();
        btnSuaThuocTinh = new app.view.swing.ButtonGradient();
        btnThemThuocTinh = new app.view.swing.ButtonGradient();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        tbl = new custome_ui.swing.ScrollPaneWin11();
        tblThuocTinh = new rojeru_san.complementos.RSTableMetro();
        jLabel9 = new javax.swing.JLabel();
        textField25 = new textfield.TextField2();
        pagination1 = new app.view.swing.Pagination();

        jDialogThemNhanhSanPham.setMinimumSize(new java.awt.Dimension(402, 336));
        jDialogThemNhanhSanPham.setModal(true);
        jDialogThemNhanhSanPham.setSize(new java.awt.Dimension(402, 336));

        jPanel20.setBackground(new java.awt.Color(38, 28, 73));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Mã sản phẩm");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tên sản phẩm");

        txtTenSanPhamThemNhanh.setForeground(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Thương hiệu");

        cbbThuongHieuThemNhanh.setBackground(new java.awt.Color(38, 28, 73));
        cbbThuongHieuThemNhanh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbThuongHieuThemNhanh.setForeground(new java.awt.Color(255, 255, 255));
        cbbThuongHieuThemNhanh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Danh mục");

        cbbDanhMucThemNhanh.setBackground(new java.awt.Color(38, 28, 73));
        cbbDanhMucThemNhanh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbDanhMucThemNhanh.setForeground(new java.awt.Color(255, 255, 255));
        cbbDanhMucThemNhanh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnDialogThemSanPham.setForeground(new java.awt.Color(0, 0, 0));
        btnDialogThemSanPham.setText("Thêm");
        btnDialogThemSanPham.setColor1(new java.awt.Color(255, 255, 255));
        btnDialogThemSanPham.setColor2(new java.awt.Color(102, 102, 102));
        btnDialogThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDialogThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDialogThemSanPhamActionPerformed(evt);
            }
        });

        buttonGradient3.setForeground(new java.awt.Color(0, 0, 0));
        buttonGradient3.setText("Làm mới");
        buttonGradient3.setColor1(new java.awt.Color(255, 255, 255));
        buttonGradient3.setColor2(new java.awt.Color(102, 102, 102));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(btnDialogThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbDanhMucThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbThuongHieuThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTenSanPhamThemNhanh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                .addComponent(txtMaSanPhamThemNhanh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaSanPhamThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenSanPhamThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbThuongHieuThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDanhMucThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDialogThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogThemNhanhSanPhamLayout = new javax.swing.GroupLayout(jDialogThemNhanhSanPham.getContentPane());
        jDialogThemNhanhSanPham.getContentPane().setLayout(jDialogThemNhanhSanPhamLayout);
        jDialogThemNhanhSanPhamLayout.setHorizontalGroup(
            jDialogThemNhanhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogThemNhanhSanPhamLayout.setVerticalGroup(
            jDialogThemNhanhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(38, 28, 73));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPaneCustom1.setBackground(new java.awt.Color(38, 28, 73));
        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(51, 0, 102));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(38, 32, 78));

        jPanel2.setBackground(new java.awt.Color(38, 28, 73));

        jPanel3.setBackground(new java.awt.Color(38, 28, 73));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel9.setBackground(new java.awt.Color(38, 28, 73));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(38, 28, 73));

        txtMaSanPham.setForeground(new java.awt.Color(0, 0, 0));
        txtMaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã sản phẩm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên sản phẩm");

        txtTenSanPham.setForeground(new java.awt.Color(0, 0, 0));
        txtTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(38, 28, 73));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Danh mục");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Thương hiệu");

        cbbThuongHieu.setBackground(new java.awt.Color(38, 28, 73));
        cbbThuongHieu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbThuongHieu.setForeground(new java.awt.Color(255, 255, 255));
        cbbThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThemNhanhDanhMuc.setForeground(new java.awt.Color(0, 0, 0));
        btnThemNhanhDanhMuc.setText("+");
        btnThemNhanhDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhDanhMucActionPerformed(evt);
            }
        });

        btnThemNhanhThuongHieu.setForeground(new java.awt.Color(0, 0, 0));
        btnThemNhanhThuongHieu.setText("+");
        btnThemNhanhThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhThuongHieuActionPerformed(evt);
            }
        });

        cbbDanhMuc.setBackground(new java.awt.Color(38, 28, 73));
        cbbDanhMuc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        cbbDanhMuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemNhanhThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(35, 35, 35)
                        .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemNhanhDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        jPanel7.setBackground(new java.awt.Color(38, 28, 73));

        btnThemSanPham.setForeground(new java.awt.Color(0, 0, 0));
        btnThemSanPham.setText("Thêm");
        btnThemSanPham.setColor1(new java.awt.Color(255, 255, 255));
        btnThemSanPham.setColor2(new java.awt.Color(102, 102, 102));
        btnThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnSuaSanPham.setForeground(new java.awt.Color(0, 0, 0));
        btnSuaSanPham.setText("Sửa");
        btnSuaSanPham.setColor1(new java.awt.Color(255, 255, 255));
        btnSuaSanPham.setColor2(new java.awt.Color(102, 102, 102));
        btnSuaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnLamMoiSanPham.setForeground(new java.awt.Color(0, 0, 0));
        btnLamMoiSanPham.setText("Làm mới");
        btnLamMoiSanPham.setColor1(new java.awt.Color(255, 255, 255));
        btnLamMoiSanPham.setColor2(new java.awt.Color(102, 102, 102));
        btnLamMoiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        buttonGradient4.setForeground(new java.awt.Color(0, 0, 0));
        buttonGradient4.setText("Chi tiết sản phẩm");
        buttonGradient4.setColor1(new java.awt.Color(255, 255, 255));
        buttonGradient4.setColor2(new java.awt.Color(102, 102, 102));
        buttonGradient4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLamMoiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(209, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(38, 28, 73));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel8.setBackground(new java.awt.Color(38, 28, 73));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Thương hiệu", "Danh mục"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblSanPham.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblSanPham.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblSanPham.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblSanPham.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblSanPham.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblSanPham.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPham.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPham.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPham.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPham.setRowHeight(40);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        scrollPaneWin111.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblSanPham.getColumnModel().getColumn(3).setResizable(false);
            tblSanPham.getColumnModel().getColumn(4).setResizable(false);
        }

        paginationSanPham.setBackground(new java.awt.Color(38, 28, 73));
        paginationSanPham.setForeground(new java.awt.Color(38, 28, 73));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 1431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(637, 637, 637)
                .addComponent(paginationSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paginationSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tìm kiếm");

        textField23.setForeground(new java.awt.Color(0, 0, 0));
        textField23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1441, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(textField23, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Sản phẩm", jPanel2);

        jPanel18.setBackground(new java.awt.Color(38, 28, 73));

        jPanel14.setBackground(new java.awt.Color(38, 28, 73));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel15.setBackground(new java.awt.Color(38, 28, 73));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSuaSanPhamChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        btnSuaSanPhamChiTiet.setText("Cập nhật");
        btnSuaSanPhamChiTiet.setColor1(new java.awt.Color(255, 255, 255));
        btnSuaSanPhamChiTiet.setColor2(new java.awt.Color(102, 102, 102));
        btnSuaSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamChiTietActionPerformed(evt);
            }
        });

        btnLamMoiSanPhamChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        btnLamMoiSanPhamChiTiet.setText("Làm mới");
        btnLamMoiSanPhamChiTiet.setColor1(new java.awt.Color(255, 255, 255));
        btnLamMoiSanPhamChiTiet.setColor2(new java.awt.Color(102, 102, 102));
        btnLamMoiSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSanPhamChiTietActionPerformed(evt);
            }
        });

        btnImportExcel.setForeground(new java.awt.Color(0, 0, 0));
        btnImportExcel.setText("Import Excel");
        btnImportExcel.setColor1(new java.awt.Color(255, 255, 255));
        btnImportExcel.setColor2(new java.awt.Color(102, 102, 102));
        btnImportExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        btnTaiExcel.setForeground(new java.awt.Color(0, 0, 0));
        btnTaiExcel.setText("Tải Excel");
        btnTaiExcel.setColor1(new java.awt.Color(255, 255, 255));
        btnTaiExcel.setColor2(new java.awt.Color(102, 102, 102));
        btnTaiExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnExportExcel.setForeground(new java.awt.Color(0, 0, 0));
        btnExportExcel.setText("Export Excel");
        btnExportExcel.setColor1(new java.awt.Color(255, 255, 255));
        btnExportExcel.setColor2(new java.awt.Color(102, 102, 102));
        btnExportExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel24.setBackground(new java.awt.Color(38, 28, 73));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tên sản phẩm");

        cbbSanPham.setBackground(new java.awt.Color(204, 204, 204));
        cbbSanPham.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbSanPham.setForeground(new java.awt.Color(255, 255, 255));
        cbbSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoLuong.setForeground(new java.awt.Color(0, 0, 0));
        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Số lượng");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Xuất xứ");

        cbbXuatXu.setBackground(new java.awt.Color(204, 204, 204));
        cbbXuatXu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbXuatXu.setForeground(new java.awt.Color(255, 255, 255));
        cbbXuatXu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Mã SPCT");

        txtMaCTSP.setForeground(new java.awt.Color(0, 0, 0));
        txtMaCTSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaCTSP.setName(""); // NOI18N
        txtMaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCTSPActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Kích cỡ");

        cbbKichCo.setBackground(new java.awt.Color(204, 204, 204));
        cbbKichCo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbKichCo.setForeground(new java.awt.Color(255, 255, 255));
        cbbKichCo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Màu sắc");

        cbbMauSac.setBackground(new java.awt.Color(204, 204, 204));
        cbbMauSac.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbMauSac.setForeground(new java.awt.Color(255, 255, 255));
        cbbMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThemNhanhMauSac.setForeground(new java.awt.Color(0, 0, 0));
        btnThemNhanhMauSac.setText("+");
        btnThemNhanhMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhMauSacActionPerformed(evt);
            }
        });

        btnThemNhanhKichCo.setForeground(new java.awt.Color(0, 0, 0));
        btnThemNhanhKichCo.setText("+");
        btnThemNhanhKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhKichCoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Chất liệu");

        cbbChatLieu.setBackground(new java.awt.Color(204, 204, 204));
        cbbChatLieu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbChatLieu.setForeground(new java.awt.Color(255, 255, 255));
        cbbChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Chiều dài tay");

        cbbChieuDaiTay.setBackground(new java.awt.Color(204, 204, 204));
        cbbChieuDaiTay.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbChieuDaiTay.setForeground(new java.awt.Color(255, 255, 255));
        cbbChieuDaiTay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Mã vạch");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Giá bán");

        txtGiaBan.setForeground(new java.awt.Color(0, 0, 0));
        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cổ áo");

        cbbCoAo.setBackground(new java.awt.Color(204, 204, 204));
        cbbCoAo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbCoAo.setForeground(new java.awt.Color(255, 255, 255));
        cbbCoAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThemNhanhChatLieu.setForeground(new java.awt.Color(0, 0, 0));
        btnThemNhanhChatLieu.setText("+");
        btnThemNhanhChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhChatLieuActionPerformed(evt);
            }
        });

        btnThemNhanhCoAo.setForeground(new java.awt.Color(0, 0, 0));
        btnThemNhanhCoAo.setText("+");
        btnThemNhanhCoAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhCoAoActionPerformed(evt);
            }
        });

        btnThemNhanhChieuDaiTay.setForeground(new java.awt.Color(0, 0, 0));
        btnThemNhanhChieuDaiTay.setText("+");
        btnThemNhanhChieuDaiTay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhChieuDaiTayActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Mô tả");

        txtMaVach.setForeground(new java.awt.Color(0, 0, 0));
        txtMaVach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaVach.setName(""); // NOI18N
        txtMaVach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaVachActionPerformed(evt);
            }
        });

        txtMoTa.setForeground(new java.awt.Color(0, 0, 0));
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMoTa.setName(""); // NOI18N
        txtMoTa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel20)
                            .addComponent(jLabel29))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemNhanhCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(cbbChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThemNhanhChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemNhanhMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThemNhanhCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemNhanhChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        btnThemSanPhamChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        btnThemSanPhamChiTiet.setText("Thêm");
        btnThemSanPhamChiTiet.setColor1(new java.awt.Color(255, 255, 255));
        btnThemSanPhamChiTiet.setColor2(new java.awt.Color(102, 102, 102));
        btnThemSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(btnThemSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoiSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaiExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 239, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(38, 28, 73));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel17.setBackground(new java.awt.Color(38, 28, 73));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SPCT", "Tên sản phẩm", "Số lượng", "Giá bán", "Màu sắc", "Kích cỡ", "Chất liệu", "Cổ áo", "Chiều dài tay", "Xuất xứ", "Mã vạch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamChiTiet.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblSanPhamChiTiet.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblSanPhamChiTiet.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblSanPhamChiTiet.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblSanPhamChiTiet.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblSanPhamChiTiet.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblSanPhamChiTiet.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPhamChiTiet.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPhamChiTiet.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPhamChiTiet.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPhamChiTiet.setRowHeight(40);
        tblSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChiTietMouseClicked(evt);
            }
        });
        scrollPaneWin113.setViewportView(tblSanPhamChiTiet);
        if (tblSanPhamChiTiet.getColumnModel().getColumnCount() > 0) {
            tblSanPhamChiTiet.getColumnModel().getColumn(0).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(1).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(2).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(3).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(4).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(5).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(6).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(7).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(8).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(9).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(10).setResizable(false);
        }

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin113, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );

        textField24.setForeground(new java.awt.Color(0, 0, 0));
        textField24.setText("textField21");
        textField24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tìm kiếm");

        paginationSanPhamChiTiet.setBackground(new java.awt.Color(38, 28, 73));
        paginationSanPhamChiTiet.setForeground(new java.awt.Color(38, 28, 73));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(textField24, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paginationSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(632, 632, 632))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paginationSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 49, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Sản phẩm chi tiết", jPanel18);

        jPanel19.setBackground(new java.awt.Color(38, 28, 73));

        jPanel10.setBackground(new java.awt.Color(38, 28, 73));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(38, 28, 73));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel21.setBackground(new java.awt.Color(38, 28, 73));

        txtMaThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        txtMaThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mã thuộc tính");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tên thuộc tính");

        txtTenThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        txtTenThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(38, 28, 73));

        rdoThuongHieu.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoThuongHieu);
        rdoThuongHieu.setForeground(new java.awt.Color(255, 255, 255));
        rdoThuongHieu.setText("Thương hiệu");
        rdoThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoDanhMuc.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoDanhMuc);
        rdoDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        rdoDanhMuc.setText("Danh mục");
        rdoDanhMuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoChatLieu.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoChatLieu);
        rdoChatLieu.setForeground(new java.awt.Color(255, 255, 255));
        rdoChatLieu.setText("Chất liệu");
        rdoChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoMauSac.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoMauSac);
        rdoMauSac.setForeground(new java.awt.Color(255, 255, 255));
        rdoMauSac.setText("Màu sắc");
        rdoMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoKichCo.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoKichCo);
        rdoKichCo.setForeground(new java.awt.Color(255, 255, 255));
        rdoKichCo.setText("Kích cỡ");
        rdoKichCo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoCoAo.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoCoAo);
        rdoCoAo.setForeground(new java.awt.Color(255, 255, 255));
        rdoCoAo.setText("Cổ áo");
        rdoCoAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoChieuDaiTay.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoChieuDaiTay);
        rdoChieuDaiTay.setForeground(new java.awt.Color(255, 255, 255));
        rdoChieuDaiTay.setText("Chiều dài tay");
        rdoChieuDaiTay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(rdoChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(rdoMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdoChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(38, 28, 73));

        btnLamMoiThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        btnLamMoiThuocTinh.setText("Làm mới");
        btnLamMoiThuocTinh.setColor1(new java.awt.Color(255, 255, 255));
        btnLamMoiThuocTinh.setColor2(new java.awt.Color(102, 102, 102));
        btnLamMoiThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        btnSuaThuocTinh.setText("Cập nhật");
        btnSuaThuocTinh.setColor1(new java.awt.Color(255, 255, 255));
        btnSuaThuocTinh.setColor2(new java.awt.Color(102, 102, 102));
        btnSuaThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnThemThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.setColor1(new java.awt.Color(255, 255, 255));
        btnThemThuocTinh.setColor2(new java.awt.Color(102, 102, 102));
        btnThemThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoiThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(38, 28, 73));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel13.setBackground(new java.awt.Color(38, 28, 73));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblThuocTinh.setAutoCreateRowSorter(true);
        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã thuộc tính", "Tên thuộc tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuocTinh.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblThuocTinh.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblThuocTinh.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblThuocTinh.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblThuocTinh.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblThuocTinh.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblThuocTinh.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblThuocTinh.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblThuocTinh.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblThuocTinh.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblThuocTinh.setRowHeight(40);
        tbl.setViewportView(tblThuocTinh);
        if (tblThuocTinh.getColumnModel().getColumnCount() > 0) {
            tblThuocTinh.getColumnModel().getColumn(0).setResizable(false);
            tblThuocTinh.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tbl, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tìm kiếm");

        textField25.setForeground(new java.awt.Color(0, 0, 0));
        textField25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(textField25, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap(12, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Thuộc tính", jPanel19);

        jPanel1.add(tabbedPaneCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 1500, 910));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1570, 1040));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiThuocTinhActionPerformed
        txtMaThuocTinh.setText("");
    }//GEN-LAST:event_btnLamMoiThuocTinhActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
        if (rdoThuongHieu.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm thương hiệu!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, thuongHieuService.update(addAndUpdateThuongHieu()));
                showDataProperties();
            }
        } else if (rdoDanhMuc.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm danh mục!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, danhMucService.add(addAndUpdateDanhMuc()));
                showDataProperties();
            }
        } else if (rdoChatLieu.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm chất liệu!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, chatLieuService.add(addAndUpdateChatLieu()));
                showDataProperties();
            }
        } else if (rdoMauSac.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm màu sắc!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, mauSacService.add(addAndUpdateMauSac()));
                showDataProperties();
            }
        } else if (rdoKichCo.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm kích cỡ!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, kichCoService.add(addAndUpdateKichCo()));
                showDataProperties();
            }
        } else if (rdoCoAo.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm cổ áo!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, coAoService.add(addAndUpdateCoAo()));
                showDataProperties();
            }
        } else if (rdoChieuDaiTay.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm chiều dài tay!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, chieuDaiTayService.add(addAndUpdateChieuDaiTay()));
                showDataProperties();
            }
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        if (rdoThuongHieu.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm thương hiệu!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, thuongHieuService.add(addAndUpdateThuongHieu()));
                showDataProperties();
            }
        } else if (rdoDanhMuc.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm danh mục!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, danhMucService.add(addAndUpdateDanhMuc()));
                showDataProperties();
            }
        } else if (rdoChatLieu.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm chất liệu!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, chatLieuService.add(addAndUpdateChatLieu()));
                showDataProperties();
            }
        } else if (rdoMauSac.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm màu sắc!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, mauSacService.add(addAndUpdateMauSac()));
                showDataProperties();
            }
        } else if (rdoKichCo.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm kích cỡ!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, kichCoService.add(addAndUpdateKichCo()));
                showDataProperties();
            }
        } else if (rdoCoAo.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm cổ áo!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, coAoService.add(addAndUpdateCoAo()));
                showDataProperties();
            }
        } else if (rdoChieuDaiTay.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm chiều dài tay!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, chieuDaiTayService.add(addAndUpdateChieuDaiTay()));
                showDataProperties();
            }
        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow();
        int limit = 10;
        int offset = (currentPage - 1) * limit + index;
        if (index >= 0) {
            SanPham selectedSanPham = sanPhamService.getAll(offset, 1).get(0);
            fillDataSanPham(selectedSanPham);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemNhanhChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhChatLieuActionPerformed
        JDialogThemChatLieu jDialogThemChatLieu = new JDialogThemChatLieu(parentFrame, true);
        showDataProperties();
        jDialogThemChatLieu.setVisible(true);
    }//GEN-LAST:event_btnThemNhanhChatLieuActionPerformed

    private void btnThemNhanhCoAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhCoAoActionPerformed
        JDialogThemCoAo jDialogThemCoAo = new JDialogThemCoAo(parentFrame, true);
        jDialogThemCoAo.setVisible(true);
    }//GEN-LAST:event_btnThemNhanhCoAoActionPerformed

    private void btnThemNhanhMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhMauSacActionPerformed
        JDialogThemMauSac jDialogThemMauSac = new JDialogThemMauSac(parentFrame, true);
        jDialogThemMauSac.setVisible(true);
    }//GEN-LAST:event_btnThemNhanhMauSacActionPerformed

    private void btnThemNhanhKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhKichCoActionPerformed
        JDialogThemKichCo jDialogThemKichCo = new JDialogThemKichCo(parentFrame, true);
        jDialogThemKichCo.setVisible(true);
    }//GEN-LAST:event_btnThemNhanhKichCoActionPerformed

    private void btnThemNhanhChieuDaiTayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhChieuDaiTayActionPerformed
        JDialogThemChieuDaiTay jDialogThemChieuDaiTay = new JDialogThemChieuDaiTay(parentFrame, true);
        jDialogThemChieuDaiTay.setVisible(true);
    }//GEN-LAST:event_btnThemNhanhChieuDaiTayActionPerformed

    private void btnThemNhanhThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhThuongHieuActionPerformed
        JDialogThemThuongHieu jDialogThemThuongHieu = new JDialogThemThuongHieu(parentFrame, true);
        jDialogThemThuongHieu.setVisible(true);
    }//GEN-LAST:event_btnThemNhanhThuongHieuActionPerformed

    private void btnThemNhanhDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhDanhMucActionPerformed
        JDialogThemDanhMuc jDialogThemDanhMuc = new JDialogThemDanhMuc(parentFrame, true);
        jDialogThemDanhMuc.setVisible(true);
    }//GEN-LAST:event_btnThemNhanhDanhMucActionPerformed

    private void btnThemSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamChiTietActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm sản phẩm!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, sanPhamChiTietService.add(addSanPhamChiTiet()));
        }
        SanPham sanPham = (SanPham) cbbSanPham.getSelectedItem();
        showDataSanPhamChiTiet(sanPham.getMa());
        showDataSanPham();
    }//GEN-LAST:event_btnThemSanPhamChiTietActionPerformed

    private void tblSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTietMouseClicked
//        int index = tblSanPhamChiTiet.getSelectedRow();
//        if (index != -1) {
//            Object firstColumnValue = tblSanPhamChiTiet.getValueAt(index, 0);
//            JDialogSanPhamChiTiet jDialogSanPhamChiTiet = new JDialogSanPhamChiTiet(parentFrame, firstColumnValue);
//            jDialogSanPhamChiTiet.setVisible(true);
//            SanPhamChiTiet selectedSanPhamChiTiet = jDialogSanPhamChiTiet.getSelectedSanPhamChiTiet();
//            if (selectedSanPhamChiTiet != null) {
//                fillDataSanPhamChiTiet(selectedSanPhamChiTiet);
//            }
//        }

//        int index = tblSanPhamChiTiet.getSelectedRow();
//        SanPham sanPham = (SanPham) cbbSanPham.getSelectedItem();
//        fillDataSanPhamChiTiet(sanPhamChiTietService.getAllByIdSanPham(sanPham.getMa()).get(index));
//int index = tblSanPham.getSelectedRow();
//        int limit = 10;
//        int offset = (currentPage - 1) * limit + index;
//        if (index >= 0) {
//            SanPham selectedSanPham = sanPhamService.getAll(offset, 1).get(0);
//            fillDataSanPham(selectedSanPham);
//        }
        int index = tblSanPhamChiTiet.getSelectedRow();
        int limit = 10;
        int offset = (currentPage - 1) * limit + index;
        if (index >= 0) {
            SanPham selectedSanPham = (SanPham) cbbSanPham.getSelectedItem();
            SanPhamChiTiet selectedSanPhamChiTiet = (SanPhamChiTiet) sanPhamChiTietService.getAllByIdSanPham(selectedSanPham.getMa(), offset, 1).get(0);
            fillDataSanPhamChiTiet(selectedSanPhamChiTiet);
        }
    }//GEN-LAST:event_tblSanPhamChiTietMouseClicked

    private void btnSuaSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamChiTietActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật!", "Cập nhật sản phẩm!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, sanPhamChiTietService.update(addSanPhamChiTiet()));
        }
    }//GEN-LAST:event_btnSuaSanPhamChiTietActionPerformed

    private void btnLamMoiSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSanPhamChiTietActionPerformed
        txtMaCTSP.setText("");
        txtMaVach.setText("");
        txtSoLuong.setText("");
        txtGiaBan.setText("");
        txtMoTa.setText("");
    }//GEN-LAST:event_btnLamMoiSanPhamChiTietActionPerformed

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed

        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;

        String defaultCurrentDirectoryPath = "‪C:\\Users\\Admin\\Downloads\\test.xlsx";

        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelBIS);

                XSSFWorkbook excelJTableImport = new XSSFWorkbook();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnImportExcelActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm sản phẩm!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, sanPhamService.add(addAndUpdateSanPham()));
            loadDataSanPhamPage(1);
            cbbSanPham.setModel(new DefaultComboBoxModel<>(sanPhamService.getAll().toArray()));
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnDialogThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDialogThemSanPhamActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm sản phẩm!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, sanPhamService.add(addNhanhSanPham()));
//            loadDataSanPhamPage(1);
            cbbSanPham.setModel(new DefaultComboBoxModel<>(sanPhamService.getAll().toArray()));
        }
        this.setVisible(false);
    }//GEN-LAST:event_btnDialogThemSanPhamActionPerformed

    private void txtMaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCTSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaCTSPActionPerformed

    private void txtMaVachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaVachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaVachActionPerformed

    private void txtMoTaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.view.swing.ButtonGradient btnDialogThemSanPham;
    private app.view.swing.ButtonGradient btnExportExcel;
    private app.view.swing.ButtonGradient btnImportExcel;
    private app.view.swing.ButtonGradient btnLamMoiSanPham;
    private app.view.swing.ButtonGradient btnLamMoiSanPhamChiTiet;
    private app.view.swing.ButtonGradient btnLamMoiThuocTinh;
    private app.view.swing.ButtonGradient btnSuaSanPham;
    private app.view.swing.ButtonGradient btnSuaSanPhamChiTiet;
    private app.view.swing.ButtonGradient btnSuaThuocTinh;
    private app.view.swing.ButtonGradient btnTaiExcel;
    private app.view.swing.Button btnThemNhanhChatLieu;
    private app.view.swing.Button btnThemNhanhChieuDaiTay;
    private app.view.swing.Button btnThemNhanhCoAo;
    private app.view.swing.Button btnThemNhanhDanhMuc;
    private app.view.swing.Button btnThemNhanhKichCo;
    private app.view.swing.Button btnThemNhanhMauSac;
    private app.view.swing.Button btnThemNhanhThuongHieu;
    private app.view.swing.ButtonGradient btnThemSanPham;
    private app.view.swing.ButtonGradient btnThemSanPhamChiTiet;
    private app.view.swing.ButtonGradient btnThemThuocTinh;
    private app.view.swing.ButtonGradient buttonGradient3;
    private app.view.swing.ButtonGradient buttonGradient4;
    private app.view.swing.ComboBoxSuggestion cbbChatLieu;
    private app.view.swing.ComboBoxSuggestion cbbChieuDaiTay;
    private app.view.swing.ComboBoxSuggestion cbbCoAo;
    private app.view.swing.ComboBoxSuggestion cbbDanhMuc;
    private app.view.swing.ComboBoxSuggestion cbbDanhMucThemNhanh;
    private app.view.swing.ComboBoxSuggestion cbbKichCo;
    private app.view.swing.ComboBoxSuggestion cbbMauSac;
    private app.view.swing.ComboBoxSuggestion cbbSanPham;
    private app.view.swing.ComboBoxSuggestion cbbThuongHieu;
    private app.view.swing.ComboBoxSuggestion cbbThuongHieuThemNhanh;
    private app.view.swing.ComboBoxSuggestion cbbXuatXu;
    private javax.swing.JDialog jDialogThemNhanhSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private app.view.swing.Pagination pagination1;
    private app.view.swing.Pagination paginationSanPham;
    private app.view.swing.Pagination paginationSanPhamChiTiet;
    private radio_button.RadioButtonCustom rdoChatLieu;
    private radio_button.RadioButtonCustom rdoChieuDaiTay;
    private radio_button.RadioButtonCustom rdoCoAo;
    private radio_button.RadioButtonCustom rdoDanhMuc;
    private radio_button.RadioButtonCustom rdoKichCo;
    private radio_button.RadioButtonCustom rdoMauSac;
    private javax.swing.ButtonGroup rdoThuocTinh;
    private radio_button.RadioButtonCustom rdoThuongHieu;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin111;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin113;
    private app.view.swing.TabbedPaneCustom tabbedPaneCustom1;
    private custome_ui.swing.ScrollPaneWin11 tbl;
    private rojeru_san.complementos.RSTableMetro tblSanPham;
    private rojeru_san.complementos.RSTableMetro tblSanPhamChiTiet;
    private rojeru_san.complementos.RSTableMetro tblThuocTinh;
    private textfield.TextField2 textField23;
    private textfield.TextField2 textField24;
    private textfield.TextField2 textField25;
    private textfield.TextField2 txtGiaBan;
    private textfield.TextField2 txtMaCTSP;
    private textfield.TextField2 txtMaSanPham;
    private textfield.TextField2 txtMaSanPhamThemNhanh;
    private textfield.TextField2 txtMaThuocTinh;
    private textfield.TextField2 txtMaVach;
    private textfield.TextField2 txtMoTa;
    private textfield.TextField2 txtSoLuong;
    private textfield.TextField2 txtTenSanPham;
    private textfield.TextField2 txtTenSanPhamThemNhanh;
    private textfield.TextField2 txtTenThuocTinh;
    // End of variables declaration//GEN-END:variables

}
