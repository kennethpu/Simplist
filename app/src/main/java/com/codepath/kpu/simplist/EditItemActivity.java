package com.codepath.kpu.simplist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        position = getIntent().getIntExtra("position", 0);
        String taskName = getIntent().getStringExtra("task_name");
        EditText etNewItem = (EditText)findViewById(R.id.etEditItem);
        etNewItem.setText(taskName);
    }

    public void onSave(View v) {
        EditText etName = (EditText) findViewById(R.id.etEditItem);

        Intent data = new Intent();
        data.putExtra("position", position);
        data.putExtra("task_name", etName.getText().toString());
        setResult(RESULT_OK, data);

        this.finish();
    }
}
