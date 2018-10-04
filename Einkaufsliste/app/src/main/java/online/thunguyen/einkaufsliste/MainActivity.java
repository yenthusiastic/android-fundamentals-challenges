package online.thunguyen.einkaufsliste;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Class name for Log tag
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // TextView for the reply body
    private TextView selected_item;
    private int count;


    // Unique tag for the intent reply
    public static final int TEXT_REQUEST = 1;

    private static final float TEXT_SIZE = 30;

    private ArrayList<String> saved_items = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
        LinearLayout all_selected_items = (LinearLayout) findViewById(R.id.all_selected_items);
        // Restore the state.
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("last_count");
            Log.d(LOG_TAG, "last count: " + count);
            //since there are already items added, remove the empty text from top of list
            all_selected_items.removeView(findViewById(getResources().getIdentifier(
                    "item_text_1", "id", getPackageName())));
            for (int i = 0; i < count; i++){
                saved_items.add(savedInstanceState.getString("item_" + i));
                Log.d(LOG_TAG, "saved item: " + savedInstanceState.getString("item_" + i));
                // Add Textview
                TextView save_item = new TextView(this);

                save_item.setLayoutParams(new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                save_item.setTextSize(TEXT_SIZE);
                save_item.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom);
                save_item.setText(savedInstanceState.getString("item_" + i));
                all_selected_items.addView(save_item);
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

        LinearLayout all_selected_items = (LinearLayout) findViewById(R.id.all_selected_items);
        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                count++;
                String item = data.getStringExtra(ItemsList.EXTRA_ITEM);
                Log.d(LOG_TAG, "item: " + item);
                if (count == 1) {
                    //if the first item is added, remove the empty text from top of list
                    all_selected_items.removeView(findViewById(getResources().getIdentifier(
                            "item_text_1", "id", getPackageName())));
                }

                // add the selected item to the list
                TextView selected_item = new TextView(this);
                selected_item.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                selected_item.setTextSize(TEXT_SIZE);
                selected_item.setText(item);
                selected_item.setPadding(20, 20, 20, 20);
                all_selected_items.addView(selected_item);
                saved_items.add(item);

            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (count > 0) {
            outState.putInt("last_count",count);
            Log.d(LOG_TAG, "last count: " + count);
            Log.d(LOG_TAG, "array size: " + saved_items.size());
            for (int i = 0; i < count; i ++){
                outState.putString("item_" + i, saved_items.get(i));
                Log.d(LOG_TAG, "selected item: " + saved_items.get(i));
            }
        }
    }
}
