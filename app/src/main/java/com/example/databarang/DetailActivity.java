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

public class DetailActivity extends AppCompatActivity {

    private EditText etQtyDetail;
    private String namaBarang;
    private int hargaBarang;
    private String keteranganBarang;
    private int imageResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Matikan dark mode khusus aplikasi ini agar tampilan selalu terang/putih
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ivFotoBarangDetail = findViewById(R.id.ivFotoBarangDetail);
        TextView tvNamaBarangDetail = findViewById(R.id.tvNamaBarangDetail);
        TextView tvHargaBarangDetail = findViewById(R.id.tvHargaBarangDetail);
        TextView tvKeteranganBarangDetail = findViewById(R.id.tvKeteranganBarangDetail);
        etQtyDetail = findViewById(R.id.etQtyDetail);
        Button btnPlusDetail = findViewById(R.id.btnPlusDetail);
        Button btnMinusDetail = findViewById(R.id.btnMinusDetail);
        Button btnCheckoutDetail = findViewById(R.id.btnCheckoutDetail);

        // Ambil Data dari Intent (dari MainActivity)
        Intent intent = getIntent();
        namaBarang = intent.getStringExtra("NAMA_BARANG");
        hargaBarang = intent.getIntExtra("HARGA_BARANG", 0);
        keteranganBarang = intent.getStringExtra("KETERANGAN_BARANG");
        imageResId = intent.getIntExtra("IMAGE_RES_ID", R.drawable.ic_product);

        // Tampilkan ke View
        if (namaBarang != null) {
            tvNamaBarangDetail.setText(namaBarang);
        }
        tvHargaBarangDetail.setText(formatRupiah(hargaBarang));
        if (keteranganBarang != null) {
            tvKeteranganBarangDetail.setText(keteranganBarang);
        }
        ivFotoBarangDetail.setImageResource(imageResId);

        // Tombol Qty (+)
        btnPlusDetail.setOnClickListener(v -> {
            int qty = getQtyValue() + 1;
            etQtyDetail.setText(String.valueOf(qty));
        });

        // Tombol Qty (-)
        btnMinusDetail.setOnClickListener(v -> {
            int qty = getQtyValue();
            if (qty > 1) {
                qty--;
                etQtyDetail.setText(String.valueOf(qty));
            } else {
                Toast.makeText(DetailActivity.this, "Jumlah unit minimal 1", Toast.LENGTH_SHORT).show();
            }
        });

        // Tombol Checkout -> Ke Halaman Bayar (BayarActivity)
        btnCheckoutDetail.setOnClickListener(v -> {
            String qtyStr = etQtyDetail.getText().toString().trim();
            if (TextUtils.isEmpty(qtyStr)) {
                Toast.makeText(DetailActivity.this, "Masukkan jumlah unit terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }

            int qty = Integer.parseInt(qtyStr);
            if (qty <= 0) {
                Toast.makeText(DetailActivity.this, "Jumlah unit harus minimal 1", Toast.LENGTH_SHORT).show();
                return;
            }

            int totalHarga = hargaBarang * qty;

            Intent intentBayar = new Intent(DetailActivity.this, BayarActivity.class);
            intentBayar.putExtra("NAMA_BARANG", namaBarang);
            intentBayar.putExtra("HARGA_BARANG", hargaBarang);
            intentBayar.putExtra("QTY", qty);
            intentBayar.putExtra("TOTAL_HARGA", totalHarga);
            intentBayar.putExtra("IMAGE_RES_ID", imageResId);
            startActivity(intentBayar);
        });
    }

    private int getQtyValue() {
        String input = etQtyDetail.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            return 1;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private String formatRupiah(int number) {
        Locale localeID = Locale.forLanguageTag("id-ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
        return format.format(number);
    }
}