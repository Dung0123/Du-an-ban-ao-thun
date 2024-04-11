/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import util.Uhelper;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Voucher;
import modelViews.QlVoucher;
import service.voucherservice.IVoucherServise;
import service.voucherservice.VoucherService;
import util.DBConnect;

/**
 *
 * @author bcuon
 */
public class ViewKhuyenMai extends javax.swing.JPanel {

    private DefaultTableModel model = new DefaultTableModel();
    private IVoucherServise iVoucherServise = new VoucherService();

    public ViewKhuyenMai() {
        initComponents();
        loadDataVoucher(iVoucherServise.getAllVoucherTrangThai012());

        txtGiatrigiam.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                handleFocusGained();
            }

            @Override
            public void focusLost(FocusEvent e) {
                handleFocusLost();
            }
        });

        cboLoaiVoucher.addActionListener(e -> handleComboBoxSelection());

    }

    private void handleComboBoxSelection() {
        String selectedOption = (String) cboLoaiVoucher.getSelectedItem();
        if ("Theo phần trăm".equals(selectedOption)) {
            txtGiatrigiam.setText("");
            txtGiatrigiam.setToolTipText("Nhập số phần trăm (%)");
        } else if ("Theo số tiền".equals(selectedOption)) {
            txtGiatrigiam.setText("");
            txtGiatrigiam.setToolTipText("Nhập số tiền (VND)");
        }
        handleFocusLost();
    }

    private void handleFocusGained() {
        if (txtGiatrigiam.getText().equals("Nhập số phần trăm (%)")
                || txtGiatrigiam.getText().equals("Nhập số tiền (VND)")) {
            txtGiatrigiam.setText("");
            txtGiatrigiam.setForeground(Color.BLACK);
        }
    }

    private void handleFocusLost() {
        if (txtGiatrigiam.getText().isEmpty()) {
            String selectedOption = (String) cboLoaiVoucher.getSelectedItem();
            if ("Theo phần trăm".equals(selectedOption)) {
                txtGiatrigiam.setText("Nhập số phần trăm (%)");
            } else if ("Theo số tiền".equals(selectedOption)) {
                txtGiatrigiam.setText("Nhập số tiền (VND)");
            }
            txtGiatrigiam.setForeground(Color.GRAY);
        }
    }

    private void loadDataVoucher(ArrayList<QlVoucher> list) {

        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }

        model = (DefaultTableModel) tblVoucher.getModel();
        model.setRowCount(0);

        for (int i = 0; i < list.size(); i++) {
            String loaiGiamGiaOption = (String) cboLoaiVoucher.getSelectedItem();
            boolean isLoaiGiamGia = "Theo phần trăm".equals(loaiGiamGiaOption);

            int giaTriGiam = list.get(i).getGiaTriGiam();
            String formattedDiscount;

            if (isLoaiGiamGia) {
                formattedDiscount = giaTriGiam + "%";
            } else {
                formattedDiscount = giaTriGiam + " VND";
            }

            model.addRow(new Object[]{
                list.get(i).getMa(),
                list.get(i).getTen(),
                sdf.format(list.get(i).getNgayBatDau()),
                sdf.format(list.get(i).getNgayKetThuc()),
                list.get(i).isLoaiGiamGia() ? "Theo phần trăm" : "Theo số tiền",
                list.get(i).getGiaTriGiam(),
                list.get(i).getSoLuong(),
                list.get(i).getGiaTriDHTT(),
                list.get(i).hienThiTrangThai()
            });
        }
    }

    int index;

    private void showDetailVoucher(ArrayList<QlVoucher> list) {
        index = tblVoucher.getSelectedRow();

        if (index != -1 && index < list.size()) {
            QlVoucher qLVoucher = list.get(index);
            txtTenVoucher.setText(qLVoucher.getTen());
            txtMaVoucher.setText(qLVoucher.getMa());
            jdNgayBd.setDate(qLVoucher.getNgayBatDau());
            jdNgayKt.setDate(qLVoucher.getNgayKetThuc());
            txtGiatriDHTT.setText(qLVoucher.getGiaTriDHTT().toString());
            txtSoluong.setText(qLVoucher.getSoLuong() + "");
            int giaTriGiam = qLVoucher.getGiaTriGiam();
            String displayGiaTriGiam;
            if (qLVoucher.isLoaiGiamGia()) {
                displayGiaTriGiam = giaTriGiam + "%";
            } else {
                displayGiaTriGiam = giaTriGiam + " VND";
            }
            txtGiatrigiam.setText(displayGiaTriGiam);

            if (qLVoucher.isLoaiGiamGia()) {
                cboLoaiVoucher.setSelectedItem("Theo phần trăm");
            } else {
                cboLoaiVoucher.setSelectedItem("Theo số tiền");
            }
        }
    }

    private QlVoucher getFormVoucher() {

        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ten = txtTenVoucher.getText();
        String ma = txtMaVoucher.getText();
        Date ngayBatDau = jdNgayBd.getDate();
        Date ngayKetThuc = jdNgayKt.getDate();
        int soLuong = Integer.parseInt(txtSoluong.getText());
        String loaiGiamGiaOption = (String) cboLoaiVoucher.getSelectedItem();

        // Lấy giá trị từ txtgiatriGiam
        String inputText = txtGiatrigiam.getText().trim();
        // Loại bỏ phần chữ 
        inputText = inputText.replaceAll("[^0-9]", "");
        boolean isLoaiGiamGia = "Theo phần trăm".equals(loaiGiamGiaOption);

        // Kiểm tra giá trị giảm giá không được lớn hơn 50% giá trị đơn hàng tối thiểu
        BigDecimal giaTriGiamToiThieu = new BigDecimal("0.5");
        BigDecimal giaTriDonHangtt = new BigDecimal(txtGiatriDHTT.getText());
        int giaTriGiam = Integer.parseInt(inputText);

        // Tính giá trị giảm tối đa (50% của giá trị đơn hàng tối thiểu)
        BigDecimal giaTriGiamToiDa = giaTriDonHangtt.multiply(giaTriGiamToiThieu).divide(new BigDecimal("100"));

        if (isLoaiGiamGia) { // Giảm theo phần trăm
            if (new BigDecimal(giaTriGiam).compareTo(giaTriGiamToiDa) > 0) {
                JOptionPane.showMessageDialog(this, "Giá trị giảm giá không được lớn hơn 50% giá trị đơn hàng tối thiểu");
                return null;
            }
        } else { // Giảm theo số tiền
            if (giaTriGiam > giaTriDonHangtt.multiply(giaTriGiamToiThieu).intValue()) {
                JOptionPane.showMessageDialog(this, "Giá trị giảm giá không được lớn hơn 50% giá trị đơn hàng tối thiểu");
                return null;
            }
        }

        int trangThai;
        if (ngayBatDau.after(dayCurrent)) {
            trangThai = 2;
        } else if ((ngayBatDau.equals(dayCurrent) || ngayBatDau.before(dayCurrent))
                && (ngayKetThuc.equals(dayCurrent) || ngayKetThuc.after(dayCurrent))) {
            trangThai = 0;
        } else {
            trangThai = 1;
        }

        QlVoucher qLVoucher = new QlVoucher(ma, ten, ngayBatDau, ngayKetThuc, isLoaiGiamGia, giaTriGiam, soLuong, giaTriDonHangtt, trangThai);
        return qLVoucher;
    }

    private void loadDataVoucherDeleted(ArrayList<QlVoucher> list) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        model = (DefaultTableModel) tblVoucherDaxoa.getModel();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                list.get(i).getMa(),
                list.get(i).getTen(),
                sdf.format(list.get(i).getNgayBatDau()),
                sdf.format(list.get(i).getNgayKetThuc()),
                list.get(i).isLoaiGiamGia() == true ? "Theo phần trăm" : "Theo số tiền",
                list.get(i).getGiaTriGiam(),
                list.get(i).getSoLuong(),
                list.get(i).getGiaTriDHTT(),
                list.get(i).hienThiTrangThai()
            });
        }
        index = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        tabVoucher = new app.view.swing.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jdNgayBd = new com.toedter.calendar.JDateChooser();
        jdNgayKt = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        btnClear = new app.view.swing.ButtonGradient();
        btnXoa = new app.view.swing.ButtonGradient();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenVoucher = new textfield.TextField2();
        txtSoluong = new textfield.TextField2();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboLoaiVoucher = new app.view.swing.ComboBoxSuggestion();
        jLabel9 = new javax.swing.JLabel();
        txtMaVoucher = new textfield.TextField2();
        jLabel10 = new javax.swing.JLabel();
        btnTatCaVoucher = new app.view.swing.ButtonGradient();
        btnSua = new app.view.swing.ButtonGradient();
        txtGiatrigiam = new textfield.TextField2();
        jLabel11 = new javax.swing.JLabel();
        txtGiatriDHTT = new textfield.TextField2();
        btnThem = new app.view.swing.ButtonGradient();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        scrollPaneWin111 = new custome_ui.swing.ScrollPaneWin11();
        scrollPaneWin112 = new custome_ui.swing.ScrollPaneWin11();
        scrollPaneWin114 = new custome_ui.swing.ScrollPaneWin11();
        scrollPaneWin115 = new custome_ui.swing.ScrollPaneWin11();
        tblVoucher = new rojeru_san.complementos.RSTableMetro();
        txtTimkiem = new textfield.TextField2();
        btnTimkiem = new app.view.swing.ButtonGradient();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cboloctheotrangthai = new app.view.swing.ComboBoxSuggestion();
        jPanel18 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        scrollPaneWin113 = new custome_ui.swing.ScrollPaneWin11();
        tblVoucherDaxoa = new rojeru_san.complementos.RSTableMetro();
        txtTimkiemVCxoa = new textfield.TextField2();
        btnTimkiemVoucherDaxoa = new app.view.swing.ButtonGradient();
        btnTatcaVoucherDaxoa = new app.view.swing.ButtonGradient();
        btnKhoiphuc = new app.view.swing.ButtonGradient();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(38, 28, 73));

        jPanel5.setBackground(new java.awt.Color(38, 28, 73));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabVoucher.setBackground(new java.awt.Color(38, 28, 73));
        tabVoucher.setForeground(new java.awt.Color(255, 255, 255));
        tabVoucher.setSelectedColor(new java.awt.Color(51, 0, 102));
        tabVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabVoucherMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(38, 28, 73));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(38, 28, 73));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin Voucher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel9.setBackground(new java.awt.Color(38, 28, 73));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(38, 28, 73));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdNgayKt, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(jdNgayBd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jdNgayBd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jdNgayKt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(38, 28, 73));

        btnClear.setForeground(new java.awt.Color(0, 0, 0));
        btnClear.setText("Làm mới");
        btnClear.setColor1(new java.awt.Color(255, 255, 255));
        btnClear.setColor2(new java.awt.Color(102, 102, 102));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnXoa.setForeground(new java.awt.Color(0, 0, 0));
        btnXoa.setText("Xóa");
        btnXoa.setColor1(new java.awt.Color(255, 255, 255));
        btnXoa.setColor2(new java.awt.Color(102, 102, 102));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã Voucher");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Giá trị đơn hàng tối thiểu");

        txtTenVoucher.setForeground(new java.awt.Color(0, 0, 0));
        txtTenVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoluong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ngày bắt đầu");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ngày kết thúc");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tên Voucher");

        cboLoaiVoucher.setBackground(new java.awt.Color(38, 28, 73));
        cboLoaiVoucher.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cboLoaiVoucher.setForeground(new java.awt.Color(255, 255, 255));
        cboLoaiVoucher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Theo phần trăm", "Theo số tiền" }));
        cboLoaiVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLoaiVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboLoaiVoucherMouseReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Loại Voucher");

        txtMaVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Số lượng");

        btnTatCaVoucher.setForeground(new java.awt.Color(0, 0, 0));
        btnTatCaVoucher.setText("Tất cả");
        btnTatCaVoucher.setColor1(new java.awt.Color(255, 255, 255));
        btnTatCaVoucher.setColor2(new java.awt.Color(102, 102, 102));
        btnTatCaVoucher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTatCaVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatCaVoucherActionPerformed(evt);
            }
        });

        btnSua.setForeground(new java.awt.Color(0, 0, 0));
        btnSua.setText("Sửa");
        btnSua.setColor1(new java.awt.Color(255, 255, 255));
        btnSua.setColor2(new java.awt.Color(102, 102, 102));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        txtGiatrigiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Giá trị giảm");

        txtGiatriDHTT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThem.setForeground(new java.awt.Color(0, 0, 0));
        btnThem.setText("Thêm");
        btnThem.setColor1(new java.awt.Color(255, 255, 255));
        btnThem.setColor2(new java.awt.Color(102, 102, 102));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(48, 48, 48)
                                .addComponent(txtTenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(123, 123, 123))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11))
                .addGap(46, 46, 46)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLoaiVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiatrigiam, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiatriDHTT, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(txtSoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(56, 56, 56)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnTatCaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(btnTatCaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoaiVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiatrigiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiatriDHTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(38, 28, 73));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách Voucher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(38, 28, 73));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblVoucher.setAutoCreateRowSorter(true);
        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"aaaa", "aaaa", "aaaa", "aaaaaaaaa", null, null, null, null, null},
                {"aaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên Voucher", "Mã Voucher", "Ngày bắt đầu", "Ngày kết thúc", "Loại voucher", "Giá trị giảm", "Số lượng", "Giá trị HD tối thiểu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblVoucher.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblVoucher.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblVoucher.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblVoucher.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblVoucher.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblVoucher.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucher.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucher.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblVoucher.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblVoucher.setRowHeight(30);
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        scrollPaneWin115.setViewportView(tblVoucher);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin115, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin115, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtTimkiem.setForeground(new java.awt.Color(0, 0, 0));
        txtTimkiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTimkiem.setForeground(new java.awt.Color(0, 0, 0));
        btnTimkiem.setText("Tìm kiếm");
        btnTimkiem.setColor1(new java.awt.Color(255, 255, 255));
        btnTimkiem.setColor2(new java.awt.Color(102, 102, 102));
        btnTimkiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Số lượng");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Lọc ");

        cboloctheotrangthai.setBackground(new java.awt.Color(38, 28, 73));
        cboloctheotrangthai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cboloctheotrangthai.setForeground(new java.awt.Color(255, 255, 255));
        cboloctheotrangthai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2" }));
        cboloctheotrangthai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboloctheotrangthai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboloctheotrangthaiItemStateChanged(evt);
            }
        });
        cboloctheotrangthai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboloctheotrangthaiMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(cboloctheotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(592, 592, 592)
                    .addComponent(jLabel12)
                    .addContainerGap(593, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboloctheotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(183, 183, 183)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        tabVoucher.addTab("Voucher", jPanel2);

        jPanel18.setBackground(new java.awt.Color(38, 28, 73));

        jPanel16.setBackground(new java.awt.Color(38, 28, 73));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách Voucher đã xóa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel17.setBackground(new java.awt.Color(38, 28, 73));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblVoucherDaxoa.setAutoCreateRowSorter(true);
        tblVoucherDaxoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"aaaa", "aaaa", "aaaa", "aaaaaaaaa", null, null, null, null, null},
                {"aaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên Voucher", "Mã Voucher", "Ngày bắt đầu", "Ngày kết thúc", "Loại voucher", "Giá trị giảm", "Số lượng", "Giá trị HD tối thiểu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucherDaxoa.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblVoucherDaxoa.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblVoucherDaxoa.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblVoucherDaxoa.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblVoucherDaxoa.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblVoucherDaxoa.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblVoucherDaxoa.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblVoucherDaxoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucherDaxoa.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucherDaxoa.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblVoucherDaxoa.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblVoucherDaxoa.setRowHeight(30);
        scrollPaneWin113.setViewportView(tblVoucherDaxoa);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin113, javax.swing.GroupLayout.PREFERRED_SIZE, 1149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin113, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtTimkiemVCxoa.setForeground(new java.awt.Color(0, 0, 0));
        txtTimkiemVCxoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimkiemVCxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimkiemVCxoaActionPerformed(evt);
            }
        });

        btnTimkiemVoucherDaxoa.setForeground(new java.awt.Color(0, 0, 0));
        btnTimkiemVoucherDaxoa.setText("Tìm kiếm");
        btnTimkiemVoucherDaxoa.setColor1(new java.awt.Color(255, 255, 255));
        btnTimkiemVoucherDaxoa.setColor2(new java.awt.Color(102, 102, 102));
        btnTimkiemVoucherDaxoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimkiemVoucherDaxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemVoucherDaxoaActionPerformed(evt);
            }
        });

        btnTatcaVoucherDaxoa.setForeground(new java.awt.Color(0, 0, 0));
        btnTatcaVoucherDaxoa.setText("Tất cả");
        btnTatcaVoucherDaxoa.setColor1(new java.awt.Color(255, 255, 255));
        btnTatcaVoucherDaxoa.setColor2(new java.awt.Color(102, 102, 102));
        btnTatcaVoucherDaxoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTatcaVoucherDaxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatcaVoucherDaxoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(btnTatcaVoucherDaxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimkiemVCxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnTimkiemVoucherDaxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiemVCxoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiemVoucherDaxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTatcaVoucherDaxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnKhoiphuc.setForeground(new java.awt.Color(0, 0, 0));
        btnKhoiphuc.setText("Khôi phục");
        btnKhoiphuc.setColor1(new java.awt.Color(255, 255, 255));
        btnKhoiphuc.setColor2(new java.awt.Color(102, 102, 102));
        btnKhoiphuc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKhoiphuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiphucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnKhoiphuc, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(372, 372, 372))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnKhoiphuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(343, Short.MAX_VALUE))
        );

        tabVoucher.addTab("Voucher đã xóa", jPanel18);

        jPanel5.add(tabVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1550, 910));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1570, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1570, 990));
    }// </editor-fold>//GEN-END:initComponents

    private void btnTatCaVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatCaVoucherActionPerformed
        loadDataVoucher(iVoucherServise.getAllVoucherTrangThai012());
    }//GEN-LAST:event_btnTatCaVoucherActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        index = tblVoucher.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Voucher trước khi xóa");
            return;
        }
        String input = txtTimkiem.getText();
        QlVoucher qLVoucher = getListVoucherWhenSearch(input).get(index);
        String ma = qLVoucher.getMa();

        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!qLVoucher.getNgayKetThuc().before(dayCurrent) && qLVoucher.getTrangThai() == 0) {
            JOptionPane.showMessageDialog(this, "Voucher đang diễn ra.");

        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa voucher " + qLVoucher.getTen());
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã không xóa");
            return;
        }
        qLVoucher = iVoucherServise.deleteVoucher(qLVoucher, ma);
        if (qLVoucher != null) {
            model.removeRow(index);
            txtTimkiem.setText("");
            clearFormVoucher();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }


    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ArrayList<QlVoucher> lst = iVoucherServise.getAllVoucher();
        for (int i = 0; i < lst.size(); i++) {
            if (txtMaVoucher.getText().equalsIgnoreCase(lst.get(i).getMa())) {
                JOptionPane.showMessageDialog(this, "Trùng mã");
                return;
            }
        }
        if (validateFormVoucher()) {
            return;
        }
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date ngayBatDau = jdNgayBd.getDate();
        Date ngayKetThuc = jdNgayKt.getDate();
        // Kiểm tra ngày kết thúc phải sau ngày bắt đầu
        if (ngayKetThuc != null && ngayKetThuc.before(ngayBatDau)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
            return; // Ngừng xử lý nếu điều kiện không thỏa mãn
        }
        if (!jdNgayBd.getDate().after(dayCurrent)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu chọn sau ngày hiện tại ");
            return;
        }
        getFormVoucher();
        QlVoucher qLVoucher = iVoucherServise.addVoucher(getFormVoucher());
        if (qLVoucher != null) {
            loadDataVoucher(iVoucherServise.getAllVoucherTrangThai012());
            clearFormVoucher();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String input = txtTimkiem.getText();

        ArrayList<QlVoucher> listVoucher = new ArrayList<>();
        ArrayList<QlVoucher> lstByMa = new ArrayList<>();
        ArrayList<QlVoucher> lstByName = new ArrayList<>();

        ArrayList<QlVoucher> lstByNgay = new ArrayList<>();
        if (!input.trim().matches("[0-9]+")) {
            lstByName = iVoucherServise.getAllVoucherByNameKM0(input);
            listVoucher.addAll(lstByName);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = iVoucherServise.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(Voucher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByMa = iVoucherServise.getAllVoucherByMa0(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucher(listVoucher);
            JOptionPane.showMessageDialog(this, "Không tìm thấy voucher");
        } else {

            if (lstByMa.size() != 0) {
                loadDataVoucher(iVoucherServise.getAllVoucherByMa0(input));
            }
            if (lstByName.size() != 0) {
                loadDataVoucher(iVoucherServise.getAllVoucherByNameKM0(input));
            }
            if (lstByNgay.size() != 0) {
                loadDataVoucher(iVoucherServise.getAllVoucherByNgay0(date));
            }
        }
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            index = tblVoucher.getSelectedRow();
            String id = tblVoucher.getValueAt(index, 0).toString();
            QlVoucher qlVoucher = getFormVoucher();
            qlVoucher.setMa(id);
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn Voucher trước khi sửa");
                return;
            }
            Date ngayBatDau = jdNgayBd.getDate();
            Date ngayKetThuc = jdNgayKt.getDate();
            // Kiểm tra ngày kết thúc phải sau ngày bắt đầu
            if (ngayKetThuc != null && ngayKetThuc.before(ngayBatDau)) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
                return; // Ngừng xử lý nếu điều kiện không thỏa mãn
            }
            if (validateFormVoucher()) {
                return;
            }
            ArrayList<QlVoucher> lst = iVoucherServise.getAllVoucher();
            for (int i = 0; i < lst.size(); i++) {
                if (txtMaVoucher.getText().equalsIgnoreCase(lst.get(i).getMa())
                        && !qlVoucher.getMa().equals(lst.get(i).getMa())) {
                    JOptionPane.showMessageDialog(this, "Trùng mã");
                    return;
                }
            }

            if (iVoucherServise.updateVoucher(qlVoucher) != null) {
                loadDataVoucher(iVoucherServise.getAllVoucher());
                clearFormVoucher();
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFormVoucher();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTatcaVoucherDaxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatcaVoucherDaxoaActionPerformed
        try {
            loadDataVoucherDeleted(iVoucherServise.getAllVoucherDeleted());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTatcaVoucherDaxoaActionPerformed

    private void btnKhoiphucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiphucActionPerformed
        // TODO add your handling code here:
        index = tblVoucherDaxoa.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn voucher trước khi khôi phục");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        QlVoucher qLVoucher = iVoucherServise.getAllVoucherDeleted().get(index);
        Integer id = qLVoucher.getId();
        QlVoucher kq = iVoucherServise.khoiPhucVoucher(qLVoucher, id);
        if (kq != null) {
            loadDataVoucherDeleted(iVoucherServise.getAllVoucherDeleted());
            JOptionPane.showMessageDialog(this, "Khôi phục thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Khôi phục thất bại");
        }
    }//GEN-LAST:event_btnKhoiphucActionPerformed

    private void tabVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabVoucherMouseClicked
        if (tabVoucher.isShowing()) {
            loadDataVoucher(iVoucherServise.getAllVoucherTrangThai012());
        } else {
            loadDataVoucherDeleted(iVoucherServise.getAllVoucherDeleted());
        }
    }//GEN-LAST:event_tabVoucherMouseClicked

    private void btnTimkiemVoucherDaxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemVoucherDaxoaActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String input = txtTimkiemVCxoa.getText();

        ArrayList<QlVoucher> listVoucher = new ArrayList<>();
        ArrayList<QlVoucher> lstByMa3 = new ArrayList<>();
        ArrayList<QlVoucher> lstByName3 = new ArrayList<>();
        ArrayList<QlVoucher> lstByNgay3 = new ArrayList<>();
        if (!input.trim().matches("[0-9]+")) {
            lstByName3 = iVoucherServise.getAllVoucherByNameKM3(input);
            listVoucher.addAll(lstByName3);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay3 = iVoucherServise.getAllVoucherByNgay3(date);
                listVoucher.addAll(lstByNgay3);
            } catch (ParseException ex) {
                Logger.getLogger(Voucher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByMa3 = iVoucherServise.getAllVoucherByMa3(input);
        listVoucher.addAll(lstByMa3);

        if (listVoucher.size() == 0) {
            loadDataVoucherDeleted(listVoucher);
            JOptionPane.showMessageDialog(this, "Không tìm thấy voucher");
        } else {

            if (lstByMa3.size() != 0) {
                loadDataVoucherDeleted(iVoucherServise.getAllVoucherByMa3(input));
            }
            if (lstByName3.size() != 0) {
                loadDataVoucherDeleted(iVoucherServise.getAllVoucherByNameKM3(input));
            }
            if (lstByNgay3.size() != 0) {
                loadDataVoucherDeleted(iVoucherServise.getAllVoucherByNgay3(date));
            }
        }
    }//GEN-LAST:event_btnTimkiemVoucherDaxoaActionPerformed

    private void cboLoaiVoucherMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiVoucherMouseReleased
//        String loaiGiamGia = (String) cboLoaiVoucher.getSelectedItem();
//        boolean isLoaiGiamGia = Boolean.parseBoolean(loaiGiamGia);
//        lblGiatrigiam.setText("Theo phần trăm".equals(loaiGiamGia) ? "%" : "Đồng");
    }//GEN-LAST:event_cboLoaiVoucherMouseReleased

    private void txtTimkiemVCxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimkiemVCxoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimkiemVCxoaActionPerformed

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        showDetailVoucher(iVoucherServise.getAllVoucherTrangThai012());
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void cboloctheotrangthaiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboloctheotrangthaiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboloctheotrangthaiMouseReleased
    private String getTrangThaiString(int trangThai) {
        if (trangThai == 0) {
            return "Đang diễn ra";
        } else if (trangThai == 2) {
            return "Sắp diễn ra";
        } else {
            return "Đã kết thúc";
        }
    }

    private void loadComboboxTrangThai() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblVoucher.getModel();

            Connection connection = DBConnect.getConnection();
            String sql = "SELECT ma, ten, ngayBatDau, ngayKetThuc, loaiGiamGia, giaTriGiam, soLuong, GiaTriDhToiThieu, trangThai FROM Voucher WHERE TrangThai = ?";

            String selectedTrangThai = cboloctheotrangthai.getSelectedItem().toString();
            if (!selectedTrangThai.isEmpty()) {
                int trangThai = Integer.parseInt(selectedTrangThai);
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, trangThai);
                ResultSet rs = statement.executeQuery();
                model.setColumnCount(0);
                model.addColumn("Mã Voucher");
                model.addColumn("Tên Voucher");
                model.addColumn("Ngày bắt đầu");
                model.addColumn("Ngày kết thúc");
                model.addColumn("Loại voucher");
                model.addColumn("Giá trị giảm");
                model.addColumn("Số lượng");
                model.addColumn("Giá trị đơn hàng tối thiểu");
                model.addColumn("Trạng thái");
                model.setRowCount(0);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("ma"),
                        rs.getString("ten"),
                        sdf.format(rs.getDate("ngayBatDau")),
                        sdf.format(rs.getDate("ngayKetThuc")),
                        rs.getString("loaiGiamGia"),
                        rs.getString("giaTriGiam"),
                        rs.getInt("soLuong"),
                        rs.getBigDecimal("GiaTriDhToiThieu"),
                        getTrangThaiString(rs.getInt("trangThai"))
                    });
                }

                statement.close();
                connection.close();
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void cboloctheotrangthaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboloctheotrangthaiItemStateChanged
        // TODO add your handling code here:
        loadComboboxTrangThai();

    }//GEN-LAST:event_cboloctheotrangthaiItemStateChanged
    private void clearFormVoucher() {

        txtTenVoucher.setText("");
        txtMaVoucher.setText("");
        jdNgayBd.setDate(null);
        jdNgayKt.setDate(null);
        txtGiatrigiam.setText("");
        txtGiatriDHTT.setText("");
        txtSoluong.setText("");
        txtTimkiem.setText("");
        tblVoucher.clearSelection();

        index = -1;
    }

    private ArrayList<QlVoucher> getListVoucherWhenSearch(String input) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        ArrayList<QlVoucher> listVoucher = new ArrayList<>();
        ArrayList<QlVoucher> lstByMa = new ArrayList<>();
        ArrayList<QlVoucher> lstByName = new ArrayList<>();
        ArrayList<QlVoucher> lstByNgay = new ArrayList<>();
        if (!input.trim().matches("[0-9]+")) {
            lstByName = iVoucherServise.getAllVoucherByNameKM0(input);
            listVoucher.addAll(lstByName);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = iVoucherServise.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(Voucher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        lstByMa = iVoucherServise.getAllVoucherByMa0(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucher(listVoucher);
            JOptionPane.showMessageDialog(this, "Không tìm thấy voucher");
        } else {
            if (lstByMa.size() != 0) {
                loadDataVoucher(iVoucherServise.getAllVoucherByMa0(input));
            }
            if (lstByName.size() != 0) {
                loadDataVoucher(iVoucherServise.getAllVoucherByNameKM0(input));
            }
            if (lstByNgay.size() != 0) {
                loadDataVoucher(iVoucherServise.getAllVoucherByNgay0(date));
            }
        }
        return listVoucher;
    }

    private Boolean validateFormVoucher() {

        if (Uhelper.checkNull(txtTenVoucher, "Nhập tên voucher")) {
            return true;
        }
        if (Uhelper.checkNull(txtMaVoucher, "Nhập mã")) {
            return true;
        }
        if (Uhelper.checkKyTuDacBiet(txtMaVoucher, "Nhập mã chỉ có chữ cái và số")) {
            return true;
        }

        if (Uhelper.checkDate(jdNgayBd, "Chọn ngày bắt đầu")) {
            return true;
        }
        if (Uhelper.checkDate(jdNgayKt, "Chọn ngày kết thúc")) {
            return true;
        }
        if (Uhelper.checkTime(jdNgayBd.getDate(), jdNgayKt.getDate(), "Ngày kết thúc phải sau ngày bắt đầu")) {
            return true;
        }
        if (Uhelper.checkNull(txtGiatrigiam, "Nhập giá trị giảm giá")) {
            return true;
        }
        if (Uhelper.checkNumber(txtGiatrigiam, "Nhập giá trị giảm là số nguyên")) {
            return true;
        }
//        if (Uhelper.checkgiatrigiam(txtGiatrigiam, "Nhập giá trị giảm >= 0 và <= 50")) {
//            return true;
//        }

        if (Uhelper.checkNull(txtSoluong, "Nhập số lượng")) {
            return true;
        }
        if (Uhelper.checkNumber(txtSoluong, "Nhập số lượng là số")) {
            return true;
        }
        if (Uhelper.checkSoLuong(txtSoluong, "Nhập số lượng > 0")) {
            return true;
        }
        if (Uhelper.checkNull(txtGiatriDHTT, "Nhập giá trị đơn hàng tối thiểu")) {
            return true;
        }
//        if (Uhelper.checkNumber(txtGtDH, "Nhập giá trị đơn hàng tối thiểu là số")) {
//            return true;
//        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.view.swing.ButtonGradient btnClear;
    private app.view.swing.ButtonGradient btnKhoiphuc;
    private app.view.swing.ButtonGradient btnSua;
    private app.view.swing.ButtonGradient btnTatCaVoucher;
    private app.view.swing.ButtonGradient btnTatcaVoucherDaxoa;
    private app.view.swing.ButtonGradient btnThem;
    private app.view.swing.ButtonGradient btnTimkiem;
    private app.view.swing.ButtonGradient btnTimkiemVoucherDaxoa;
    private app.view.swing.ButtonGradient btnXoa;
    private app.view.swing.ComboBoxSuggestion cboLoaiVoucher;
    private app.view.swing.ComboBoxSuggestion cboloctheotrangthai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private com.toedter.calendar.JDateChooser jdNgayBd;
    private com.toedter.calendar.JDateChooser jdNgayKt;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin111;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin112;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin113;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin114;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin115;
    private app.view.swing.TabbedPaneCustom tabVoucher;
    private rojeru_san.complementos.RSTableMetro tblVoucher;
    private rojeru_san.complementos.RSTableMetro tblVoucherDaxoa;
    private textfield.TextField2 txtGiatriDHTT;
    private textfield.TextField2 txtGiatrigiam;
    private textfield.TextField2 txtMaVoucher;
    private textfield.TextField2 txtSoluong;
    private textfield.TextField2 txtTenVoucher;
    private textfield.TextField2 txtTimkiem;
    private textfield.TextField2 txtTimkiemVCxoa;
    // End of variables declaration//GEN-END:variables
}
