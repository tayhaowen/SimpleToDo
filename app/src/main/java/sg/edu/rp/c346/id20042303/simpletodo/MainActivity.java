package sg.edu.rp.c346.id20042303.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvTask;
    Spinner sinnerAddDelete;
    ArrayList<String> taskList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        sinnerAddDelete = findViewById(R.id.spinner);
        lvTask = findViewById(R.id.ListView);

        ArrayAdapter aaTasks = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, taskList);
        lvTask.setAdapter(aaTasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etTask.getText().toString().isEmpty()){
                    String task = etTask.getText().toString();
                    taskList.add(task);
                    aaTasks.notifyDataSetChanged();
                    etTask.setText("");
                    Toast.makeText(MainActivity.this, "New Task Added!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Task is Empty!", Toast.LENGTH_SHORT).show();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                aaTasks.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "All Task Removed!", Toast.LENGTH_SHORT).show();
            }
        });
        sinnerAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //add
                        etTask.setHint("Type in a new task here");
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        etTask.setText("");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        //delete
                        etTask.setHint("Type in the index of the task to be removed");
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etTask.setText("");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etTask.getText().toString().isEmpty()){
                    int pos = Integer.parseInt(etTask.getText().toString());
                    if (aaTasks.getCount() > pos) {
                        taskList.remove(pos);
                    } else if (aaTasks.getCount() == 0) {
                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(MainActivity.this, "Index " + pos + " have been deleted", Toast.LENGTH_SHORT).show();
                    aaTasks.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "Index is empty", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}