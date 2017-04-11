package br.com.android.calculadora;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.mode;

public class CalculadoraActivity extends AppCompatActivity {

    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        context = this;

        final EditText numero1 = (EditText) findViewById(R.id.edtNum1) ;
        final EditText numero2 = (EditText) findViewById(R.id.edtNum2) ;
        final EditText resultado = (EditText) findViewById(R.id.edtRst) ;
        final TextView operacao = (TextView) findViewById(R.id.operacao);

        Button soma = (Button) findViewById(R.id.BtnSoma);
        soma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao.setText("+");
            }
        });

        Button subtracao = (Button) findViewById(R.id.BtnSubt);
        subtracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao.setText("-");
            }
        });

        Button multiplicacao = (Button) findViewById(R.id.BtnMult);
        multiplicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao.setText("x");
            }
        });

        Button divisao = (Button) findViewById(R.id.BtnDiv);
        divisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao.setText("/");
            }
        });

        Button salvar = (Button) findViewById(R.id.btnSalvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultado.getText().toString().equals("")){
                    Toast.makeText(context, "Erro! Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }else{
                    ContasSalvasActivity.setConta(resultado.getText().toString());
                    Intent intent = new Intent(context, ContasSalvasActivity.class);
                    startActivity(intent);
                }
            }
        });

        ImageButton imgCalcular = (ImageButton) findViewById(R.id.imageButton2);
        imgCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double resultadoFinal = calcula(numero1.getText().toString(), numero2.getText().toString(), operacao.getText().toString());
                if(resultadoFinal != null){
                    String resultadoTela = numero1.getText().toString() + " " + operacao.getText().toString() +
                            " " + numero2.getText().toString() + " = " + resultadoFinal.toString();
                    resultado.setText(resultadoTela);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Confirmar Saída")
                .setMessage("Deseja realmente sair da aplicação?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CalculadoraActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private static Double calcula(String valor1, String valor2, String operacao){
        Double n1 = null;
        Double n2 = null;
        try{
            n1 = new Double(valor1);
            n2 = new Double(valor2);
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Erro! Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return null;
        }


        switch (operacao){
            case "+":
                return n1 + n2;

            case "-":
                return n1 - n2;

            case "x":
                return n1 * n2;

            case "/":
                return n1 / n2;
        }
        return null;
    }

}
