package com.example.puzzledroid;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class custom_dialog_menu {

    public interface returnDialogMenu {
        void Result(String username, int puzzres, int imgId);

    }

    private final returnDialogMenu intrfc;
    public Intent intent = new Intent();




    public custom_dialog_menu(Context context, returnDialogMenu actividad) {

        intrfc = actividad;
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setContentView(R.layout.custom_dialog_menu);

        final TextView userName = (TextView) dialog.findViewById(R.id.txt_UserEnter);
        ((TextView) dialog.findViewById(R.id.txt_UserEnter)).setTextColor(Color.BLACK); //Text color for white background.



        // Botones selección de imágenes
        Button imgDefault = (Button) dialog.findViewById(R.id.btnDefaultSelect);
        Button imgCamera = (Button) dialog.findViewById(R.id.btnCameraSelect);
        Button imgLibrary = (Button) dialog.findViewById(R.id.btnLibrarySelect);

        imgDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int puzzres = radioButtonCheck(dialog);
                if (userNameCheck(userName, context)) {
                    intrfc.Result(userName.getText().toString(), puzzres, imageRandomReturn());
                    dialog.dismiss();
                }
            }
        });
        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int puzzres = radioButtonCheck(dialog);
                if (userNameCheck(userName, context)) {
                    intrfc.Result(userName.getText().toString(), puzzres, R.drawable.level2);
                    dialog.dismiss();
                }

            }
        });
        imgLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int puzzres = radioButtonCheck(dialog);
                if (userNameCheck(userName, context)) {
                    intrfc.Result(userName.getText().toString(), puzzres, R.drawable.level3);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    // Función de comprobación del nombre de ususario.
    public boolean userNameCheck(TextView userName, Context context) {
        if (userName.getText().toString().equals("")) {
            Toast.makeText(context, "Please, enter a valid user name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    // Función que devuelve la resolución del puzzle según los radio buttons.
    public int radioButtonCheck (Dialog dialog){
        int puzzres = 18;
        // Botones seleccción de Dificultad
        RadioButton radioButtonMedium = (RadioButton) dialog.findViewById(R.id.rbtnMediu);
        RadioButton radioButtonHard = (RadioButton) dialog.findViewById(R.id.rbtnHard);

        if (radioButtonMedium.isChecked()) {
            puzzres = 32;
        }

        if (radioButtonHard.isChecked()) {
            puzzres = 128;
        }

        return puzzres;
    }

    // Función  que devuelve una imagen aleatoria de las propuestas inicialmente para el juego.
    public int imageRandomReturn(){
        List<Integer> image = Arrays.asList(R.drawable.level1, R.drawable.level2, R.drawable.level3);
        Random random = new Random();

        return image.get(random.nextInt(image.size()));
    }





}


