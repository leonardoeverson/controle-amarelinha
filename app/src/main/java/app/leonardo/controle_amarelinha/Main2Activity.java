package app.leonardo.controle_amarelinha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.*;
import static android.widget.Toast.LENGTH_SHORT;

public class Main2Activity extends AppCompatActivity {

    public Button button2;
    public EditText editText;
    public Bundle bundle;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Fixa em modo Retrato
        editText = (EditText)findViewById(R.id.editText2);
        button2 = (Button)findViewById(R.id.button2);
        bundle = getIntent().getExtras();
        spinner = (Spinner)findViewById(R.id.spinner);

        TextView textView = (TextView)findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/ff.ttf");
        textView.setTypeface(typeface);

       //editText.setText("0");

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().isEmpty() && Integer.parseInt(editText.getText().toString()) > 0) {
                    bundle.putString("quant_users", editText.getText().toString());
                    bundle.putString("game_mode",spinner.getSelectedItem().toString());
                    Intent escolhe_interacao = new Intent(Main2Activity.this, InteractionActivity.class);
                    escolhe_interacao.putExtras(bundle);
                    startActivity(escolhe_interacao);
                }else{
                    Toast.makeText(Main2Activity.this,"Defina a quantidade de jogadores", LENGTH_SHORT).show();
                }
            }
        });
    }
}
