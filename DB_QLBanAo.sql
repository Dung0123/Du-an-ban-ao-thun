create database QlBanAo1
use QlBanAo1
go
CREATE TABLE [CoAo] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  [nguoiTao] nvarchar(20) null,
  [nguoiSua] nvarchar(20) null,
  [isDelete] int null,
  PRIMARY KEY ([id])
);
go
CREATE TABLE [Voucher] (
  [id] UNIQUEIDENTIFIER  DEFAULT NEWID(),
  [IdnhanVien] UNIQUEIDENTIFIER null,
  [loaiVoucher] varchar(20) null,
  [giamGia%] int null,
  [diauKienApDung] varchar(20) null,
  [moTa] nvarchar(255) null,
  [ngayBatDau] Datetime null,
  [ngayKetThuc] Datetime null,
  [trangThai] int DEFAULT 0 null,
  [nguoiTao] nvarchar(30) null,
  [nguoiSua] nvarchar(30) null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  [isDelete] int,
  PRIMARY KEY ([id])
);
go
CREATE TABLE [chucVu] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [ma] varchar(20) null,
  [ten] nvarchar(30) null,
  [moTa] nvarchar(50) null,
  PRIMARY KEY ([id])
);
go
CREATE TABLE [NhanVien] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [IdChucVu] UNIQUEIDENTIFIER null,
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [email] varchar(20) null,
  [sdt] varchar(20) null,
  [matKhau] varchar(32) null,
  [diaChi] nvarchar(255) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_NhanVien.IdChucVu]
    FOREIGN KEY ([IdChucVu])
      REFERENCES [chucVu]([id])
);
go
drop table KhachHang
CREATE TABLE [KhachHang] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [sdt] varchar(20) null,
  [email] varchar(20) null,
  [gioiTinh] Bit null,
  ngaySinh Date,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  PRIMARY KEY ([id])
)
select id,ma,ten,sdt,email,gioiTinh,ngaySinh,trangThai from KhachHang

go
CREATE TABLE [HoaDon] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [IdnhanVien] UNIQUEIDENTIFIER null,
  [IdkhachHang] UNIQUEIDENTIFIER null,
  [IdhinhThucThanhToan] UNIQUEIDENTIFIER null,
  [sdt] varchar(20) null,
  [diaChi] nvarchar(255) null,
  [tongTien] Decimal(20,2) null,
  [tongTienGiam] Decimal(20,2) null,
  [ngayGiaoHang] Datetime null,
  [ngayNhan] Datetime null,
  [ngayMuonNhan] Datetime null,
  [loaiHoaDon] varchar(20) null,
  [tienGiaoHang] Decimal(20,2) null,
  [ngayTao] Datetime null,
  [nguoiTao] nvarchar(20) null,
  [nguoiSua] nvarchar(20) null,
  [trangThai] int DEFAULT 0 null,
  [IdVoucher] UNIQUEIDENTIFIER null,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_HoaDon.IdVoucher]
    FOREIGN KEY ([IdVoucher])
      REFERENCES [Voucher]([id]),
  CONSTRAINT [FK_HoaDon.IdkhachHang]
    FOREIGN KEY ([IdkhachHang])
      REFERENCES [KhachHang]([id]),
  CONSTRAINT [FK_HoaDon.IdnhanVien]
    FOREIGN KEY ([IdnhanVien])
      REFERENCES [NhanVien]([id])
);
go
CREATE TABLE [ChatLieu] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  [nguoiTao] nvarchar(20) null,
  [nguoiSua] nvarchar(20) null,
  [isDelete] int null,
  PRIMARY KEY ([id])
);
go
CREATE TABLE [MauSac] (
  [id] UNIQUEIDENTIFIER  DEFAULT NEWID(),
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [trangThai] int null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  [nguoiTao] nvarchar(20) null,
  [nguoiSua] nvarchar(20) null,
  [isDelete] int null,
  PRIMARY KEY ([id])
);
go
CREATE TABLE [ThanhToan] (
  [id] UNIQUEIDENTIFIER  DEFAULT NEWID(),
  [maGiaoDich] varchar(20) null,
  [hinhThuc] varchar(20) null,
  [moTa] nvarchar(255) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  PRIMARY KEY ([id])
);
go
CREATE TABLE [HinhThucThanhToan] (
  [id] UNIQUEIDENTIFIER  DEFAULT NEWID(),
  [IdThanhToan] UNIQUEIDENTIFIER null,
  [IdHoaDon] UNIQUEIDENTIFIER null,
  [moTa] nvarchar(255) null,
  [giaTri] Decimal(20,2) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_HinhThucThanhToan.IdHoaDon]
    FOREIGN KEY ([IdHoaDon])
      REFERENCES [HoaDon]([Id]),
  CONSTRAINT [FK_HinhThucThanhToan.IdThanhToan]
    FOREIGN KEY ([IdThanhToan])
      REFERENCES [ThanhToan]([id])
);
go
CREATE TABLE [ChieuDaiTay] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  [nguoiTao] nvarchar(20) null,
  [nguoiSua] nvarchar(20) null,
  [isDelete] int null,
  PRIMARY KEY ([id])
);
go
CREATE TABLE [KichCo](
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  [nguoiTao] nvarchar(20) null ,
  [nguoiSua] nvarchar(20)null,
  [isDelete] int null,
  PRIMARY KEY ([id])
);
go
CREATE TABLE [ThuongHieu] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  [nguoiTao] nvarchar(20) null,
  [nguoiSua] nvarchar(20) null,
  [isDelete] int null,
  PRIMARY KEY ([id])
);
go
CREATE TABLE [SanPham] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [ma] varchar(20) null,
  [ten] nvarchar(50) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  [nguoiTao] nvarchar(20) null,
  [nguoiSua] nvarchar(20) null,
  [isDelete] int null,
  [IdDanhmuc] UNIQUEIDENTIFIER null,
  [IdThuonghieu] UNIQUEIDENTIFIER null,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_SanPham.IdThuonghieu]
    FOREIGN KEY ([IdThuonghieu])
      REFERENCES [ThuongHieu]([id]),
	  CONSTRAINT [FK_SanPham.IdDanhmuc]
    FOREIGN KEY ([IdDanhmuc])
      REFERENCES [DanhMuc]([id])
);
go
CREATE TABLE [SanPhamChiTiet] (
  [id] UNIQUEIDENTIFIER DEFAULT NEWID(),
  [Id_chatLieu] UNIQUEIDENTIFIER null,
  Id_chieuDaiTay UNIQUEIDENTIFIER null,
  [id_kichCo] UNIQUEIDENTIFIER null,
  [id_mauSac] UNIQUEIDENTIFIER null,
  [id_sanPham] UNIQUEIDENTIFIER null,
  [id_coAo] UNIQUEIDENTIFIER null,
  [so_luong] int null,
  [gia_ban] Decimal(20,2) null,
  [mo_Ta] nvarchar(255) null,
  [xuat_Xu] nvarchar(20) null,
  [maQR] varchar(255)null,
  [trang_Thai] int DEFAULT 0 null,
  [nguoi_Tao] nvarchar(20) null,
  [nguoi_Sua] nvarchar(20) null,
  [isDelete] int null,
  PRIMARY KEY ([id]),
 
  CONSTRAINT [FK_SanPhamChiTiet.id_coAo]
    FOREIGN KEY ([id_coAo])
      REFERENCES [CoAo]([id]),
  CONSTRAINT [FK_SanPhamChiTiet.Id_chieuDaiTay]
    FOREIGN KEY (Id_chieuDaiTay)
      REFERENCES [ChieuDaiTay]([id]),
  CONSTRAINT [FK_SanPhamChiTiet.id_mauSac]
    FOREIGN KEY ([id_mauSac])
      REFERENCES [MauSac]([id]),
  CONSTRAINT [FK_SanPhamChiTiet.Id_chatLieu]
    FOREIGN KEY ([Id_chatLieu])
      REFERENCES [ChatLieu]([id]),
  CONSTRAINT [FK_SanPhamChiTiet.id_sanPham]
    FOREIGN KEY ([id_sanPham])
      REFERENCES [SanPham]([id]),
	  CONSTRAINT [FK_SanPhamChiTiet.id_kichCo]
    FOREIGN KEY ([id_kichCo])
      REFERENCES [KichCo]([id])
);
go
CREATE TABLE [HoaDonChiTiet] (
  [id] UNIQUEIDENTIFIER  DEFAULT NEWID(),
  [idSpChiTiet] UNIQUEIDENTIFIER null,
  [idHoaDon] UNIQUEIDENTIFIER null,
  [soLuong] int null,
  [giaTien] Decimal(20,2) null,
  [thanhTien] Decimal(20,2) null,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_HoaDonChiTiet.idSpChiTiet]
    FOREIGN KEY ([idSpChiTiet])
      REFERENCES [SanPhamChiTiet]([id]),
  CONSTRAINT [FK_HoaDonChiTiet.idHoaDon]
    FOREIGN KEY ([idHoaDon])
      REFERENCES [HoaDon]([id])
);
go
CREATE TABLE [DanhMuc] (
  [id] UNIQUEIDENTIFIER  DEFAULT NEWID(),
  [maDanhMuc] varchar(20) null,
  [tenDanhMuc] nvarchar(50) null,
  [trangThai] int DEFAULT 0 null,
  [ngayTao] Datetime null,
  [ngaySua] Datetime null,
  [nguoiTao] nvarchar(20) null,
  [nguoiSua] nvarchar(20) null,
  [isDelete] int null,
  PRIMARY KEY ([id])
);


