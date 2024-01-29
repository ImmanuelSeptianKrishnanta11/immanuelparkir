import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Kendaraan {

    private String platNomor;
    private LocalDate tanggalMasuk;
    private LocalDate tanggalKeluar;

    public Kendaraan(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(LocalDate tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public LocalDate getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(LocalDate tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public boolean isCheckOut() {
        return tanggalKeluar != null;
    }

    public boolean isCheckIn() {
        return tanggalMasuk != null;
    }

    public String hitungBiayaParkir() {
        // Hitung waktu parkir
        int waktuParkir = (int) ((this.tanggalKeluar.toEpochDay() - this.tanggalMasuk.toEpochDay()) * 24 * 60);

        // Hitung biaya parkir
        int biayaParkir = 0;
        if (waktuParkir <= 10) {
            // Gratis
        } else {
            biayaParkir = 10000;
            waktuParkir -= 10;

            // Hitung biaya parkir per jam
            biayaParkir += 5000 * waktuParkir / 60;
        }

        // Konversi biaya parkir ke string
        return String.format("Rp%,d", biayaParkir);
    }
}

class Parkir {

    private List<Kendaraan> kendaraanSedangParkir = new ArrayList<>();
    private List<Kendaraan> kendaraanPernahParkir = new ArrayList<>();

    public void checkIn(Kendaraan kendaraan) {
        kendaraan.setTanggalMasuk(LocalDate.now());
        kendaraanSedangParkir.add(kendaraan);
        System.out.println("Kendaraan " + kendaraan.getPlatNomor() + " check in.");
    }

    public boolean checkOut(Kendaraan kendaraan) {
        kendaraan.setTanggalKeluar(LocalDate.now());
        kendaraanSedangParkir.remove(kendaraan);
        kendaraanPernahParkir.add(kendaraan);

        System.out.println("Kendaraan " + kendaraan.getPlatNomor() + " check out pada " + kendaraan.getTanggalKeluar());

        return true;
    }

    public List<Kendaraan> getKendaraanSedangParkir() {
        return kendaraanSedangParkir;
    }

    public List<Kendaraan> getKendaraanPernahParkir() {
        return kendaraanPernahParkir;
    }

    public void cetakStruk(Kendaraan kendaraan) {

        // Tambahkan header
        System.out.println("Struk Parkir");

        // Tampilkan informasi kendaraan
        System.out.println("Plat Nomor: " + kendaraan.getPlatNomor());
        System.out.println("Waktu Masuk: " + kendaraan.getTanggalMasuk());
        System.out.println("Waktu Keluar: " + kendaraan.getTanggalKeluar());

        // Tambahkan kolom tanggal check in dan check out
        System.out.println("Tanggal Check In: " + kendaraan.getTanggalMasuk());
        System.out.println("Tanggal Check Out: " + kendaraan.getTanggalKeluar());

        // Tampilkan total biaya parkir
        System.out.println("Total Biaya: Rp" + kendaraan.hitungBiayaParkir());

        // Tambahkan footer
        System.out.println("Terima kasih telah menggunakan layanan kami.");
    }


    public static void main(String[] args) {
        Parkir parkir = new Parkir();
        Scanner scanner = new Scanner(System.in);

        // Menu utama
        System.out.println("Selamat datang di sistem parkir!");
        System.out.println("1. Check in kendaraan");
        System.out.println("2. Check out kendaraan");
        System.out.println("3. Tampilkan daftar kendaraan parkir");
        System.out.println("4. Tampilkan riwayat parkir");
        System.out.println("5. Cetak struk");
        System.out.println("6. Keluar");

        // Looping utama
        while (true) {
            // Pilihan pengguna
            System.out.print("Masukkan pilihan Anda: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    // Check in kendaraan
                    System.out.print("Masukkan plat nomor kendaraan: ");
                    String platNomor = scanner.next();
                    Kendaraan kendaraan2 = new Kendaraan(platNomor);
                    parkir.checkIn(kendaraan2);
                    break;
                case 2:
                    // Check out kendaraan
                    System.out.print("Masukkan plat nomor kendaraan: ");
                    platNomor = scanner.next();
                    kendaraan2 = new Kendaraan(platNomor);
                    parkir.checkOut(kendaraan2);
                    break;
                case 3:
                    // Tampilkan daftar kendaraan parkir
                    System.out.println("Daftar kendaraan yang sedang parkir:");
                    for (Kendaraan kendaraan1 : parkir.getKendaraanSedangParkir()) {
                        System.out.println("• Plat nomor: " + kendaraan1.getPlatNomor());
                    }
                    break;
                case 4:
                    // Tampilkan riwayat parkir
                    System.out.println("Riwayat parkir:");
                    for (Kendaraan kendaraan1 : parkir.getKendaraanPernahParkir()) {
                        System.out.println("• Plat nomor: " + kendaraan1.getPlatNomor() + ", check in pada " + kendaraan1.getTanggalMasuk() + ", check out pada " + kendaraan1.getTanggalKeluar());
                    }
                    break;
                case 5:
                    // Cetak struk
                    System.out.print("Masukkan plat nomor kendaraan: ");
                    platNomor = scanner.next();
                    kendaraan2 = new Kendaraan(platNomor);
                    parkir.cetakStruk(kendaraan2);
                    break;
                case 6:
                    // Keluar
                    System.out.println("Terima kasih telah menggunakan sistem parkir kami.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

