package com.example.workdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView btn = findViewById(R.id.already);
        TextView username = findViewById(R.id.username);
        TextView email = findViewById(R.id.Email);
        TextView password = findViewById(R.id.Password);
        Button registerbtn = findViewById(R.id.btnofResigter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LogInActivity.class));
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionclass();
                try {
                    if(connection != null)
                    {
                        String sqlinsert="Insert into UserInfo_Tab values('" + username.getText().toString() + "','" + email.getText().toString() + "','" + password.getText().toString() + "')";
                        Statement st=connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }

                }
                catch (Exception exception)
                {
                    Log.e("Error", exception.getMessage());
                }
            }
        });

    }

    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
        Connection con = null;
        String ip = "192.168.0.104", port="53935", username="Demo", password="koria",databasename="Data";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionurl = "jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
            con= DriverManager.getConnection(connectionurl);
        }
        catch (Exception exception){
            Log.e("error", exception.getMessage());
        }
        return con;
    }
}