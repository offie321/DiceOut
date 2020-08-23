package example.com;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //field to hold the roll result text.
    TextView rollResult;


    //field to hold the score
    int score;

    //field to hold random number generator
    Random rand;

    //fieldss to hold the dice value
    int die1;
    int die2;
    int die3;

    int die4;

    int amount;

    // Field to hold the score text
    TextView scoreText;

    // ArrayList to hold all three dice values
    ArrayList<Integer> dice;

    // ArrayList to hold all three dice images
    ArrayList<ImageView> diceImageViews;

//    //TODO Dice Predictions
//    ArrayList<EditText> dicePredictions;
    EditText predict1;
    EditText predict2;
    EditText predict3;
    EditText predict4;

    String stringPrediction1;
    int valuePrediction1;

    String stringPrediction2;
    int valuePrediction2;

    String stringPrediction3;
    int valuePrediction3;

    String stringPrediction4;
    int valuePrediction4;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        //set initial score
        score = 0;

        amount = 1;

        rollResult = findViewById(R.id.rollResult);
        scoreText = (TextView) findViewById(R.id.scoreText);

        //create greeting
        Toast.makeText(getApplicationContext(), "Welcome to DiceOut!", Toast.LENGTH_SHORT).show();

        // Initialize the random number generator
        rand = new Random();

        // Create ArrayList container for the dice values
        dice = new ArrayList<>();

        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        ImageView die4Image = (ImageView) findViewById(R.id.die4Image);

        diceImageViews = new ArrayList<>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);
        diceImageViews.add(die4Image);

//        //TODO Dice predictions
//        EditText predict1 = (EditText) findViewById(R.id.predict1);
//        EditText predict2 = (EditText) findViewById(R.id.predict2);
//        EditText predict3 = (EditText) findViewById(R.id.predict3);
//
//        dicePredictions = new ArrayList<>();
//        dicePredictions.add(predict1);
//        dicePredictions.add(predict2);
//        dicePredictions.add(predict3);

        //get values from predictions
        predict1 = findViewById(R.id.predict1);
        predict2 = findViewById(R.id.predict2);
        predict3 = findViewById(R.id.predict3);

        predict4 = findViewById(R.id.predict4);

//        predict1=findViewById(R.id.predict1);
//        stringPrediction1=predict1.getText().toString();
//        valuePrediction1=Integer.parseInt(stringPrediction1);

//        predict1=findViewById(R.id.predict1);

//        stringPrediction1=predict1.getText().toString();


//        valuePrediction1=Integer.parseInt(stringPrediction1);


    }

    public void rollDice(View v){
        rollResult.setText(R.string.RollResult);

        //Get values from inputted predictions

        //Prediction1
        stringPrediction1= predict1.getText().toString();
        valuePrediction1=Integer.parseInt(stringPrediction1);

        //Prediction2
        stringPrediction2= predict2.getText().toString();
        valuePrediction2=Integer.parseInt(stringPrediction2);

        //Prediction3
        stringPrediction3= predict3.getText().toString();
        valuePrediction3=Integer.parseInt(stringPrediction3);

        //Prediction4
        stringPrediction4= predict4.getText().toString();
        valuePrediction4=Integer.parseInt(stringPrediction4);

        // Roll dice
//        die1 = rand.nextInt(6)+1;
//        die2 = rand.nextInt(6)+1;
//        die3 = rand.nextInt(6)+1;

//         Set dice values into an ArrayList
//        dice.clear();
//        dice.add(die1);
//        dice.add(die2);
//        dice.add(die3);


        if (amount == 1){
            die1 = rand.nextInt(6)+1;
            dice.clear();
            dice.add(die1);
            dice.add(null);
            dice.add(null);
            dice.add(null);
            amount = 2;
        }else if(amount == 2){
            die2 = rand.nextInt(6)+1;
            dice.clear();
            dice.add(null);
            dice.add(die2);
            dice.add(null);
            dice.add(null);
            amount = 3;
        }else if(amount == 3){
            die3 = rand.nextInt(6)+1;
            dice.clear();
            dice.add(null);
            dice.add(null);
            dice.add(die3);
            dice.add(null);
            amount = 4;
        }else if(amount == 4){
            die4 = rand.nextInt(6)+1;
            dice.clear();
            dice.add(null);
            dice.add(null);
            dice.add(null);
            dice.add(die4);
            amount = 1;
        }

        for (int dieOfSet = 0; dieOfSet < 4; dieOfSet++) {
            String imageName = "die_" + dice.get(dieOfSet) + ".png";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        // Build message with the result
        String msg;
        if(die1 == die2 && die1 == die3 && die1 == die4){
            int scoreDelta = die1 * 500;
            msg = "You rolled four of the same dice " + die1 + "! You score " + scoreDelta + " points!";
            score += scoreDelta;
        }else if (die1 == die2 && die1 == die3 || die1 == die2 && die1 == die4 || die1 == die3 && die1 == die4 || die2 == die3 && die2 == die4){
            // Triples
            int scoreDelta = die1 * 100;
            msg = "You rolled a triple " + die1 + "! You score " + scoreDelta + " points!";
            score += scoreDelta;
        } else if (die1 == die2 || die1 == die3 || die2 == die3){
            // Doubles
            msg = "You rolled doubles for 50 points!";
            score += 50;
            //Als je één van de cijfers hebt geraden.
        } else if(die1 == valuePrediction1 || die2 == valuePrediction2 || die3 == valuePrediction3){
            msg = "You guessed one of the dice for 25 points.";
            score+=25;
        }

        else{
            msg = "You didn't score this roll. Try again!";
        }

        // Update the app to display the result message
        rollResult.setText(msg);
        scoreText.setText("Score: " + score);

        //TODO Dice Predictions
//        if (valuePrediction1 == 1){
//            msg = "het cijfer is 1";
//        }
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