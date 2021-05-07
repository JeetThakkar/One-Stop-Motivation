package com.test;

import android.Manifest;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.spotify.test.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;


public class AddNewLender extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    public static final String[] PROJECTION = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
    };
    public static final String SELECTION = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?";

    String t = "";

    private ArrayAdapter<String> contactAdapter;
    private AutoCompleteTextView queryBox;
    private ArrayList<String> contactNameList = new ArrayList<>();
    private ArrayList<String> contactNumberList = new ArrayList<>();
    private LoaderManager loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    0);

        setContentView(R.layout.activity_add_new_lender);
        loader = getLoaderManager();
        queryBox = findViewById(R.id.lenderNameAddNewLender);
        contactAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                contactNameList
        );
        queryBox.setThreshold(2);
        queryBox.setOnItemClickListener(this);
        queryBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1 && !t.contains(s)) {
                    t += s;
                    runOnUiThread(() -> queryBox.setAdapter(null));
                    new Thread(() -> {
                        readContacts(s.toString());
                        runOnUiThread(() -> {
                            Toast.makeText(AddNewLender.this, "Finish Loading", Toast.LENGTH_SHORT).show();
                            queryBox.setAdapter(contactAdapter);
                        });
                    }).start();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void readContacts(String str) {
        String phoneNumber;
        ContentResolver cr = getBaseContext().getContentResolver();
        try {

            Cursor readContentCursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    PROJECTION,
                    SELECTION,
                    new String[]{str + "%"},
                    null);
            runOnUiThread(() -> Toast.makeText(this, "Length " + Objects.requireNonNull(readContentCursor).getCount(), Toast.LENGTH_SHORT).show());
            if (Objects.requireNonNull(readContentCursor).getCount() > 0) {
                while (readContentCursor.moveToNext()) {
                    String id = readContentCursor.getString(
                            readContentCursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = readContentCursor.getString(
                            readContentCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));

                    if (Integer.parseInt(
                            readContentCursor.getString(
                                    readContentCursor.getColumnIndex(
                                            ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        Cursor phonenocursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id},
                                null);
                        int j = 0;
                        while (Objects.requireNonNull(phonenocursor).moveToNext()) {
                            if (j == 0) {
                                phoneNumber = "" + phonenocursor.getString(
                                        phonenocursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                contactAdapter.add(name);
                                contactNameList.add(name);
                                contactNumberList.add(phoneNumber);
                                j++;
                            }
                        }
                        phonenocursor.close();
                    }
                }
            }
            readContentCursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        queryBox.setText(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NotNull String[] permissions,@NotNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                onCreate(null);
            else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, 0);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}