package com.example.pro4.smartbarbell;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataJSONActivity extends AppCompatActivity {
    private static HashMap<String, ArrayList<Object>> workoutMap;
    private static HashMap<String, ArrayList<Object>> exerciseMap;

    private static JSONObject workoutJSON;
    private static JSONObject exerciseJSON;

    private static final String filenameWorkoutJSON = "workout.json";
    private static final String filenameExerciseJSON = "exercise.json";

    private static Gson gson;

    private static DatabaseReference mDatabaseReference;

    public static void init(Context context) {
        DataJSONActivity.workoutMap = new HashMap<>();
        DataJSONActivity.exerciseMap = new HashMap<>();

        DataJSONActivity.workoutJSON = new JSONObject();
        DataJSONActivity.exerciseJSON = new JSONObject();

        gson = new Gson();

        loadWorkout(context);
        loadExercise(context);
        initFirebaseDB(context);
    }

    public static void addWorkout(Context context, String workoutName, ArrayList<Object> exerciseList) {
        loadWorkout(context);
        DataJSONActivity.workoutMap.put(workoutName, exerciseList);
        saveWorkout(context);
    }

    public static void deleteWorkout(Context context, String workoutName) {
        loadWorkout(context);
        // remove workout entirely
        if (DataJSONActivity.workoutMap.remove(workoutName) != null) {
            Toast.makeText(context, "Workout '" + workoutName + "' removed.", Toast.LENGTH_SHORT).show();
        }
        saveWorkout(context);
    }

    public static void addExercise(Context context, String exerciseName, String workoutName) {
        loadWorkout(context);
        // add exercise to this workout
        ArrayList<Object> exercisesOfWorkout = DataJSONActivity.workoutMap.get(workoutName);
        exercisesOfWorkout.add(exerciseName);
        DataJSONActivity.workoutMap.put(workoutName, exercisesOfWorkout);
        // -- //
        saveWorkout(context);
    }

    public static void deleteExercise(Context context, String exerciseName, String workoutName) {
        loadWorkout(context);
        // remove exercise from this workout
        ArrayList<Object> arrayList = DataJSONActivity.workoutMap.get(workoutName);
        if (arrayList.remove(exerciseName)) {
            Toast.makeText(context, "Exercise removed from '" + workoutName + "'.", Toast.LENGTH_SHORT).show();
        }
        DataJSONActivity.workoutMap.put(workoutName, arrayList);
        // -- //
        saveWorkout(context);
    }

    public static void deleteExerciseForAll(Context context, String exerciseName) {
        loadWorkout(context);
        // remove exercise from all workouts
        for (String key : DataJSONActivity.workoutMap.keySet()) {
            ArrayList<Object> arrayList = DataJSONActivity.workoutMap.get(key);
            if (arrayList.contains(exerciseName)) {
                arrayList.remove(exerciseName);
            }
            DataJSONActivity.workoutMap.put(key, arrayList);
        }
        saveWorkout(context);
    }

    public static void addExerciseData(Context context, String exerciseName, ArrayList<Object> exerciseData) {
        loadExercise(context);
        // add exercise data to this exercise
        ArrayList<Object> exerciseEntries = exerciseMap.get(exerciseName);
        if (exerciseEntries == null) {
            exerciseEntries = new ArrayList<>();
        }
        exerciseEntries.addAll(exerciseData);
        DataJSONActivity.exerciseMap.put(exerciseName, exerciseEntries);
        // -- //
        saveExercise(context);
    }

    public static void deleteExerciseData(Context context, String exerciseName) {
        loadExercise(context);
        // remove data of this exercise
        DataJSONActivity.exerciseMap.remove(exerciseName);
        Toast.makeText(context, "Data of exercise '" + exerciseName + "' deleted.", Toast.LENGTH_SHORT).show();
        // -- //
        saveExercise(context);
    }

    // --- Firebase Realtime Database -- //

    private static boolean initFirebaseDB(Context context) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child("90001114");
        return addDBValueEventListener(context);
    }

    private static boolean addDBValueEventListener(final Context context) {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataJSONActivity.processData(dataSnapshot, context);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return true;
    }

    private static void processData(DataSnapshot dataSnapshot, Context context) {
        // get entire exercise
        for (DataSnapshot exercise : dataSnapshot.getChildren()) {
            // --- //
            String exerciseName = exercise.getKey(); // key
            ArrayList<Object> exerciseData = new ArrayList<>(); // value(S) to be PUSHED -- add every entry to this before setting the static Hashmap!
            // --- //

            // get actual values under the hash-strings
            for (DataSnapshot exerciseEntry : exercise.getChildren()) {
                // -- //
                ArrayList<Object> singleExerciseEntry = new ArrayList<>();
                ArrayList<Object> dataDistance = new ArrayList<>();
                ArrayList<Object> dataWeight = new ArrayList<>();
                String date = "";
                int reps = 0;
                int weight = 0;
                // -- //

                int iSubentry = 0;
                // get actual data
                for (DataSnapshot subentry : exerciseEntry.getChildren()) {
                    Log.d("DatabaseSnapshot2[" + exerciseName + " -> " + subentry.getKey() + " -> " + iSubentry + "] ", "\"" + subentry.getValue() + "\"");
                    switch (iSubentry) {
                        case 0:
                            ArrayList<Object> dataDistanceList = new ArrayList<>();
                            for (DataSnapshot snap : subentry.getChildren()) {
                                // add data + date to arrayList
                                String[][] data = new String[(int) subentry.getChildrenCount()][];
                                int i = 0;

                                for (DataSnapshot snapData : snap.getChildren()) {
                                    String[] entry = {snapData.getKey(), snapData.getValue().toString()};
                                    data[i] = entry;
                                    i++;
                                }
                                dataDistanceList.add(data);
                            }
                            dataDistance = dataDistanceList;
                            break;
                        case 1:
                            ArrayList<Object> dataWeightList = new ArrayList<>();
                            for (DataSnapshot snap : subentry.getChildren()) {
                                // add data + date to arrayList
                                String[][] data = new String[(int) subentry.getChildrenCount()][];
                                int i = 0;

                                for (DataSnapshot snapData : snap.getChildren()) {
                                    String[] entry = {snapData.getKey(), snapData.getValue().toString()};
                                    data[i] = entry;
                                    i++;
                                }
                                dataWeightList.add(data);
                            }
                            dataWeight = dataWeightList;
                            break;
                        case 2:
                            date = subentry.getValue().toString();
                            break;
                        case 3:
                            reps = Integer.parseInt(subentry.getValue().toString());
                            break;
                        case 4:
                            weight = Integer.parseInt(subentry.getValue().toString());
                            break;
                    }
                    iSubentry++;
                }
                singleExerciseEntry.add(dataDistance);
                singleExerciseEntry.add(dataWeight);
                singleExerciseEntry.add(date);
                singleExerciseEntry.add(reps);
                singleExerciseEntry.add(weight);
                exerciseData.add(singleExerciseEntry);
            }

            // check if there is any data present (cuz the database could be empty)
            if (!exerciseName.equals("") && exerciseData.size() > 0) {
                addExerciseData(context, exerciseName, exerciseData);

                // delete entries for this exercise
                //mDatabaseReference.child(exerciseName).removeValue();
            }
        }
    }

    // --- save/read JSON --- //

    private static boolean saveWorkout(Context context) {
        // https://en.proft.me/2014/06/21/how-readwrite-files-android/

        // try-with-resources statement
        try (FileOutputStream fileOutputStream = context.openFileOutput(filenameWorkoutJSON, Context.MODE_PRIVATE)) { // MODE_PRIVATE - create a new file or overwrite one if it already exists with the same name
            workoutJSON = (JSONObject) mapToJSON(DataJSONActivity.workoutMap);
            fileOutputStream.write(gson.toJson(DataJSONActivity.workoutJSON).getBytes());
            fileOutputStream.close();
            Log.i("saveWorkout()", "Successfully Copied JSON Object to File...");
            Log.i("saveWorkout()", "\nJSON Object: " + DataJSONActivity.workoutJSON);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("saveWorkout()", "Failed to save workouts.");
            return false;
        }
    }

    private static boolean loadWorkout(Context context) {
        JSONParser parser = new JSONParser();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput(filenameWorkoutJSON)));
            try {
                DataJSONActivity.workoutJSON = (JSONObject) parser.parse(bufferedReader);
                DataJSONActivity.workoutMap = JSONToHashMap(DataJSONActivity.workoutJSON);
                return true;
            } catch (ParseException | IOException | JSONException e) {
                e.printStackTrace();
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("File not Found", "File '" + filenameWorkoutJSON + "' does not exist. Creating new file.");
            return saveWorkout(context);
        }
    }

    private static boolean saveExercise(Context context) {
        // try-with-resources statement
        try (FileOutputStream fileOutputStream = context.openFileOutput(filenameExerciseJSON, Context.MODE_PRIVATE)) { // MODE_PRIVATE - create a new file or overwrite one if it already exists with the same name
            exerciseJSON = (JSONObject) mapToJSON(DataJSONActivity.exerciseMap);
            fileOutputStream.write(gson.toJson(DataJSONActivity.exerciseJSON).getBytes());
            fileOutputStream.close();
            Log.i("saveExercise()", "Successfully Copied JSON Object to File...");
            Log.i("saveExercise()", "\nJSON Object: " + gson.toJson(DataJSONActivity.exerciseJSON));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("saveExercise()", "Failed to save workouts.");
            return false;
        }
    }

    private static boolean loadExercise(Context context) {
        JSONParser parser = new JSONParser();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput(filenameExerciseJSON)));
            try {
                DataJSONActivity.exerciseJSON = (JSONObject) parser.parse(bufferedReader);
                DataJSONActivity.exerciseMap = JSONToHashMap(DataJSONActivity.exerciseJSON);
                return true;
            } catch (ParseException | IOException | JSONException e) {
                e.printStackTrace();
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("File not Found", "File '" + filenameExerciseJSON + "' does not exist. Creating new file.");
            return saveExercise(context);
        }
    }

    // if the workout is already in the list, return false
    public static boolean workoutAvailable(String workoutName) {
        return !DataJSONActivity.workoutMap.containsKey(workoutName);
    }

    // if the exercise is already in the list, return false
    public static boolean exerciseAvailable(String exerciseName, String workoutName) {
        return DataJSONActivity.workoutMap.get(workoutName) == null || !DataJSONActivity.workoutMap.get(workoutName).contains(exerciseName);
    }

    // clears map data, JSON data, and overwrites the files with empty ones
    public static void deleteJSONs(final Context context) {
        // confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure?")
                .setMessage("Do you really want to delete all Workout and Exercise data?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataJSONActivity.workoutMap = new HashMap<>();
                        DataJSONActivity.exerciseMap = new HashMap<>();

                        DataJSONActivity.workoutJSON = new JSONObject();
                        DataJSONActivity.exerciseJSON = new JSONObject();

                        saveWorkout(context);
                        saveExercise(context);
                        initFirebaseDB(context);

                        Toast.makeText(context, "Data successfully deleted.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("delete", "delete was cancelled");
                    }
                })
                .show();
    }

    // --- JSON TO HASHMAP --- ///

    private static Object mapToJSON(HashMap<String, ArrayList<Object>> map) {
        if (map.equals(DataJSONActivity.workoutMap)) {
            return new JSONObject(map);
        } else {
            try {
                return toJSON(map);
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
        }
    }

    public static Object toJSON(Object object) throws JSONException {
        if (object instanceof HashMap) {
            JSONObject json = new JSONObject();
            HashMap map = (HashMap) object;
            for (Object key : map.keySet()) {
                json.put(key.toString(), toJSON(map.get(key)));
            }
            return json;
        } else if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable) object)) {
                json.add(toJSON(value));
            }
            return json;
        } else {
            return object;
        }
    }

    private static HashMap<String, ArrayList<Object>> JSONToHashMap(JSONObject jsonObject) throws JSONException {
        return new HashMap<>(jsonToMap(jsonObject));
    }

    // --- //

    private static Map<String, ArrayList<Object>> jsonToMap(JSONObject json) throws JSONException {
        Map<String, ArrayList<Object>> retMap = new HashMap<>();
        if (json != null) {
            retMap = toMap(json);
        }
        return retMap;
    }

    private static Map<String, ArrayList<Object>> toMap(JSONObject object) throws JSONException {
        Map<String, ArrayList<Object>> map = new HashMap<>();

        // https://stackoverflow.com/questions/24371957/iterate-through-jsonobject-from-root-in-json-simple
        for (Object o : object.keySet()) {
            String key = (String) o;
            Object value = object.get(key);
            value = toList((JSONArray) value);
            map.put(key, (ArrayList<Object>) value);
        }
        return map;
    }

    private static ArrayList<Object> toList(JSONArray array) throws JSONException {
        ArrayList<Object> list = new ArrayList<Object>();

        for (int i = 0; i < array.size(); i++) {
            Object value = array.get(i);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }

            list.add(value);
        }
        return list;
    }

    // --- GETTERS --- //

    public static ArrayList<Object> getExerciseData(String exerciseName) {
        if (DataJSONActivity.exerciseMap.get(exerciseName) != null) {
            return new ArrayList<>(DataJSONActivity.exerciseMap.get(exerciseName));
        } else {
            return new ArrayList<>();
        }
    }

    public static ArrayList<Object> getExerciseListOfWorkout(String workoutName) {
        if (DataJSONActivity.workoutMap != null && DataJSONActivity.workoutMap.get(workoutName) != null) {
            return new ArrayList<>(DataJSONActivity.workoutMap.get(workoutName));
        } else {
            return new ArrayList<>();
        }
    }

    public static ArrayList<Object> getWorkoutList() {
        if (DataJSONActivity.workoutMap != null) {
            return new ArrayList<Object>(DataJSONActivity.workoutMap.keySet());
        } else {
            return new ArrayList<>();
        }
    }
}
