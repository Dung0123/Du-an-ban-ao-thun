package view;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import util.JpanelLoader;

/**
 *
 * @author bcuon
 */
public class MainJFrame extends javax.swing.JFrame {

    private final JpanelLoader jpanelLoader = new JpanelLoader();
    private static final Color colorMoved = new Color(38, 32, 78);
    private static final Color colorExited = new Color(51, 0, 102);

    public MainJFrame() {
        initComponents();
        setJPanel(pnLoad, new ViewBanHang(), "Bán Hàng", "sale64.png");
    }

    private void setJPanel(JPanel jPanelMain, JPanel jPanelSub, String textLabel, String imageName) {
        jpanelLoader.jPanelLoader(jPanelMain, jPanelSub);
        lbTextBanner.setText(textLabel);
        ImageIcon imageIcon = new ImageIcon("D:\\PosPro\\src\\img\\" + imageName);
        lbIconBanner.setIcon(imageIcon);
    }

    private void setJPanelMouseMoved(JPanel jPanel) {
        jPanel.setBackground(colorMoved);
    }

    private void setJPanelMouseExited(JPanel jPanel) {
        jPanel.setBackground(colorExited);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnBackgroundProfile = new javax.swing.JPanel();
        pnBanHang = new javax.swing.JPanel();
        lbBanHang = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnSanPham = new javax.swing.JPanel();
        lbSanPham = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnNhanVien = new javax.swing.JPanel();
        lbNhanVien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnKhachHang = new javax.swing.JPanel();
        lbKhachHang = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnThongKe = new javax.swing.JPanel();
        lbThongKe = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pnHoaDon = new javax.swing.JPanel();
        lbHoaDon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnKhuyenMai = new javax.swing.JPanel();
        lbKhuyenMai = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnLoad = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnBanner = new javax.swing.JPanel();
        lbTextBanner = new javax.swing.JLabel();
        lbIconBanner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnBackgroundProfile.setBackground(new java.awt.Color(51, 0, 102));
        pnBackgroundProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnBanHang.setBackground(new java.awt.Color(51, 0, 102));
        pnBanHang.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnBanHangMouseMoved(evt);
            }
        });
        pnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnBanHangMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnBanHangMouseExited(evt);
            }
        });

        lbBanHang.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        lbBanHang.setForeground(new java.awt.Color(255, 255, 255));
        lbBanHang.setText("Bán Hàng");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sale.png"))); // NOI18N

        javax.swing.GroupLayout pnBanHangLayout = new javax.swing.GroupLayout(pnBanHang);
        pnBanHang.setLayout(pnBanHangLayout);
        pnBanHangLayout.setHorizontalGroup(
            pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBanHangLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbBanHang)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        pnBanHangLayout.setVerticalGroup(
            pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBanHangLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lbBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnBackgroundProfile.add(pnBanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 350, 70));

        pnSanPham.setBackground(new java.awt.Color(51, 0, 102));
        pnSanPham.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnSanPhamMouseMoved(evt);
            }
        });
        pnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnSanPhamMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnSanPhamMouseExited(evt);
            }
        });

        lbSanPham.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        lbSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lbSanPham.setText("Sản Phẩm");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/product.png"))); // NOI18N

        javax.swing.GroupLayout pnSanPhamLayout = new javax.swing.GroupLayout(pnSanPham);
        pnSanPham.setLayout(pnSanPhamLayout);
        pnSanPhamLayout.setHorizontalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSanPham)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        pnSanPhamLayout.setVerticalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnSanPhamLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pnBackgroundProfile.add(pnSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 350, 70));

        pnNhanVien.setBackground(new java.awt.Color(51, 0, 102));
        pnNhanVien.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnNhanVienMouseMoved(evt);
            }
        });
        pnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnNhanVienMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnNhanVienMouseExited(evt);
            }
        });

        lbNhanVien.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        lbNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lbNhanVien.setText("Nhân Viên");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/employee.png"))); // NOI18N

        javax.swing.GroupLayout pnNhanVienLayout = new javax.swing.GroupLayout(pnNhanVien);
        pnNhanVien.setLayout(pnNhanVienLayout);
        pnNhanVienLayout.setHorizontalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNhanVien)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        pnNhanVienLayout.setVerticalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanVienLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnBackgroundProfile.add(pnNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 350, 70));

        pnKhachHang.setBackground(new java.awt.Color(51, 0, 102));
        pnKhachHang.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnKhachHangMouseMoved(evt);
            }
        });
        pnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnKhachHangMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnKhachHangMouseExited(evt);
            }
        });

        lbKhachHang.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        lbKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lbKhachHang.setText("Khách Hàng");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/customer.png"))); // NOI18N

        javax.swing.GroupLayout pnKhachHangLayout = new javax.swing.GroupLayout(pnKhachHang);
        pnKhachHang.setLayout(pnKhachHangLayout);
        pnKhachHangLayout.setHorizontalGroup(
            pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachHangLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbKhachHang)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        pnKhachHangLayout.setVerticalGroup(
            pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhachHangLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnBackgroundProfile.add(pnKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 350, 70));

        pnThongKe.setBackground(new java.awt.Color(51, 0, 102));
        pnThongKe.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnThongKeMouseMoved(evt);
            }
        });
        pnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnThongKeMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnThongKeMouseExited(evt);
            }
        });

        lbThongKe.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        lbThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lbThongKe.setText("Thống Kê");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report.png"))); // NOI18N

        javax.swing.GroupLayout pnThongKeLayout = new javax.swing.GroupLayout(pnThongKe);
        pnThongKe.setLayout(pnThongKeLayout);
        pnThongKeLayout.setHorizontalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongKeLayout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbThongKe)
                .addGap(115, 115, 115))
        );
        pnThongKeLayout.setVerticalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongKeLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lbThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnBackgroundProfile.add(pnThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 770, 350, 70));

        pnHoaDon.setBackground(new java.awt.Color(51, 0, 102));
        pnHoaDon.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnHoaDonMouseMoved(evt);
            }
        });
        pnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnHoaDonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnHoaDonMouseExited(evt);
            }
        });

        lbHoaDon.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        lbHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lbHoaDon.setText("Hóa Đơn");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/invoice.png"))); // NOI18N

        javax.swing.GroupLayout pnHoaDonLayout = new javax.swing.GroupLayout(pnHoaDon);
        pnHoaDon.setLayout(pnHoaDonLayout);
        pnHoaDonLayout.setHorizontalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoaDon)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        pnHoaDonLayout.setVerticalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHoaDonLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lbHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnBackgroundProfile.add(pnHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 350, 70));

        pnKhuyenMai.setBackground(new java.awt.Color(51, 0, 102));
        pnKhuyenMai.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnKhuyenMaiMouseMoved(evt);
            }
        });
        pnKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnKhuyenMaiMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnKhuyenMaiMouseExited(evt);
            }
        });

        lbKhuyenMai.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        lbKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        lbKhuyenMai.setText("Khuyến Mãi");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/invoice.png"))); // NOI18N

        javax.swing.GroupLayout pnKhuyenMaiLayout = new javax.swing.GroupLayout(pnKhuyenMai);
        pnKhuyenMai.setLayout(pnKhuyenMaiLayout);
        pnKhuyenMaiLayout.setHorizontalGroup(
            pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhuyenMaiLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbKhuyenMai)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        pnKhuyenMaiLayout.setVerticalGroup(
            pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lbKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnBackgroundProfile.add(pnKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 350, 70));

        getContentPane().add(pnBackgroundProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 1080));

        pnLoad.setBackground(new java.awt.Color(38, 28, 73));
        pnLoad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(38, 32, 78));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        pnLoad.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        getContentPane().add(pnLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 1570, 990));

        pnBanner.setBackground(new java.awt.Color(38, 28, 73));

        lbTextBanner.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N
        lbTextBanner.setForeground(new java.awt.Color(255, 255, 255));
        lbTextBanner.setText("Dashboard");

        lbIconBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard64.png"))); // NOI18N

        javax.swing.GroupLayout pnBannerLayout = new javax.swing.GroupLayout(pnBanner);
        pnBanner.setLayout(pnBannerLayout);
        pnBannerLayout.setHorizontalGroup(
            pnBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBannerLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbIconBanner)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTextBanner)
                .addContainerGap(1299, Short.MAX_VALUE))
        );
        pnBannerLayout.setVerticalGroup(
            pnBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIconBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBannerLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(lbTextBanner)
                .addGap(15, 15, 15))
        );

        getContentPane().add(pnBanner, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBanHangMouseClicked
        setJPanel(pnLoad, new ViewBanHang(), "Bán Hàng", "sale64.png");
    }//GEN-LAST:event_pnBanHangMouseClicked

    private void pnSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnSanPhamMouseClicked
        setJPanel(pnLoad, new ViewSanPham(this), "Quản Lý Sản Phẩm", "product64.png");
    }//GEN-LAST:event_pnSanPhamMouseClicked

    private void pnNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnNhanVienMouseClicked
        setJPanel(pnLoad, new ViewNhanVien(), "Nhân Viên", "employee64.png");
    }//GEN-LAST:event_pnNhanVienMouseClicked

    private void pnHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnHoaDonMouseClicked
        setJPanel(pnLoad, new ViewHoaDon(), "Hóa Đơn", "invoice64.png");
    }//GEN-LAST:event_pnHoaDonMouseClicked

    private void pnKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnKhuyenMaiMouseClicked
        setJPanel(pnLoad, new ViewKhuyenMai(), "Khuyến Mãi", "invoice64.png");
    }//GEN-LAST:event_pnKhuyenMaiMouseClicked

    private void pnKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnKhachHangMouseClicked
        setJPanel(pnLoad, new ViewKhachHang(), "Khách Hàng", "customer64.png");
    }//GEN-LAST:event_pnKhachHangMouseClicked

    private void pnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThongKeMouseClicked
        setJPanel(pnLoad, new ViewThongKe(), "Thống Kê", "report64.png");
    }//GEN-LAST:event_pnThongKeMouseClicked

    private void pnBanHangMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBanHangMouseMoved
        setJPanelMouseMoved(pnBanHang);
    }//GEN-LAST:event_pnBanHangMouseMoved

    private void pnSanPhamMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnSanPhamMouseMoved
        setJPanelMouseMoved(pnSanPham);
    }//GEN-LAST:event_pnSanPhamMouseMoved

    private void pnNhanVienMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnNhanVienMouseMoved
        setJPanelMouseMoved(pnNhanVien);
    }//GEN-LAST:event_pnNhanVienMouseMoved

    private void pnHoaDonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnHoaDonMouseMoved
        setJPanelMouseMoved(pnHoaDon);
    }//GEN-LAST:event_pnHoaDonMouseMoved

    private void pnKhuyenMaiMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnKhuyenMaiMouseMoved
        setJPanelMouseMoved(pnKhuyenMai);
    }//GEN-LAST:event_pnKhuyenMaiMouseMoved

    private void pnKhachHangMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnKhachHangMouseMoved
        setJPanelMouseMoved(pnKhachHang);
    }//GEN-LAST:event_pnKhachHangMouseMoved

    private void pnThongKeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThongKeMouseMoved
        setJPanelMouseMoved(pnThongKe);
    }//GEN-LAST:event_pnThongKeMouseMoved

    private void pnBanHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBanHangMouseExited
        setJPanelMouseExited(pnBanHang);
    }//GEN-LAST:event_pnBanHangMouseExited

    private void pnSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnSanPhamMouseExited
        setJPanelMouseExited(pnSanPham);
    }//GEN-LAST:event_pnSanPhamMouseExited

    private void pnNhanVienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnNhanVienMouseExited
        setJPanelMouseExited(pnNhanVien);
    }//GEN-LAST:event_pnNhanVienMouseExited

    private void pnHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnHoaDonMouseExited
        setJPanelMouseExited(pnHoaDon);
    }//GEN-LAST:event_pnHoaDonMouseExited

    private void pnKhuyenMaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnKhuyenMaiMouseExited
        setJPanelMouseExited(pnKhuyenMai);
    }//GEN-LAST:event_pnKhuyenMaiMouseExited

    private void pnKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnKhachHangMouseExited
        setJPanelMouseExited(pnKhachHang);
    }//GEN-LAST:event_pnKhachHangMouseExited

    private void pnThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThongKeMouseExited
        setJPanelMouseExited(pnThongKe);
    }//GEN-LAST:event_pnThongKeMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbBanHang;
    private javax.swing.JLabel lbHoaDon;
    private javax.swing.JLabel lbIconBanner;
    private javax.swing.JLabel lbKhachHang;
    private javax.swing.JLabel lbKhuyenMai;
    private javax.swing.JLabel lbNhanVien;
    private javax.swing.JLabel lbSanPham;
    private javax.swing.JLabel lbTextBanner;
    private javax.swing.JLabel lbThongKe;
    private javax.swing.JPanel pnBackgroundProfile;
    private javax.swing.JPanel pnBanHang;
    private javax.swing.JPanel pnBanner;
    private javax.swing.JPanel pnHoaDon;
    private javax.swing.JPanel pnKhachHang;
    private javax.swing.JPanel pnKhuyenMai;
    private javax.swing.JPanel pnLoad;
    private javax.swing.JPanel pnNhanVien;
    private javax.swing.JPanel pnSanPham;
    private javax.swing.JPanel pnThongKe;
    // End of variables declaration//GEN-END:variables
}
