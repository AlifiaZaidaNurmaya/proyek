package org.proyek.parkirassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.zxing.Result;

import org.proyek.parkirassistant.customScanPaint.CustomViewFinderView;

public class ScanActivity extends AppCompatActivity {

    private ZXingScannerView mScannerView;
    private boolean isCaptured = false;
    SharedPrefManager shared;

    String hasil = "";

    FrameLayout frameLayoutCamera;

    Button ulangButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        shared = new SharedPrefManager(getApplicationContext());
        frameLayoutCamera = (FrameLayout) findViewById(R.id.frame_layout_camera_scan);
        ulangButton = (Button) findViewById(R.id.ulang_button);


        initScannerView();

        ulangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScannerView.resumeCameraPreview(new ZXingScannerView.ResultHandler() {
                    @Override
                    public void handleResult(Result result) {
                        hasil = result.getText();
                        Toast.makeText(getApplicationContext(), "Hasil : " + hasil, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


    }

    private void initScannerView() {
        mScannerView = new ZXingScannerView(getApplicationContext()){
            @Override
            public IViewFinder createViewFinderView(Context context){
                return new CustomViewFinderView(context);
            }
        };
        mScannerView.setAutoFocus(true);
        mScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {
                hasil = result.getText();
                Toast.makeText(getApplicationContext(), "Hasil : " + hasil, Toast.LENGTH_LONG).show();
            }
        });
        frameLayoutCamera.addView(mScannerView);
    }

    @Override
    protected void onStart() {
        mScannerView.startCamera();
        doRequestPermission();
        super.onStart();
    }

    private void doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100:
                initScannerView();
                break;
            default:
                break;
        }

    }


    @Override
    protected void onPause() {
        mScannerView.stopCamera();
        super.onPause();
    }

    public void onClickBack(View view) {
        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);
        finish();
    }


}