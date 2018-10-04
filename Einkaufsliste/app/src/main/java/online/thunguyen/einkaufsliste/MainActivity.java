package online.thunguyen.einkaufsliste;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Class name for Log tag
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Unique tag for the intent reply.
    public static final String EXTRA_ITEM =
            "online.thunguyen.einkaufsliste.extra.ITEM";
    public static final int MAX_ITEMS_NUM = 10;


    // TextView for the reply body
    private TextView selected_item;
    private int count;


    // Unique tag for the intent reply
    public static final int TEXT_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        // Restore the state.
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("last_count");
            for (int i = 0; i < count; i++){
                TextView save_item = findViewById(getResources().getIdentifier(
                        "item_text_" + (i + 1), "id",  getPackageName()));
                save_item.setText(savedInstanceState.getString("item_" + i));
            }
        } else count = 0;


    }

    public void showAllItems(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, ItemsList.class);
        startActivityForResult(intent, TEXT_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                count++;
                if (count > MAX_ITEMS_NUM){
                    for (int i = 0; i < count; i++){
                        TextView item = findViewById(getResources().getIdentifier(
                                "item_text_" + (i + 1), "id",  getPackageName()));
                        item.setText("");

                    }
                    Snackbar warning = Snackbar.make(findViewById(R.id.fab_add), R.string.warning, Snackbar.LENGTH_LONG);
                    warning.show();
                    count = 1;
                }
                String item = data.getStringExtra(ItemsList.EXTRA_ITEM);
                int item_id = getResources().getIdentifier("item_text_" + count, "id",  getPackageName());

                selected_item = findViewById(item_id);
                // Set the reply and make it visible.
                selected_item.setText(item);
                selected_item.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (count > 0) {
            outState.putInt("last_count",count);
            Log.d(LOG_TAG, "last count: " + count);
            for (int i = 0; i < count; i++){
                TextView save_item = findViewById(getResources().getIdentifier("item_text_" + (i+1), "id",  getPackageName()));
                outState.putString("item_" + i, save_item.getText().toString());
                Log.d(LOG_TAG, "saved item: " + save_item.getText().toString());
            }
        }
    }
}
