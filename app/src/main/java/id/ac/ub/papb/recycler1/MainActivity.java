package id.ac.ub.papb.recycler1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv1;
    Button bt1;
    EditText etNim, etNama;
    public static String TAG = "RV1";
    MahasiswaAdapter adapter;
    ArrayList<Mahasiswa> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        rv1 = findViewById(R.id.rv1);
        bt1 = findViewById(R.id.bt1);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.editTextTextPersonName2);

        // Inisialisasi data awal
        data = getData();

        // Setup RecyclerView
        adapter = new MahasiswaAdapter(this, data);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        // Set event listener untuk tombol SIMPAN
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = etNim.getText().toString();
                String nama = etNama.getText().toString();

                // Validasi input
                if (nim.isEmpty() || nama.isEmpty()) {
                    Toast.makeText(MainActivity.this, "NIM dan Nama harus diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Buat objek Mahasiswa baru
                Mahasiswa newMahasiswa = new Mahasiswa();
                newMahasiswa.nim = nim;
                newMahasiswa.nama = nama;

                // Tambahkan ke data list dan update RecyclerView
                data.add(newMahasiswa);
                adapter.notifyItemInserted(data.size() - 1);

                // Log data yang ditambahkan
                Log.d(TAG, "Added Mahasiswa: NIM = " + newMahasiswa.nim + ", Nama = " + newMahasiswa.nama);

                // Kosongkan EditText setelah menambah data
                etNim.setText("");
                etNama.setText("");
            }
        });
    }

    public ArrayList getData() {
        ArrayList<Mahasiswa> data = new ArrayList<>();
        List<String> nim = Arrays.asList(getResources().getStringArray(R.array.nim));
        List<String> nama = Arrays.asList(getResources().getStringArray(R.array.nama));
        for (int i = 0; i < nim.size(); i++) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.nim = nim.get(i);
            mhs.nama = nama.get(i);
            Log.d(TAG,"getData "+mhs.nim);
            data.add(mhs);
        }
        return data;
    }
}