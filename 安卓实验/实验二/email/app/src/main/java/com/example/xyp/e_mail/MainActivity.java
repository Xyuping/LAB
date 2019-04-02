package com.example.xyp.e_mail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.write:
//                replaceFragment(new WriteMailFragment());
//                Button button = (Button) findViewById(R.id.camera);
////                button.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        replaceFragment(new AnotherRightFragment());
////                    }
////                });
//                Button button2 = (Button) findViewById(R.id.add);
                Intent intent = new Intent(MainActivity.this,WriteActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
    private void replaceFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.write,fragment);
        transaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
