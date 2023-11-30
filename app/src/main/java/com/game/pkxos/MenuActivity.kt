package com.game.pkxos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startGame(View view) {
        Intent startGame = new Intent(this, GameActivity.class);
        startActivity(startGame);
    }

    public void openAbout(View view) {
//        PrettyDialog dialog = new PrettyDialog(this);
//        dialog
//                .setAnimationEnabled(true)
//                .addButton("OK", Color.BLACK, Color.BLACK, new PrettyDialogCallback() {
//                    @Override
//                    public void onClick() {
//                        dialog.dismiss();
//
//                    }
//                })
//                .setTitle(getString(R.string.rules_title))
//                .setMessage(getString(R.string.rules)).show();

    }
}
