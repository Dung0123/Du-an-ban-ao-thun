create database G7BanAoThun
use G7BanAoThun
go
CREATE TABLE [ChucVu] (
  [id] int identity,
  [ma] varchar(20) null,
  [ten] nvarchar(30) null,
  [moTa] nvarchar(255) null,
  PRIMARY KEY ([id])
)

CREATE TABLE [XuatXu] (
  [id] int identity ,
  [ten] nvarchar(50) null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  [isDelete] Bit null,
  PRIMARY KEY ([id])
)

CREATE TABLE [ChieuDaiTay] (
  [id] int identity ,
  [ten] nvarchar(50) null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  [isDelete] Bit null,
  PRIMARY KEY ([id])
)

CREATE TABLE [DanhMuc] (
  [id] int identity ,
  [ten] nvarchar(50) null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  [isDelete] Bit null,
  PRIMARY KEY ([id])
)

CREATE TABLE [KichCo](
  [id] int identity ,
  [ten] nvarchar(50) null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  [isDelete] Bit null,
  PRIMARY KEY ([id])
)

CREATE TABLE [ThuongHieu] (
  [id] int identity ,
  [ten] nvarchar(50) null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  [isDelete] Bit null,
  PRIMARY KEY ([id])
)

CREATE TABLE [ChatLieu] (
  [id] int identity ,
  [ten] nvarchar(50) null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  [isDelete] Bit null,
  PRIMARY KEY ([id])
)

CREATE TABLE [MauSac] (
  [id] int identity  ,
  [ten] nvarchar(50) null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  [isDelete] Bit null,
  PRIMARY KEY ([id])
)

CREATE TABLE [CoAo] (
  [id] int identity,
  [ten] nvarchar(50) null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  [isDelete] Bit null,
  PRIMARY KEY ([id])
)

CREATE TABLE [KhachHang] (
  [id] int identity(1,1),
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [sdt] varchar(20) null,
  [email] varchar(20) null,
  [gioiTinh] Bit null,
  ngaySinh Date,
  diaChi nvarchar(255),
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  PRIMARY KEY ([id])
)

CREATE TABLE [Voucher] (
  [id] int identity ,
  [ma] varchar(20) ,
  [ten] varchar(20) ,
  [IdnhanVien] int ,
  [loaiGiamGia] bit null,
  [ngayBatDau] Datetime2 null,
  [ngayKetThuc] Datetime2 null,
  giaTriGiam int null,
   [GiaTriDhToiThieu] Decimal(20,2) null,
  [trangThai] int DEFAULT 0 null,
  soLuong int null,
  soLuotSd int null,
  PRIMARY KEY ([id])
);

CREATE TABLE [ThanhToan] (
  [id] int identity  ,
  [maGiaoDich] varchar(20) null,
  [hinhThuc] varchar(20) null,
  [moTa] nvarchar(255) null,
  [giaTri] Decimal(20,2) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  PRIMARY KEY ([id])
)

CREATE TABLE [NhanVien] (
  [id] int identity,
  [idChucVu] int ,
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [email] varchar(20) null,
  [sdt] varchar(20) null,
  [matKhau] varchar(32) null,
  [diaChi] nvarchar(255) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_NhanVien.IdChucVu]
    FOREIGN KEY ([idChucVu])
      REFERENCES [ChucVu]([id])
)

CREATE TABLE [HoaDon] (
  [id] int identity,
  [idNhanVien] int,
  [idKhachHang] int,
  [idHinhThucThanhToan] int,
  [sdt] varchar(20) null,
  [diaChi] nvarchar(255) null,
  [tongTien] Decimal(20,2) null,
  [tongTienGiam] Decimal(20,2) null,
  [ngayGiaoHang] Datetime2 null,
  [ngayNhan] Datetime2 null,
  [ngayMuonNhan] Datetime2 null,
  [loaiHoaDon] varchar(20) null,
  [tienGiaoHang] Decimal(20,2) null,
  [ngayTao] Datetime2 null,
  [trangThai] int DEFAULT 0 null,
  [idVoucher] int ,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_HoaDon.IdVoucher]
    FOREIGN KEY ([idVoucher])
      REFERENCES [Voucher]([id]),
  CONSTRAINT [FK_HoaDon.IdkhachHang]
    FOREIGN KEY ([idKhachHang])
      REFERENCES [KhachHang]([id]),
  CONSTRAINT [FK_HoaDon.IdnhanVien]
    FOREIGN KEY ([idNhanVien])
      REFERENCES [NhanVien]([id]),
	  CONSTRAINT [FK_HoaDon.IdHinhThucThanhToan]
    FOREIGN KEY ([idHinhThucThanhToan])
      REFERENCES [ThanhToan]([id])
)

CREATE TABLE [SanPham] (
  [id] int identity ,
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [isDelete] Bit null,
  [idDanhMuc] int null,
  [idThuongHieu] int null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_SanPham.idThuonghieu]
    FOREIGN KEY ([idThuongHieu])
      REFERENCES [ThuongHieu]([id]),
	  CONSTRAINT [FK_SanPham.idDanhMuc]
    FOREIGN KEY ([idDanhMuc])
      REFERENCES [DanhMuc]([id])
)

CREATE TABLE [SanPhamChiTiet] (
  [id] int identity,
  [ma] varchar(10),
  [maVach] varchar(255)null,
  [idChatLieu] int null,
  [idChieuDaiTay] int null,
  [idKichCo] int  null,
  [idMauSac] int null,
  [idSanPham] int  null,
  [idCoAo] int null,
  [idXuatXu] int null,
  [soLuong] int null,
  [giaBan] Decimal(20,2) null,
  [moTa] nvarchar(255) null,
  [ngayTao] Datetime2 null,
  [ngaySua] Datetime2 null,
  [trangThai] int DEFAULT 0 null,
  PRIMARY KEY ([id]),

  CONSTRAINT [FK_SanPhamChiTiet.idCoAo]
    FOREIGN KEY ([idCoAo])
      REFERENCES [CoAo]([id]),
  CONSTRAINT [FK_SanPhamChiTiet.idChieuDaiTay]
    FOREIGN KEY ([idChieuDaiTay])
      REFERENCES [ChieuDaiTay]([id]),
  CONSTRAINT [FK_SanPhamChiTiet.idMauSac]
    FOREIGN KEY ([idMauSac])
      REFERENCES [MauSac]([id]),
  CONSTRAINT [FK_SanPhamChiTiet.idChatLieu]
    FOREIGN KEY ([idChatLieu])
      REFERENCES [ChatLieu]([id]),
  CONSTRAINT [FK_SanPhamChiTiet.idSanPham]
    FOREIGN KEY ([idSanPham])
      REFERENCES [SanPham]([id]),
	  CONSTRAINT [FK_SanPhamChiTiet.idKichCo]
    FOREIGN KEY ([idKichCo])
      REFERENCES [KichCo]([id]),
	  CONSTRAINT [FK_SanPhamChiTiet.idXuatXu]
    FOREIGN KEY ([idXuatXu])
      REFERENCES [XuatXu]([id])
);

CREATE TABLE [HoaDonChiTiet] (
  [id] int identity  ,
  [idSanPhamChiTiet] int null,
  [idHoaDon] int null,
  [soLuong] int null,
  [giaTien] Decimal(20,2) null,
  [thanhTien] Decimal(20,2) null,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_HoaDonChiTiet.idSanPhamChiTiet]
    FOREIGN KEY ([idSanPhamChiTiet])
      REFERENCES [SanPhamChiTiet]([id]),
  CONSTRAINT [FK_HoaDonChiTiet.idHoaDon]
    FOREIGN KEY ([idHoaDon])
      REFERENCES [HoaDon]([id])
);

-- Insert three rows of data in Vietnamese
INSERT INTO [KhachHang] ([ma], [ten], [sdt], [email], [gioiTinh], ngaySinh, diaChi, [trangThai], [ngayTao], [ngaySua])
VALUES
  ('MaKH1', N'Tên KH 1', '0123456789', 'kh1@example.com', 1, '1990-01-01', N'Địa chỉ 1', 1, '2023-01-01', '2023-01-01'),
  ('MaKH2', N'Tên KH 2', '0987654321', 'kh2@example.com', 0, '1985-05-05', N'Địa chỉ 2', 0, '2023-02-01', '2023-02-01'),
  ('MaKH3', N'Tên KH 3', '0369852147', 'kh3@example.com', 1, '2000-10-10', N'Địa chỉ 3', 1, '2023-03-01', '2023-03-01');
 
-- Chèn dữ liệu vào bảng [Voucher]
INSERT INTO [Voucher] ([ma], [ten], [IdnhanVien], [loaiGiamGia], [ngayBatDau], [ngayKetThuc], giaTriGiam, [GiaTriDhToiThieu], [trangThai], soLuong, soLuotSd)
VALUES
  ('Ma1', N'Tên1', 1, 1, '2023-01-01', '2023-02-01', 100, 50.5, 1, 10, 5),
  ('Ma2', N'Tên2', 2, 0, '2023-02-01', '2023-03-01', 50, 30.7, 0, 20, 8),
  ('Ma3', N'Tên3', 3, 1, '2023-03-01', '2023-04-01', 80, 40.2, 1, 15, 12);

-- Chèn dữ liệu vào bảng ThanhToan
INSERT INTO [ThanhToan] ([maGiaoDich], [hinhThuc], [moTa], [giaTri], [trangThai], [ngayTao], [ngaySua])
VALUES
  ('GD001', 'COD', N'Thanh toán đơn hàng online', 500.00, 1, '2023-11-23T08:00:00', '2023-11-23T08:30:00'),
  ('GD002', 'Cash', N'Thanh toán tại cửa hàng', 800.00, 1, '2023-11-24T09:00:00', '2023-11-24T09:30:00'),

-- Chèn dữ liệu vào bảng HoaDon
INSERT INTO [HoaDon] ([idNhanVien], [idKhachHang], [idHinhThucThanhToan], [sdt], [diaChi], [tongTien], [tongTienGiam], [ngayGiaoHang], [ngayNhan], [ngayMuonNhan], [loaiHoaDon], [tienGiaoHang], [ngayTao], [trangThai], [idVoucher])
VALUES
  (2, 1, 1, '0123456789', N'123 Đường ABC, Quận 1, TP.HCM', 500.00, 50.00, '2023-11-23T13:00:00', '2023-11-23T14:00:00', '2023-11-23T15:00:00', 'Online', 20.00, '2023-11-23T16:00:00', 1, 1),
  (2, 2, 2, '0987654321', N'456 Đường XYZ, Quận 2, TP.HCM', 800.00, 80.00, '2023-11-24T09:00:00', '2023-11-24T10:00:00', '2023-11-24T11:00:00', 'In-store', 30.00, '2023-11-24T12:00:00', 1, 2),
  (4, 3, 1, '0123456789', N'789 Đường DEF, Quận 3, TP.HCM', 1200.00, 120.00, '2023-11-25T14:00:00', '2023-11-25T15:00:00', '2023-11-25T16:00:00', 'Online', 40.00, '2023-11-25T17:00:00', 0, 3),
  (3, 1, 2, '0987654321', N'101 Đường GHI, Quận 4, TP.HCM', 1500.00, 150.00, '2023-11-26T10:00:00', '2023-11-26T11:00:00', '2023-11-26T12:00:00', 'In-store', 50.00, '2023-11-26T13:00:00', 1, 1),
  (5, 2, 1, '0123456789', N'111 Đường JKL, Quận 5, TP.HCM', 2000.00, 200.00, '2023-11-27T12:00:00', '2023-11-27T13:00:00', '2023-11-27T14:00:00', 'Online', 60.00, '2023-11-27T15:00:00', 0, 2);
  select * from HoaDon
-- Chèn dữ liệu vào bảng NhanVien
INSERT INTO [NhanVien] ([idChucVu], [ma], [ten], [email], [sdt], [matKhau], [diaChi], [trangThai], [ngayTao], [ngaySua])
VALUES
  (1, 'NV001', N'Nguyễn Văn A', 'nv.a@example.com', '0123456789', 'hashed_password_1', N'123 Đường ABC, Quận 1, TP.HCM', 1, '2023-11-23T08:00:00', '2023-11-23T08:30:00'),
  (2, 'NV002', N'Trần Thị B', 'nv.b@example.com', '0987654321', 'hashed_password_2', N'456 Đường XYZ, Quận 2, TP.HCM', 1, '2023-11-23T09:00:00', '2023-11-23T09:30:00'),
  (1, 'NV003', N'Hoàng Văn C', 'nv.c@example.com', '0123456789', 'hashed_password_3', N'789 Đường DEF, Quận 3, TP.HCM', 0, '2023-11-23T10:00:00', '2023-11-23T10:30:00'),
  (3, 'NV004', N'Lê Thị D', 'nv.d@example.com', '0987654321', 'hashed_password_4', N'101 Đường GHI, Quận 4, TP.HCM', 1, '2023-11-23T11:00:00', '2023-11-23T11:30:00'),
  (2, 'NV005', N'Phạm Văn E', 'nv.e@example.com', '0123456789', 'hashed_password_5', N'111 Đường JKL, Quận 5, TP.HCM', 0, '2023-11-23T12:00:00', '2023-11-23T12:30:00');

-- Insert into ChucVu
INSERT INTO [ChucVu] ([ma], [ten], [moTa])
VALUES
  ('MA_CV_1', N'Tên Chức Vụ 1', N'Mô tả Chức Vụ 1'),
  ('MA_CV_2', N'Tên Chức Vụ 2', N'Mô tả Chức Vụ 2'),
  ('MA_CV_3', N'Tên Chức Vụ 3', N'Mô tả Chức Vụ 3');

-- Insert into XuatXu
INSERT INTO [XuatXu] ([ten], [ngayTao], [ngaySua], [isDelete])
VALUES
  (N'Tên Xuất Xứ 1', GETDATE(), GETDATE(), 0),
  (N'Tên Xuất Xứ 2', GETDATE(), GETDATE(), 0),
  (N'Tên Xuất Xứ 3', GETDATE(), GETDATE(), 0);

-- Insert into ChieuDaiTay
INSERT INTO [ChieuDaiTay] ([ten], [ngayTao], [ngaySua], [isDelete])
VALUES
  (N'Tên Chiều Dài Tay 1', GETDATE(), GETDATE(), 0),
  (N'Tên Chiều Dài Tay 2', GETDATE(), GETDATE(), 0),
  (N'Tên Chiều Dài Tay 3', GETDATE(), GETDATE(), 0);

-- Insert into DanhMuc
INSERT INTO [DanhMuc] ([ten], [ngayTao], [ngaySua], [isDelete])
VALUES
  (N'Tên Danh Mục 1', GETDATE(), GETDATE(), 0),
  (N'Tên Danh Mục 2', GETDATE(), GETDATE(), 0),
  (N'Tên Danh Mục 3', GETDATE(), GETDATE(), 0);

-- Insert into KichCo
INSERT INTO [KichCo] ([ten], [ngayTao], [ngaySua], [isDelete])
VALUES
  (N'Tên Kích Cỡ 1', GETDATE(), GETDATE(), 0),
  (N'Tên Kích Cỡ 2', GETDATE(), GETDATE(), 0),
  (N'Tên Kích Cỡ 3', GETDATE(), GETDATE(), 0);

-- Insert into ThuongHieu
INSERT INTO [ThuongHieu] ([ten], [ngayTao], [ngaySua], [isDelete])
VALUES
  (N'Tên Thương Hiệu 1', GETDATE(), GETDATE(), 0),
  (N'Tên Thương Hiệu 2', GETDATE(), GETDATE(), 0),
  (N'Tên Thương Hiệu 3', GETDATE(), GETDATE(), 0);

-- Insert into ChatLieu
INSERT INTO [ChatLieu] ([ten], [ngayTao], [ngaySua], [isDelete])
VALUES
  (N'Tên Chất Liệu 1', GETDATE(), GETDATE(), 0),
  (N'Tên Chất Liệu 2', GETDATE(), GETDATE(), 0),
  (N'Tên Chất Liệu 3', GETDATE(), GETDATE(), 0);

-- Insert into MauSac
INSERT INTO [MauSac] ([ten], [ngayTao], [ngaySua], [isDelete])
VALUES
  (N'Tên Màu Sắc 1', GETDATE(), GETDATE(), 0),
  (N'Tên Màu Sắc 2', GETDATE(), GETDATE(), 0),
  (N'Tên Màu Sắc 3', GETDATE(), GETDATE(), 0);

-- Insert into CoAo
INSERT INTO [CoAo] ([ten], [ngayTao], [ngaySua], [isDelete])
VALUES
  (N'Tên Cỡ Áo 1', GETDATE(), GETDATE(), 0),
  (N'Tên Cỡ Áo 2', GETDATE(), GETDATE(), 0),
  (N'Tên Cỡ Áo 3', GETDATE(), GETDATE(), 0);

  -- Insert into SanPhamChiTiet
INSERT INTO [SanPhamChiTiet] (
  [ma], [maVach], [idChatLieu], [idChieuDaiTay], [idKichCo],
  [idMauSac], [idSanPham], [idCoAo], [idXuatXu], [soLuong],
  [giaBan], [moTa], [ngayTao], [ngaySua], [trangThai]
)
VALUES
  ('SPCT_001', 'MaVach_001', 1, 2, 3, 2, 1, 1, 1, 100, 50000.00, N'Mô tả sản phẩm 1', GETDATE(), GETDATE(), 0),
  ('SPCT_002', 'MaVach_002', 2, 3, 1, 2, 2, 2, 2, 150, 75000.00, N'Mô tả sản phẩm 2', GETDATE(), GETDATE(), 1),
  ('SPCT_003', 'MaVach_003', 3, 1, 2, 3, 3, 3, 3, 200, 100000.00, N'Mô tả sản phẩm 3', GETDATE(), GETDATE(), 0);

  select * from SanPham
  select * from SanPhamChiTiet
  -- Insert into SanPham
INSERT INTO [SanPham] (
  [ma], [ten], [isDelete], [idDanhMuc], [idThuongHieu], [ngayTao], [ngaySua]
)
VALUES
  ('SP001', N'Sản phẩm 1', 0, 1, 1, GETDATE(), GETDATE()),
  ('SP002', N'Sản phẩm 2', 0, 2, 2, GETDATE(), GETDATE()),
  ('SP003', N'Sản phẩm 3', 0, 3, 3, GETDATE(), GETDATE());

