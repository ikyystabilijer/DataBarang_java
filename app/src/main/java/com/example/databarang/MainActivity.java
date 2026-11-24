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

    private EditText etQty1;
    private EditText etQty2;

    // Data Barang 1
    private final String namaBarang1 = "Sepatu Sneakers Premium";
    private final int hargaBarang1 = 250000;
    private final String keteranganBarang1 = "Sepatu sneakers berkualitas tinggi dengan bahan lembut, sol karet anti licin, dan desain modern yang nyaman digunakan untuk aktivitas sehari-hari.";
    private final int imageResId1 = R.drawable.jordan;

    // Data Barang 2 (Baru)
    private final String namaBarang2 = "Tas Ransel Backpack Casual";
    private final int hargaBarang2 = 150000;
    private final String keteranganBarang2 = "Tas ransel kasual berkualitas tinggi dengan kompartemen luas, bahan tahan air (waterproof), serta desain stylish yang cocok untuk sekolah, kuliah, maupun kerja.";
    private final int imageResId2 = R.drawable.tas;

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
        etQty1 = findViewById(R.id.etQty1);
        Button btnPlus1 = findViewById(R.id.btnPlus1);
        Button btnMinus1 = findViewById(R.id.btnMinus1);
        Button btnCheckout1 = findViewById(R.id.btnCheckout1);

        ivFotoBarang1.setImageResource(imageResId1);
        tvNamaBarang1.setText(namaBarang1);
        tvHargaBarang1.setText(formatRupiah(hargaBarang1));
        tvKeteranganBarang1.setText(keteranganBarang1);

        btnPlus1.setOnClickListener(v -> {
            int qty = getQtyValue(etQty1) + 1;
            etQty1.setText(String.valueOf(qty));
        });

        btnMinus1.setOnClickListener(v -> {
            int qty = getQtyValue(etQty1);
            if (qty > 1) {
                qty--;
                etQty1.setText(String.valueOf(qty));
            } else {
                Toast.makeText(MainActivity.this, "Jumlah unit minimal 1", Toast.LENGTH_SHORT).show();
            }
        });

        btnCheckout1.setOnClickListener(v -> prosesCheckout(namaBarang1, hargaBarang1, etQty1, imageResId1));


        // ================= INISIALISASI PRODUK 2 =================
        ImageView ivFotoBarang2 = findViewById(R.id.ivFotoBarang2);
        TextView tvNamaBarang2 = findViewById(R.id.tvNamaBarang2);
        TextView tvHargaBarang2 = findViewById(R.id.tvHargaBarang2);
        TextView tvKeteranganBarang2 = findViewById(R.id.tvKeteranganBarang2);
        etQty2 = findViewById(R.id.etQty2);
        Button btnPlus2 = findViewById(R.id.btnPlus2);
        Button btnMinus2 = findViewById(R.id.btnMinus2);
        Button btnCheckout2 = findViewById(R.id.btnCheckout2);

        ivFotoBarang2.setImageResource(imageResId2);
        tvNamaBarang2.setText(namaBarang2);
        tvHargaBarang2.setText(formatRupiah(hargaBarang2));
        tvKeteranganBarang2.setText(keteranganBarang2);

        btnPlus2.setOnClickListener(v -> {
            int qty = getQtyValue(etQty2) + 1;
            etQty2.setText(String.valueOf(qty));
        });

        btnMinus2.setOnClickListener(v -> {
            int qty = getQtyValue(etQty2);
            if (qty > 1) {
                qty--;
                etQty2.setText(String.valueOf(qty));
            } else {
                Toast.makeText(MainActivity.this, "Jumlah unit minimal 1", Toast.LENGTH_SHORT).show();
            }
        });

        btnCheckout2.setOnClickListener(v -> prosesCheckout(namaBarang2, hargaBarang2, etQty2, imageResId2));
    }

    private void prosesCheckout(String nama, int harga, EditText editTextQty, int imageRes) {
        String qtyStr = editTextQty.getText().toString().trim();
        if (TextUtils.isEmpty(qtyStr)) {
            Toast.makeText(MainActivity.this, "Masukkan jumlah unit terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        int qty = Integer.parseInt(qtyStr);
        if (qty <= 0) {
            Toast.makeText(MainActivity.this, "Jumlah unit harus minimal 1", Toast.LENGTH_SHORT).show();
            return;
        }

        int totalHarga = harga * qty;

        // Mengirim data barang ke Halaman Bayar via Intent
        Intent intent = new Intent(MainActivity.this, BayarActivity.class);
        intent.putExtra("NAMA_BARANG", nama);
        intent.putExtra("HARGA_BARANG", harga);
        intent.putExtra("QTY", qty);
        intent.putExtra("TOTAL_HARGA", totalHarga);
        intent.putExtra("IMAGE_RES_ID", imageRes);
        startActivity(intent);
    }

    private int getQtyValue(EditText editText) {
        String input = editText.getText().toString().trim();
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