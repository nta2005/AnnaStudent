package nta.annastudent.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nta.annastudent.R;
import nta.annastudent.dao.TheLoaiDAO;
import nta.annastudent.model.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {
    Button btnThemTheLoai;
    TheLoaiDAO theloaiDAO;
    EditText edMa, edTen, edVi, edMo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloai);
        setTitle("THÊM THỂ LOẠI");
        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnThemTheLoai = findViewById(R.id.btnThem);
        edMa = findViewById(R.id.edMa);
        edTen = findViewById(R.id.edTen);
        edVi = findViewById(R.id.edVi);
        edMo = findViewById(R.id.edMo);
        TextView text = findViewById(R.id.text);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/svn-hello_sweets.otf");
        text.setTypeface(type);
    }

    public void showtheloai(View view) {
        finish();
    }

    public void quaylai(View view) {
        onBackPressed();
    }

    public void themtheloai(View view) {
        theloaiDAO = new TheLoaiDAO(TheLoaiActivity.this);
        TheLoai user = new TheLoai(edMa.getText().toString(), edTen.getText().toString(), edMo.getText().toString(), edVi.getText().toString());
        try {
            if (validateForm() > 0) {
                if (theloaiDAO.inserTheLoai(user) > 0) {
                    onBackPressed();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Lỗi:", ex.toString());
        }
    }

    public int validateForm() {
        int check = 1;
        if (edMa.getText().length() == 0 || edTen.getText().length() == 0
                || edVi.getText().length() == 0 || edMo.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
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
