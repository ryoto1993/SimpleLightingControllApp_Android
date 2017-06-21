package jp.ac.doshisha.mikilab.tom.kc104;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    EditText[] op;
    EditText port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        op = new EditText[4];

        op[0] = (EditText) findViewById(R.id.text_ip_oq1);
        op[1] = (EditText) findViewById(R.id.text_ip_oq2);
        op[2] = (EditText) findViewById(R.id.text_ip_oq3);
        op[3] = (EditText) findViewById(R.id.text_ip_oq4);

        port = (EditText) findViewById(R.id.editText_port);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("調光サーバ設定");

        // セッティング読み込み
        readSetting();

        // ボタンListener登録
        Button btn = (Button) findViewById(R.id.button_config_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeSetting();
            }
        });

    }

    private void readSetting() {
        String[] ip = AsyncDimmer.getIpAddress().split("\\.");

        for(int i=0; i<4; i++) {
            op[i].setText(ip[i], TextView.BufferType.NORMAL);
        }

        port.setText(AsyncDimmer.getPORT(), TextView.BufferType.NORMAL);
    }

    private void writeSetting() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String ip =  String.valueOf(op[0].getText()) + "." +
                String.valueOf(op[1].getText()) + "." +
                String.valueOf(op[2].getText()) + "." +
                String.valueOf(op[3].getText());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("IP", ip);
        editor.commit();
        editor.putString("port", String.valueOf(port.getText()));
    }

}
