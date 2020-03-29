package com.glenkernick.apps.sandwichclub.utils;

import com.glenkernick.apps.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String IMAGE = "image";
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String INGREDIENTS = "ingredients";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject nameObject = jsonObject.optJSONObject(NAME);
            sandwich.setMainName(nameObject.optString(MAIN_NAME));
            JSONArray alsoKnownAsJsonArray = nameObject.optJSONArray(ALSO_KNOWN_AS);
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJsonArray.optString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownAs);
            sandwich.setPlaceOfOrigin(jsonObject.optString(PLACE_OF_ORIGIN));
            sandwich.setDescription(jsonObject.optString(DESCRIPTION));
            sandwich.setImage(jsonObject.optString(IMAGE));
            JSONArray ingredientsJsonArray = jsonObject.optJSONArray(INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                ingredients.add(ingredientsJsonArray.optString(i));
            }
            sandwich.setIngredients(ingredients);

        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return sandwich;
    }
}