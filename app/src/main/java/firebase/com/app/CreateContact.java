package firebase.com.app;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateContact extends AppCompatActivity {
    private EditText Name, LastName, Phone, Cellphone;
    private android.content.res.Resources Resources;
    private ArrayList<Contact> contacts;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_contact);

        Name = (EditText)findViewById(R.id.TxtName);
        LastName = (EditText)findViewById(R.id.TxtLastName);
        Phone = (EditText)findViewById(R.id.TxtPhone);
        Cellphone = (EditText)findViewById(R.id.TxtCellphone);

        Resources = this.getResources();
        contacts = Data.Get();
        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    public void Save(View view){
        String NameV, LastNameV, PhoneV, CellphoneV, ID;
        ID = (contacts.size()+1)+"";
        NameV = Name.getText().toString();
        LastNameV = LastName.getText().toString();
        PhoneV = Phone.getText().toString();
        CellphoneV = Cellphone.getText().toString();

        Contact C = new Contact(ID, NameV, LastNameV, PhoneV, CellphoneV);
       /* C.setID(ID);
        C.setName(NameV);
        C.setCellphone(CellphoneV);
        C.setLastName(LastNameV);
        C.setPhone(PhoneV);*/
        databaseReference.child("Contact").child(C.getID()).setValue(C);
        Toast.makeText(this, R.string.done, Toast.LENGTH_LONG).show();

    }
}