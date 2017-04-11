package br.com.android.calculadora;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by evandrosilvapinto on 11/04/17.
 */

public class ContasSalvasActivity extends AppCompatActivity {


    private static ArrayList<String> contasSalvas = null;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas_salvas);
        context = this;

        ListView listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, contasSalvas);
        listView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.fechar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CalculadoraActivity.class);
                startActivity(intent);
            }
        });
    }

    public static ArrayList<String> getContasSalvas() {
        return contasSalvas;
    }

    public static void setConta(String conta) {
        if(contasSalvas == null){
            contasSalvas = new ArrayList<>();
            contasSalvas.add(0, conta);
        }else{
            contasSalvas.add(0, conta);
        }
    }
}
