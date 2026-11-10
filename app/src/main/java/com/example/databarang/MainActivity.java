package com.example.databarang;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etQty;

    // Data barang
    private final String namaBarang = "Sepatu Sneakers Premium";
    private final int hargaBarang = 250000;
    private final String keteranganBarang = "Sepatu sneakers berkualitas tinggi dengan bahan lembut, sol karet anti licin, dan desain modern yang nyaman digunakan untuk aktivitas sehari-hari.";
    private final int imageResId = R.drawable.jordan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Matikan dark mode khusus aplikasi ini agar tampilan selalu terang/putih
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi View
        ImageView ivFotoBarang = findViewById(R.id.ivFotoBarang);
        TextView tvNamaBarang = findViewById(R.id.tvNamaBarang);
        TextView tvHargaBarang = findViewById(R.id.tvHargaBarang);
        TextView tvKeteranganBarang = findViewById(R.id.tvKeteranganBarang);
        etQty = findViewById(R.id.etQty);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnCheckout = findViewById(R.id.btnCheckout);

        // Set Data ke Tampilan
        ivFotoBarang.setImageResource(imageResId);
        tvNamaBarang.setText(namaBarang);
        tvHargaBarang.setText(formatRupiah(hargaBarang));
        tvKeteranganBarang.setText(keteranganBarang);

        // Tombol Tambah Qty (+)
        btnPlus.setOnClickListener(v -> {
            int qty = getQtyValue() + 1;
            etQty.setText(String.valueOf(qty));
        });

        // Tombol Kurang Qty (-)
        btnMinus.setOnClickListener(v -> {
            int qty = getQtyValue();
            if (qty > 1) {
                qty--;
                etQty.setText(String.valueOf(qty));
            } else {
                Toast.makeText(MainActivity.this, "Jumlah unit minimal 1", Toast.LENGTH_SHORT).show();
            }
        });

        // Tombol Checkout -> Pindah ke Halaman Bayar via Intent
        btnCheckout.setOnClickListener(v -> {
            String qtyStr = etQty.getText().toString().trim();
            if (TextUtils.isEmpty(qtyStr)) {
                Toast.makeText(MainActivity.this, "Masukkan jumlah unit terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }

            int qty = Integer.parseInt(qtyStr);
            if (qty <= 0) {
                Toast.makeText(MainActivity.this, "Jumlah unit harus minimal 1", Toast.LENGTH_SHORT).show();
                return;
            }

            int totalHarga = hargaBarang * qty;

            // Mengirim data ke Halaman Bayar via Intent
            Intent intent = new Intent(MainActivity.this, BayarActivity.class);
            intent.putExtra("NAMA_BARANG", namaBarang);
            intent.putExtra("HARGA_BARANG", hargaBarang);
            intent.putExtra("QTY", qty);
            intent.putExtra("TOTAL_HARGA", totalHarga);
            intent.putExtra("IMAGE_RES_ID", imageResId);
            startActivity(intent);
        });
    }

    private int getQtyValue() {
        String input = etQty.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            return 1;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public static String formatRupiah(int number) {
        Locale localeID = Locale.forLanguageTag("id-ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
        return format.format(number);
    }
}