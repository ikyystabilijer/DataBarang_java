package com.example.databarang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.text.NumberFormat;
import java.util.Locale;

public class BayarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Matikan dark mode khusus aplikasi ini agar tampilan selalu terang/putih
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        ImageView ivFotoBarangBayar = findViewById(R.id.ivFotoBarangBayar);
        TextView tvNamaBarangBayar = findViewById(R.id.tvNamaBarangBayar);
        TextView tvHargaSatuanBayar = findViewById(R.id.tvHargaSatuanBayar);
        TextView tvQtyBayar = findViewById(R.id.tvQtyBayar);
        TextView tvHargaPerUnitDetail = findViewById(R.id.tvHargaPerUnitDetail);
        TextView tvTotalHargaBayar = findViewById(R.id.tvTotalHargaBayar);
        Button btnProsesBayar = findViewById(R.id.btnProsesBayar);
        Button btnKembali = findViewById(R.id.btnKembali);

        // Ambil Data Intent dari MainActivity
        Intent intent = getIntent();
        String namaBarang = intent.getStringExtra("NAMA_BARANG");
        int hargaBarang = intent.getIntExtra("HARGA_BARANG", 0);
        int qty = intent.getIntExtra("QTY", 1);
        int totalHarga = intent.getIntExtra("TOTAL_HARGA", 0);
        int imageResId = intent.getIntExtra("IMAGE_RES_ID", R.drawable.jordan);

        // Tampilkan Data ke View
        if (namaBarang != null) {
            tvNamaBarangBayar.setText(namaBarang);
        }
        ivFotoBarangBayar.setImageResource(imageResId);
        String labelHargaSatuan = "Harga: " + formatRupiah(hargaBarang);
        tvHargaSatuanBayar.setText(labelHargaSatuan);

        String textQty = qty + " Unit";
        tvQtyBayar.setText(textQty);

        tvHargaPerUnitDetail.setText(formatRupiah(hargaBarang));
        tvTotalHargaBayar.setText(formatRupiah(totalHarga));

        // Event Tombol Bayar
        btnProsesBayar.setOnClickListener(v -> Toast.makeText(BayarActivity.this,
                "Pembayaran sebesar " + formatRupiah(totalHarga) + " berhasil diselesaikan!",
                Toast.LENGTH_LONG).show());

        // Event Tombol Kembali
        btnKembali.setOnClickListener(v -> finish());
    }

    private String formatRupiah(int number) {
        Locale localeID = Locale.forLanguageTag("id-ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
        return format.format(number);
    }
}