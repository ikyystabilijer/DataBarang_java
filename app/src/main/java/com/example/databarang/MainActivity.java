package com.example.databarang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Data Produk 1
    private final String namaBarang1 = "Sepatu Sneakers Premium";
    private final int hargaBarang1 = 250000;
    private final String keteranganBarang1 = "Sepatu sneakers berkualitas tinggi dengan bahan lembut, sol karet anti licin, dan desain modern yang sangat nyaman digunakan untuk aktivitas sehari-hari.";
    private final int imageResId1 = R.drawable.jordan;

    // Data Produk 2
    private final String namaBarang2 = "Tas Ransel Backpack Casual";
    private final int hargaBarang2 = 150000;
    private final String keteranganBarang2 = "Tas ransel kasual berkualitas tinggi dengan kompartemen luas, bahan tahan air (waterproof), serta desain stylish yang cocok untuk sekolah, kuliah, maupun kerja.";
    private final int imageResId2 = R.drawable.tas;

    // Data Produk 3
    private final String namaBarang3 = "Smartwatch Digital Sport";
    private final int hargaBarang3 = 350000;
    private final String keteranganBarang3 = "Smartwatch olahraga multifungsi dengan pengukur detak jantung, pemantau tidur, penghitung langkah, serta daya tahan baterai hingga 7 hari.";
    private final int imageResId3 = R.drawable.smartwatch;

    // Data Produk 4
    private final String namaBarang4 = "Headphone Wireless Bluetooth";
    private final int hargaBarang4 = 450000;
    private final String keteranganBarang4 = "Headphone nirkabel dengan teknologi Active Noise Cancelling (ANC), suara bass mendalam, serta bantalan telinga empuk yang nyaman digunakan berjam-jam.";
    private final int imageResId4 = R.drawable.headphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Matikan dark mode khusus aplikasi ini agar tampilan selalu terang/putih
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ================= INISIALISASI PRODUK 1 =================
        ImageView ivFotoBarang1 = findViewById(R.id.ivFotoBarang1);
        TextView tvNamaBarang1 = findViewById(R.id.tvNamaBarang1);
        TextView tvHargaBarang1 = findViewById(R.id.tvHargaBarang1);
        TextView tvKeteranganBarang1 = findViewById(R.id.tvKeteranganBarang1);
        Button btnDetail1 = findViewById(R.id.btnDetail1);

        ivFotoBarang1.setImageResource(imageResId1);
        tvNamaBarang1.setText(namaBarang1);
        tvHargaBarang1.setText(formatRupiah(hargaBarang1));
        tvKeteranganBarang1.setText(keteranganBarang1);

        btnDetail1.setOnClickListener(v -> bukaDetailBarang(namaBarang1, hargaBarang1, keteranganBarang1, imageResId1));

        // ================= INISIALISASI PRODUK 2 =================
        ImageView ivFotoBarang2 = findViewById(R.id.ivFotoBarang2);
        TextView tvNamaBarang2 = findViewById(R.id.tvNamaBarang2);
        TextView tvHargaBarang2 = findViewById(R.id.tvHargaBarang2);
        TextView tvKeteranganBarang2 = findViewById(R.id.tvKeteranganBarang2);
        Button btnDetail2 = findViewById(R.id.btnDetail2);

        ivFotoBarang2.setImageResource(imageResId2);
        tvNamaBarang2.setText(namaBarang2);
        tvHargaBarang2.setText(formatRupiah(hargaBarang2));
        tvKeteranganBarang2.setText(keteranganBarang2);

        btnDetail2.setOnClickListener(v -> bukaDetailBarang(namaBarang2, hargaBarang2, keteranganBarang2, imageResId2));

        // ================= INISIALISASI PRODUK 3 =================
        ImageView ivFotoBarang3 = findViewById(R.id.ivFotoBarang3);
        TextView tvNamaBarang3 = findViewById(R.id.tvNamaBarang3);
        TextView tvHargaBarang3 = findViewById(R.id.tvHargaBarang3);
        TextView tvKeteranganBarang3 = findViewById(R.id.tvKeteranganBarang3);
        Button btnDetail3 = findViewById(R.id.btnDetail3);

        ivFotoBarang3.setImageResource(imageResId3);
        tvNamaBarang3.setText(namaBarang3);
        tvHargaBarang3.setText(formatRupiah(hargaBarang3));
        tvKeteranganBarang3.setText(keteranganBarang3);

        btnDetail3.setOnClickListener(v -> bukaDetailBarang(namaBarang3, hargaBarang3, keteranganBarang3, imageResId3));

        // ================= INISIALISASI PRODUK 4 =================
        ImageView ivFotoBarang4 = findViewById(R.id.ivFotoBarang4);
        TextView tvNamaBarang4 = findViewById(R.id.tvNamaBarang4);
        TextView tvHargaBarang4 = findViewById(R.id.tvHargaBarang4);
        TextView tvKeteranganBarang4 = findViewById(R.id.tvKeteranganBarang4);
        Button btnDetail4 = findViewById(R.id.btnDetail4);

        ivFotoBarang4.setImageResource(imageResId4);
        tvNamaBarang4.setText(namaBarang4);
        tvHargaBarang4.setText(formatRupiah(hargaBarang4));
        tvKeteranganBarang4.setText(keteranganBarang4);

        btnDetail4.setOnClickListener(v -> bukaDetailBarang(namaBarang4, hargaBarang4, keteranganBarang4, imageResId4));
    }

    private void bukaDetailBarang(String nama, int harga, String keterangan, int imageRes) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("NAMA_BARANG", nama);
        intent.putExtra("HARGA_BARANG", harga);
        intent.putExtra("KETERANGAN_BARANG", keterangan);
        intent.putExtra("IMAGE_RES_ID", imageRes);
        startActivity(intent);
    }

    public static String formatRupiah(int number) {
        Locale localeID = Locale.forLanguageTag("id-ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
        return format.format(number);
    }
}