package com.example.draft1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import org.tensorflow.lite.Interpreter;



import android.content.res.AssetFileDescriptor;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
public class MainActivity extends AppCompatActivity {
    Interpreter tflite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.getvalues);
        Intent intent = new Intent(this, resultdisplay.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t = findViewById(R.id.getsex);
                String sex = t.getText().toString();
                Log.e("sex", sex);
                t = findViewById(R.id.getage);
                String age = t.getText().toString();
                t = findViewById(R.id.getbp);
                String highbp = t.getText().toString();
                t = findViewById(R.id.getchol);
                String chol = t.getText().toString();
                t = findViewById(R.id.getcholcheck);
                String cholcheck = t.getText().toString();
                t = findViewById(R.id.getbmi);
                String bmi = t.getText().toString();
                t = findViewById(R.id.getcigare);
                String cigare = t.getText().toString();
                t = findViewById(R.id.getstroke);
                String stroke = t.getText().toString();
                t = findViewById(R.id.getheart);
                String heart = t.getText().toString();
                t = findViewById(R.id.getactivity);
                String activity = t.getText().toString();
                t = findViewById(R.id.getfruit);
                String fruit = t.getText().toString();
                t = findViewById(R.id.getvegetables);
                String vegetables = t.getText().toString();
                t = findViewById(R.id.getheacydrinker);
                String heacydrinker = t.getText().toString();
                t = findViewById(R.id.gethealthcare);
                String healthcare = t.getText().toString();
                t = findViewById(R.id.getdoctor);
                String doctor = t.getText().toString();
                t = findViewById(R.id.getgeneral);
                String general = t.getText().toString();
                t = findViewById(R.id.getmental);
                String mental = t.getText().toString();
                t = findViewById(R.id.getphysical);
                String physical = t.getText().toString();
                t = findViewById(R.id.getdifficulty);
                String difficulty = t.getText().toString();
                t = findViewById(R.id.geteducation);
                String education = t.getText().toString();
                t = findViewById(R.id.getincome);
                String income = t.getText().toString();
                TextView print1 = (TextView) findViewById(R.id.display);
                print1.setText(sex);

                try {
                    tflite = new Interpreter(loadModelFile());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                float[][] input= new float[1][21];
                input[0][0] = Float.parseFloat(highbp); ;
                input[0][1] = Float.parseFloat(chol); ;
                input[0][2] = Float.parseFloat(cholcheck); ;
                input[0][3] = Float.parseFloat(bmi);
                input[0][4] = Float.parseFloat(cigare);
                input[0][5] = Float.parseFloat(stroke);
                input[0][6] = Float.parseFloat(heart);
                input[0][7] = Float.parseFloat(activity);
                input[0][8] = Float.parseFloat(fruit);
                input[0][9] = Float.parseFloat(vegetables);
                input[0][10] = Float.parseFloat(heacydrinker);
                input[0][11] = Float.parseFloat(healthcare);
                input[0][12] = Float.parseFloat(doctor);
                input[0][13] = Float.parseFloat(general);
                input[0][14] = Float.parseFloat(mental);
                input[0][15] = Float.parseFloat(physical);
                input[0][16] = Float.parseFloat(difficulty);
                input[0][17] = Float.parseFloat(sex);
                input[0][18] = Float.parseFloat(age);
                input[0][19] = Float.parseFloat(education);
                input[0][20] = Float.parseFloat(income);
                float result = doInference(input);
                String r = "You have " + String.valueOf(result) + "% chance to have diabetes.";
                intent.putExtra("result", r);
                startActivity(intent);
            }
        });
    }
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor=this.getAssets().openFd("model.tflite");
        FileInputStream inputStream=new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel=inputStream.getChannel();
        long startOffset=fileDescriptor.getStartOffset();
        long declareLength=fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffset,declareLength);
    }
    private float doInference(float input[][]) {
        float[][] output=new float[1][1];
        tflite.run(input,output);
        return output[0][0];
    }
}