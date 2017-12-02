package app.leonardo.controle_amarelinha;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import java.nio.charset.Charset;

public class Minesweeper extends AppCompatActivity {

    Bundle bundle;
    private JsonControl jsonControl;
    private Button button;
    private int quant_users;
    private byte[] data_send;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Fixa em modo Retrato

        jsonControl = new JsonControl();
        button = (Button)findViewById(R.id.button4);
        quant_users = Integer.parseInt("2");

        spinner = (Spinner)findViewById(R.id.spinner2);

        TextView textView = (TextView)findViewById(R.id.textView18);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/inky.ttf");
        textView.setTypeface(typeface);


        //Log.d("k6","DEVICE UUID: " + bundle.getString("device"));
        //Log.d("k6", "Device Address: " + bundle.getString("address"));
        //Log.d("Quantidade_de_Usuários", String.valueOf(quant_users));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modo = String.valueOf(spinner.getSelectedItemId());



                try {
                    jsonControl.add_data("jogo","2");
                    jsonControl.add_data("quant_users", "2");
                    jsonControl.add_data("modo",modo);
                    String tmp = jsonControl.json_prepare();
                    data_send = tmp.getBytes(Charset.defaultCharset());

                    Intent intent = new Intent("data_send");
                    intent.putExtra("data_to_send",data_send);
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

                    Log.d("Data send", tmp);
                    Log.d("MinesweeperActivity","Dados enviados");

                    //mBluetoothConnection.write(data_send);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
