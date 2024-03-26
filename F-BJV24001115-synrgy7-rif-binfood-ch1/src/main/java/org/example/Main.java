package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Array menu dan harga
        String[] menu = {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"};
        int[] harga = {15000, 13000, 18000, 3000, 5000};

        ArrayList<Pesanan> daftarPesanan = new ArrayList<>();
        inputPesanan(daftarPesanan, menu, harga);
        konfirmasiPembayaran(daftarPesanan, menu, harga);
    }

    public static void inputPesanan(ArrayList<Pesanan> daftarPesanan, String[] menu, int[] harga) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("==========================");
            System.out.println("Selamat datang di Binarfud");
            System.out.println("==========================\n");
            System.out.println("Silahkan pilih makanan :");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ". " + menu[i] + "\t|\t" + harga[i]);
            }
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.print("=> ");
            pilihan = scanner.nextInt();

            if (pilihan > 0 && pilihan <= menu.length) {
                System.out.println("==========================");
                System.out.println("Berapa pesanan anda");
                System.out.println("==========================\n");
                System.out.println(menu[pilihan - 1] + "\t|\t" + harga[pilihan - 1]);
                System.out.println("input 0 untuk kembali");
                System.out.print("=> ");
                int jumlah = scanner.nextInt();
                if (jumlah > 0) {
                    tambahPesanan(daftarPesanan, pilihan - 1, jumlah);
                    System.out.println("Pesanan berhasil ditambahkan.");
                }
            } else if (pilihan == 0) {
                System.exit(0);
            } else if (pilihan != 99) {
                System.out.println("Menu tidak valid!");
            }
        } while (pilihan != 99);
    }

    public static void tambahPesanan(ArrayList<Pesanan> daftarPesanan, int kodeMenu, int jumlah) {
        daftarPesanan.add(new Pesanan(kodeMenu, jumlah));
    }

    public static void cetakKwitansi(ArrayList<Pesanan> daftarPesanan, String[] menu, int[] harga) {
        try {
            FileWriter writer = new FileWriter("kwitansi.txt");
            writer.write("==========================\n");
            writer.write("BinarFud\n");
            writer.write("==========================\n\n");
            writer.write("Terima kasih sudah memesan\n");
            writer.write("di BinarFud\n\n");
            writer.write("Dibawah ini adalah pesanan anda\n\n");
            int totalHargaSemua = 0;
            int totalJumlahSemua = 0;
            for (Pesanan pesanan : daftarPesanan) {
                int kodeMenu = pesanan.getKodeMenu();
                int jumlah = pesanan.getJumlah();
                int hargaSatuan = harga[kodeMenu];
                int totalHarga = hargaSatuan * jumlah;
                totalJumlahSemua += jumlah;
                totalHargaSemua += totalHarga;
                writer.write(menu[kodeMenu] + "\t" + jumlah + "\t" + "\t" + totalHarga + "\n");
            }
            writer.write("-------------------------------+\n");
            writer.write("Total\t\t" + totalJumlahSemua + "\t\t" + totalHargaSemua + "\n\n");
            writer.write("Pembayaran : BinarCash\n\n");
            writer.write("==========================\n");
            writer.write("Simpan struk ini sebagai\n");
            writer.write("bukti pembayaran\n");
            writer.write("==========================\n\n");
            writer.close();
            System.out.println("Kwitansi telah berhasil disimpan dalam file kwitansi.txt");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis file: " + e.getMessage());
        }
    }

    public static void konfirmasiPembayaran(ArrayList<Pesanan> daftarPesanan, String[] menu, int[] harga) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        System.out.println("==========================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("==========================\n");
        int totalHargaSemua = 0;
        int totalJumlahSemua = 0;
        for (Pesanan pesanan : daftarPesanan) {
            int kodeMenu = pesanan.getKodeMenu();
            int jumlah = pesanan.getJumlah();
            int hargaSatuan = harga[kodeMenu];
            int totalHarga = hargaSatuan * jumlah;
            totalJumlahSemua += jumlah;
            totalHargaSemua += totalHarga;
            System.out.println(menu[kodeMenu] + "\t" + jumlah + "\t" + "\t" + totalHarga);
        }
        System.out.println("-------------------------------+");
        System.out.println("Total\t\t" + totalJumlahSemua + "\t\t" + totalHargaSemua + "\n");
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
        System.out.print("=> ");
        pilihan = scanner.nextInt();
        switch (pilihan) {
            case 1:
                cetakKwitansi(daftarPesanan, menu, harga);
                break;
            case 2:
                inputPesanan(daftarPesanan, menu, harga);
                break;
            case 0:
                System.exit(0);
        }
    }

}

class Pesanan {
    private int kodeMenu;
    private int jumlah;

    public Pesanan(int kodeMenu, int jumlah) {
        this.kodeMenu = kodeMenu;
        this.jumlah = jumlah;
    }

    public int getKodeMenu() {
        return kodeMenu;
    }

    public int getJumlah() {
        return jumlah;
    }
}
