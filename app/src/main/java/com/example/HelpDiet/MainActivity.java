package com.example.HelpDiet;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private Button imageButton;
    private ImageView profileImage;


    /*private String user_name;
    private int height;
    private int weight;
    private int tg_weight;*/

    class BtnOnClickListner implements Button.OnClickListener {
        @Override
        public void onClick(View view){
            Intent intent;
            switch (view.getId()) {
                case R.id.chartButton:
                    intent = new Intent(getApplicationContext(), ChartActivity.class);
                    startActivity(intent);
                    break;
                case R.id.foodButton:
                    intent = new Intent(getApplicationContext(), myfood.class);
                    startActivity(intent);
                    break;
                case R.id.machineButton:
                    intent = new Intent(getApplicationContext(), MachineActivity.class);
                    startActivity(intent);
                    break;
                case R.id.changeButton:
                Toast.makeText(getApplicationContext(), "아직 정보 수정 기능은 이용하실 수 없습니다.", Toast.LENGTH_SHORT);

                case R.id.calendarButton:
                    intent = new Intent(getApplicationContext(), Todo_Listmain.class);
                    startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BtnOnClickListner onClickListner = new BtnOnClickListner();

        Button chartBtn = (Button) findViewById(R.id.chartButton);
        Button machineBtn = (Button) findViewById(R.id.machineButton);
        Button calendarBtn = (Button) findViewById(R.id.calendarButton);
        Button foodBtn = (Button) findViewById(R.id.foodButton);
        Button changeBtn = (Button) findViewById(R.id.changeButton);

        imageButton = findViewById(R.id.imageButton);
        profileImage = findViewById(R.id.profileImage);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });


        foodBtn.setOnClickListener(onClickListner);
        machineBtn.setOnClickListener(onClickListner);
        calendarBtn.setOnClickListener(onClickListner);
        chartBtn.setOnClickListener(onClickListner);
        changeBtn.setOnClickListener(onClickListner);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    profileImage.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료하시겠습니까?");

        // "예" 버튼을 누르면 실행되는 리스너
        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // 현재 액티비티를 종료한다. (MainActivity에서 작동하기 때문에 애플리케이션을 종료한다.)
            }
        });
        // "아니오" 버튼을 누르면 실행되는 리스너
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return; // 아무런 작업도 하지 않고 돌아간다
            }
        });
        alBuilder.setTitle("프로그램 종료");
        alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
    }
}