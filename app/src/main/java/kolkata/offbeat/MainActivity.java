package kolkata.offbeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button register, update, delete, login;
    EditText name, phone;
    SharedPreferences sp;

    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("Sayan", MODE_PRIVATE); //This line is having the database name Sayan.xml


        //Defining the Buttons
        register = findViewById(R.id.register);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        login = findViewById(R.id.login);


        //Defining the Edit Text
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(phone.getText().toString())) {


                    if (!sp.contains("n") && !sp.contains("p")) {
                        ed = sp.edit();
                        ed.putString("n", name.getText().toString());
                        ed.putString("p", phone.getText().toString());
                        ed.commit();
                        Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Already Registered", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    name.setError("Name Should not be Empty");
                    phone.setError("Phone Number Should not be Empty");
                    Toast.makeText(MainActivity.this, "Enter Something", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sp.contains("n") && sp.contains("p")){
                    ed = sp.edit();
                    ed.putString("n",name.getText().toString());
                    ed.commit();
                    Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Nothing to be Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.contains("n") && sp.contains("p")) {
                    ed = sp.edit();
                    ed.clear();
                    ed.commit();
                    Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Nothing to Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sp.contains("n") && sp.contains("p")){
                    String a = sp.getString("n",null);
                    String b = sp.getString("p",null);
                    Toast.makeText(MainActivity.this, ""+a+"\n"+b, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User is not Registered", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
