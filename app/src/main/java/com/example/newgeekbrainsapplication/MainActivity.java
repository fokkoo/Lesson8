package com.example.newgeekbrainsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    // 2 25 19
    private itemAdapter adapter;
    private CardSource cardSource;
    private RecyclerView recyclerView;
    private int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new itemAdapter(cardSource);
        recyclerView = findViewById(R.id.recyclerView);


        cardSource.init(cardSource -> adapter.notifyDataSetChanged());

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

        cardSource = new CardSourceFirebaseImpl();


        recyclerView.setHasFixedSize(true); // так как все элементы списка одинаковы то recyclerView будет с этим работать быстрее
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // либо уакзать в html activity_main app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider, null)); // отступы между элементами
        recyclerView.addItemDecoration(itemDecoration);


        adapter.setListener(new itemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                currentPosition = position;
                view.showContextMenu(20, 20); //показываем контекст меню с отступом
            }


        });


        registerForContextMenu(recyclerView); // регистрация контекстного меню
        /*
        adapter.setListener(position ->
                Toast.makeText(this,"Click to"+data[position],Toast.LENGTH_SHORT)
                        .show());
                        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cards_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:

                CardData cardData = new CardData("new title", "new description", R.drawable.nature1, false);
                cardData.setId(UUID.randomUUID().toString()); // генерирование уникального ключа для карточки


                cardSource.addCardData(cardData);
                adapter.notifyItemChanged(cardSource.size() - 1); // уведомление адаптера о обновлении списка в конкретном месте
                recyclerView.scrollToPosition(cardSource.size() - 1);
                return true;
            case R.id.action_clear:
                cardSource.clearCardData();
                adapter.notifyDataSetChanged(); // уведомление адаптера о обновлении списка
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.card_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delite:
                cardSource.deliteCardData(currentPosition);
                adapter.notifyItemRemoved(currentPosition); // уведомление адаптера о обновлении списка в конкретном месте

                return true;
            case R.id.action_update:

                CardData cardData = new CardData("new Title", "Description", R.drawable.nature1, false);
                cardData.setId(cardSource.getCardData(currentPosition).getId());

                cardSource.updateCardData(currentPosition, cardData);
                adapter.notifyItemChanged(currentPosition); // уведомление адаптера о обновлении списка
                return true;
        }


        return super.onContextItemSelected(item);
    }
}