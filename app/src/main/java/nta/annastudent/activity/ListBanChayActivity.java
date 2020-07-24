package nta.annastudent.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nta.annastudent.R;
import nta.annastudent.adapter.SachAdapter;
import nta.annastudent.dao.SachDAO;
import nta.annastudent.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListBanChayActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDAO sachDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("BÁN CHẠY");
        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        setContentView(R.layout.activity_list_banchay);
        lvBook = findViewById(R.id.lvBookTop);
        edThang = findViewById(R.id.edThang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView text = findViewById(R.id.text);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/svn-hello_sweets.otf");
        text.setTypeface(type);
    }

    public void VIEW_SACH_TOP_10(View view) {
        try {
            if (Integer.parseInt(edThang.getText().toString()) > 13 || Integer.parseInt(edThang.getText().toString()) < 0) {
                Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng (1-12)!", Toast.LENGTH_SHORT).show();
            } else {
                sachDAO = new SachDAO(ListBanChayActivity.this);
                dsSach = sachDAO.getSachTop10(edThang.getText().toString());
                adapter = new SachAdapter(this, dsSach);
                lvBook.setAdapter(adapter);
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Lỗi nhập không đúng kí tự!", Toast.LENGTH_SHORT).show();
        }
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