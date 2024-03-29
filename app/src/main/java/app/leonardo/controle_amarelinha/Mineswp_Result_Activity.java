package app.leonardo.controle_amarelinha;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Mineswp_Result_Activity extends AppCompatActivity {
    private int control_users;
    private int quant_users;
    private TextView textView19;
    private TextView textView17;
    private Bundle bundle;
    private TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mineswp__result_);

        textView3 = (TextView) findViewById(R.id.textView3);
        textView19 = (TextView)findViewById(R.id.textView19);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/ff.ttf");
        textView3.setTypeface(typeface);
        textView19.setTypeface(typeface);

        bundle = getIntent().getExtras();
        if(!bundle.getString("quant_users").isEmpty()) {
            quant_users = Integer.parseInt(bundle.getString("quant_users"));
        }

        control_users = 1;
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter("data_receive"));
    }


    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            JSONObject json = null;
            //String tmp = null;
            //String cmh = null;
            String btn_pedra_state = null;
            String result = null;

            try {
                //tmp = bundle.getString("data_rec");
                json = new JSONObject(bundle.getString("data_rec"));
                //cmh = json.getString("caminho");
                btn_pedra_state = json.getString("pedra_ok").toString();
                result = json.getString("status").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            /*if(cmh != null){
                if(!cmh.isEmpty()){
                    caminho = cmh;
                }
            }
            */

            if (btn_pedra_state != null) {
                if (!btn_pedra_state.isEmpty() && btn_pedra_state.equalsIgnoreCase("ok")) {

//                    if(result.equalsIgnoreCase("w")){
//                        textView3.setText("Você Ganhou");
//                    }else{
//                        textView3.setText("Você Perdeu");
//                    }

                    control_users++;
                    if(control_users > quant_users){
                        control_users = 1;
                    }

                    textView19.setText("Jogador "+ control_users);
                }
            }
        }
    };
}
