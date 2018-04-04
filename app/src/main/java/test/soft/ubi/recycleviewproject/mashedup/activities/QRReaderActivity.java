package test.soft.ubi.recycleviewproject.mashedup.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.soft.ubi.recycleviewproject.R;

public class QRReaderActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @BindView(R.id.scanbtn)
    Button scanBtn;
    @BindView(R.id.result)
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrreader);
        ButterKnife.bind(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
    }

    @OnClick(R.id.scanbtn)
    public void onScanClick() {
        Intent intent = new Intent(QRReaderActivity.this, ScanActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent() != null) {
            result.setText(getIntent().getParcelableExtra("barcode"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                final Barcode barcode = data.getParcelableExtra("barcode");
                result.post(() -> result.setText(barcode.displayValue));
            }
        }
    }

}
