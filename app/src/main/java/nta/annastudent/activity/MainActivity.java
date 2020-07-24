package nta.annastudent.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import nta.annastudent.R;

public class MainActivity extends AppCompatActivity {
    ImageView imageNguoidung, imageTheloai, imageHoadon, imageSach, imageBanchay, imageThongke;
    LinearLayout Lin1, Lin2, Lin3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("QUẢN LÍ SÁCH");
// animation activity
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

        imageNguoidung = findViewById(R.id.imgNguoiDung);
        imageTheloai = findViewById(R.id.imgTheLoai);
        imageSach = findViewById(R.id.imgSach);
        imageHoadon = findViewById(R.id.imgHoaDon);
        imageBanchay = findViewById(R.id.imgBanChay);
        imageThongke = findViewById(R.id.imgThongKe);

        Lin1 = findViewById(R.id.Lin1);
        Lin2 = findViewById(R.id.Lin2);
        Lin3 = findViewById(R.id.Lin3);

        imageNguoidung.setBackgroundResource(R.drawable.run_user);
        imageTheloai.setBackgroundResource(R.drawable.run_type);
        imageSach.setBackgroundResource(R.drawable.run_book);
        imageHoadon.setBackgroundResource(R.drawable.run_hoadon);
        imageBanchay.setBackgroundResource(R.drawable.run_money);
        imageThongke.setBackgroundResource(R.drawable.run_thongke);

// Đổi font chữ:
        TextView text = findViewById(R.id.text);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/svn-hello_sweets.otf");
        text.setTypeface(type);

// Animation icon
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
        imageNguoidung.startAnimation(animation);

// Animation Layout:

        Animation lin1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out_to_top);
        Lin1.startAnimation(lin1);

        Animation lin2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out_to_top2);
        Lin2.startAnimation(lin2);

        Animation lin3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out_to_top3);
        Lin3.startAnimation(lin3);


        imageNguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListNguoiDungActivity.class);
                startActivity(i);
            }
        });

        imageTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListTheLoaiActivity.class);
                startActivity(i);
            }
        });

        imageSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListSachActivity.class);
                startActivity(i);
            }
        });

        imageHoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListHoaDonActivity.class);
                startActivity(i);
            }
        });

        imageBanchay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListBanChayActivity.class);
                startActivity(i);
            }
        });

        imageThongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListThongKeActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        AnimationDrawable frameAnim = (AnimationDrawable) imageNguoidung.getBackground();
        AnimationDrawable frameAnim2 = (AnimationDrawable) imageTheloai.getBackground();
        AnimationDrawable frameAnim3 = (AnimationDrawable) imageSach.getBackground();
        AnimationDrawable frameAnim4 = (AnimationDrawable) imageHoadon.getBackground();
        AnimationDrawable frameAnim5 = (AnimationDrawable) imageBanchay.getBackground();
        AnimationDrawable frameAnim6 = (AnimationDrawable) imageThongke.getBackground();
        if (hasFocus) {
            frameAnim.start();
            frameAnim2.start();
            frameAnim3.start();
            frameAnim4.start();
            frameAnim5.start();
            frameAnim6.start();
        } else {
            frameAnim.stop();
            frameAnim2.stop();
            frameAnim3.stop();
            frameAnim4.stop();
            frameAnim5.stop();
            frameAnim6.stop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                finish();
                return (true);
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}