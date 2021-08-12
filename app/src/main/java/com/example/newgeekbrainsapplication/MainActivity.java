package com.example.newgeekbrainsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

     /*   String[] data = new  String[]{
                "Жим лежа",
                "Становая тяга",
                "Приседание",
                "Отжимание",
                "Подтягивание",
                "Выпады",
                "Тяга в наклоне",
                "Прыжки",
                "Наклоны",
                "Отжимание на брусьях"

        };*/

        CardSource cardSource = new CardSourceImpl(this);

        itemAdapter adapter = new itemAdapter(cardSource);

        recyclerView.setHasFixedSize(true); // так как все элементы списка одинаковы то recyclerView будет с этим работать быстрее
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // либо уакзать в html activity_main app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider, null)); // отступы между элементами
        recyclerView.addItemDecoration(itemDecoration);

        /*
        adapter.setListener(position ->
                Toast.makeText(this,"Click to"+data[position],Toast.LENGTH_SHORT)
                        .show());
                        */

    }
}