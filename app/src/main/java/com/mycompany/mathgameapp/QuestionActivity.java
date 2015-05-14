package com.mycompany.mathgameapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class QuestionActivity extends ActionBarActivity {

    private int counter;
    private int min = 2;
    private int max = 11;
    private int level;
    private boolean correct;
    private Question q;
    private TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        correct = false;
        counter = 0;

        // allow to accept extra information
        Bundle extraData = getIntent().getExtras();
        String playerName = extraData.getString("playerName");
        TextView name = (TextView)findViewById(R.id.player);
        name.setText("Player : "+playerName);

        level = extraData.getInt("levelSelected");
        // create an instance of question
        System.out.println("level: "+level);
        q = new Question(min, max);
        q.generateQuestion(level);
        System.out.println("question: "+q.getQuestion());
        question = (TextView)findViewById(R.id.questionField);
        question.setText(q.getQuestion());

    }

    public void onClickAnswerField(View view){
        ((EditText)findViewById(R.id.answerField)).setText("");
    }

    public void onClickRestart(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void onClickCheck(View view){
        int answer;
        try{
            String ans = ((EditText)findViewById(R.id.answerField)).getText().toString();
            answer = Integer.valueOf(ans);

            if(answer == q.getAnswer()){
                Toast.makeText(getApplicationContext(), "Correct! You may click next.", Toast.LENGTH_SHORT).show();
                correct = true;
                counter++;
            }else if(answer != q.getAnswer()){
                Toast.makeText(getApplicationContext(), "Wrong! Please try again.", Toast.LENGTH_SHORT).show();
                counter = 0;
            }
        }catch(NullPointerException npe){
            Toast.makeText(getApplicationContext(), "Please input an answer!", Toast.LENGTH_SHORT).show();
        }catch(NumberFormatException nfe){
            Toast.makeText(getApplicationContext(),"Please input only numbers!", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"I don't know what you're doing!", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickNext(View view){
        if(correct){
            if(counter == 10 && level < 3) level++;

            q.generateQuestion(level);

            question = (TextView)findViewById(R.id.questionField);
            question.setText(q.getQuestion());

            correct = false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
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
