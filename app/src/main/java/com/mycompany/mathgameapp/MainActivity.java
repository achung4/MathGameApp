package com.mycompany.mathgameapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    // Instant Fields
    private String levelSelected;
    private String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nameTextField(View view){
        ((EditText)findViewById(R.id.playerName)).setText("");
    }

    public void onClickRadio(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.level1:
                if (checked)
                    levelSelected = "1";
                    break;
            case R.id.level2:
                if (checked)
                    levelSelected = "2";
                    break;
            case R.id.level3:
                if (checked)
                    levelSelected = "3";
                    break;
        }

    }

    public void onClickStart(View view){
        EditText nameInput = (EditText)findViewById(R.id.playerName);
        name = nameInput.getText().toString();

        if((name.equals("") || name.equals("Enter Player Name")) && levelSelected == null){
            Toast.makeText(getApplicationContext(),"Please input name \n and select a level!", Toast.LENGTH_SHORT).show();
        }else if(name.equals("")|| name.equals("Enter Player Name")){
            Toast.makeText(getApplicationContext(),"Please input name!", Toast.LENGTH_SHORT).show();
        }else if(levelSelected == null){
            Toast.makeText(getApplicationContext(),"Please select a level!", Toast.LENGTH_SHORT).show();
        }else {
            Intent i = new Intent(this, QuestionActivity.class);
            i.putExtra("playerName", name);
            i.putExtra("levelSelected", Integer.valueOf(levelSelected));
            startActivity(i);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
