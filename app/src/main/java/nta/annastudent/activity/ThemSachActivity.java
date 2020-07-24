package nta.annastudent.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nta.annastudent.R;
import nta.annastudent.dao.SachDAO;
import nta.annastudent.dao.TheLoaiDAO;
import nta.annastudent.model.Sach;
import nta.annastudent.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ThemSachActivity extends AppCompatActivity {
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spnTheLoai;
    Button btnADDBOOK;
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsach);
        setTitle("THÊM SÁCH");
        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spnTheLoai = findViewById(R.id.spnTheLoai);
        getTheLoai();
        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTenSach);
        edNXB = findViewById(R.id.edNXB);
        edTacGia = findViewById(R.id.edTacGia);
        edGiaBia = findViewById(R.id.edGiaBia);
        edSoLuong = findViewById(R.id.edSoLuong);
        btnADDBOOK = findViewById(R.id.btnAddBook);
        TextView text = findViewById(R.id.text);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/svn-hello_sweets.otf");
        text.setTypeface(type);
        //
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheloai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaSach.setText(b.getString("MASACH"));
            String maTheLoai = b.getString("MATHELOAI");
            edTenSach.setText(b.getString("TENSACH"));
            edNXB.setText(b.getString("NXB"));
            edTacGia.setText(b.getString("TACGIA"));
            edGiaBia.setText(b.getString("GIABIA"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
        }

    }

    public void showSpinner(View view) {
        sachDAO = new SachDAO(ThemSachActivity.this);
        sachDAO.getAllSach();
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(ThemSachActivity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }

    public void addBook(View view) {
        try {
            sachDAO = new SachDAO(ThemSachActivity.this);
            Sach sach = new Sach(edMaSach.getText().toString(), maTheLoai, edTenSach.getText().toString(), edTacGia.getText().toString(), edNXB.getText().toString(), parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText().toString()));
            if (sachDAO.inserSach(sach) > 0) {
                onBackPressed();
                Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception ex) {
            String s = edGiaBia.getText().toString();
            String t = edSoLuong.getText().toString();
            if (edMaSach.getText().length() == 0 || edTenSach.getText().length() == 0
                    || edTacGia.getText().length() == 0 || edNXB.getText().length() == 0
                    || edGiaBia.getText().length() == 0 || edSoLuong.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Bạn chưa nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            }
            try {
                Double.parseDouble(s);
                Integer.parseInt(t);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Kiểm tra định dạng giá bán và số lượng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showBook(View view) {
        finish();
    }

    public void cancel(View view) {
        finish();
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (strTheLoai.equals(listTheLoai.get(i).getMaTheloai())) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}