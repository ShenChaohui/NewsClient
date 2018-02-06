package com.geniuses.newsclient.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.geniuses.newsclient.R;
import com.zaaach.citypicker.CityPickerActivity;

/**
 * Created by Sch on 2018/2/5.
 */

public class WeatherActivity extends BasicActivity {
    private static final int REQUEST_CODE_PICK_CITY = 0;
    @Override
    public int getActivity() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initView() {
        initTitle();
        getRightButton(R.mipmap.ic_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(WeatherActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });
    }

    @Override
    protected void main() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                Toast.makeText(this,city,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
