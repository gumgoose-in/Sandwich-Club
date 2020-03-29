package com.glenkernick.apps.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.glenkernick.apps.sandwichclub.model.Sandwich;
import com.glenkernick.apps.sandwichclub.utils.JsonUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.imageView);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.get()
                .load(sandwich.getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView alsoKnown = findViewById(R.id.alsoKnown);
        TextView placeOfOrigin = findViewById(R.id.placeOfOrigin);
        TextView alsoKnownAsTextView = findViewById(R.id.also_knownTextView);
        TextView ingredientsTextView = findViewById(R.id.ingredientsTextView);
        TextView placeOfOriginTextView = findViewById(R.id.placeOfOriginTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

        StringBuilder alsoKnownSB = new StringBuilder();
        List<String> alsoKnownList = sandwich.getAlsoKnownAs();
        if (alsoKnownList.size() > 0) {
            for (int i = 0; i < alsoKnownList.size(); i++) {
                alsoKnownSB.append(alsoKnownList.get(i));
                if (i < alsoKnownList.size() - 1) {
                    alsoKnownSB.append(", ");
                }
            }
            alsoKnownAsTextView.setText(alsoKnownSB.toString());
        } else {
            alsoKnown.setVisibility(View.INVISIBLE);
            alsoKnownAsTextView.setVisibility(View.INVISIBLE);
        }

        StringBuilder ingredientsSB = new StringBuilder();
        List<String> ingredientsList = sandwich.getIngredients();
        for (int i = 0; i < ingredientsList.size(); i++) {
            ingredientsSB.append(ingredientsList.get(i));
            if (i < ingredientsList.size() - 1) {
                ingredientsSB.append("\n");
            }
        }
        ingredientsTextView.setText(ingredientsSB.toString());

        if (!sandwich.getPlaceOfOrigin().isEmpty()) {
            placeOfOriginTextView.setText(sandwich.getPlaceOfOrigin());
        } else {
            placeOfOrigin.setVisibility(View.INVISIBLE);
            placeOfOriginTextView.setVisibility(View.INVISIBLE);
        }

        descriptionTextView.setText(sandwich.getDescription());
    }
}