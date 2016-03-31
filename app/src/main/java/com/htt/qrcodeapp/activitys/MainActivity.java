package com.htt.qrcodeapp.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.htt.qrcodeapp.R;

public class MainActivity extends AppCompatActivity {
    private TextView tvQRCodeText;
    private ImageView ivQRCodePicture;
    public static final int REQUEST_CODE_QRCODE_SCAN=0x0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvQRCodeText=(TextView)this.findViewById(R.id.tv_qrcode_text);
        ivQRCodePicture=(ImageView)this.findViewById(R.id.iv_qrcode_picture);
    }

    public void scanQRCode(View view){
        Intent intent=new Intent(this,QRCodeScanActivity.class);
        this.startActivityForResult(intent,REQUEST_CODE_QRCODE_SCAN);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if(resultCode==RESULT_OK){
            if(data!=null){
                Bundle bundle=data.getExtras();
                if(bundle!=null){
                    tvQRCodeText.setText("二维码扫描识别结果:"+bundle.getString("result"));
                    Bitmap bitmap=bundle.getParcelable("bitmap");
                    if(bitmap!=null){
                        ivQRCodePicture.setImageBitmap(bitmap);
                    }
                }
            }
        }
    }
}
