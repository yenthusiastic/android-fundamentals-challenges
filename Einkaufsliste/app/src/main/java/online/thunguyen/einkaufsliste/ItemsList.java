package online.thunguyen.einkaufsliste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItemsList extends AppCompatActivity {

    private static final String LOG_TAG = ItemsList.class.getSimpleName();
    // Unique tag for the intent reply.
    public static final String EXTRA_ITEM =
            "online.thunguyen.einkaufsliste.extra.ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
    }

    public void addItemToList(View view) {
        Button selected_btn = (Button) view;
        String selected_item = selected_btn.getText().toString();
        Log.d(LOG_TAG, selected_item);
        Intent return_intent = new Intent();
        return_intent.putExtra(EXTRA_ITEM, selected_item);
        setResult(RESULT_OK, return_intent);
        Log.d(LOG_TAG, "End ItemsList Activity");
        finish();
    }

    @Override
    public void onPause() {
        Log.d(LOG_TAG, "onPause");
        super.onPause();


    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }


    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
