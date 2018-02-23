package com.example.didact.otroejerciciofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class parte1activity extends AppCompatActivity {
    TextView textViewparte1;
    private DatabaseReference dbRef;

    //el evento Value nos permite recoger los datos a tiempo real
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte1activity);



        textViewparte1=(TextView)findViewById(R.id.textViewparte1);
        dbRef= FirebaseDatabase.getInstance().getReference().child("jugadores/j1");
        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Cjugador jug=dataSnapshot.getValue(Cjugador.class);
                textViewparte1.setText("nombre "+jug.getNombre()+"\n"+
                                        "dorsal "+jug.getDorsal()+"\n"+
                                        "posicion "+jug.getPosicion()+"\n"+
                                        "sueldo "+jug.getSueldo());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbRef.addValueEventListener(valueEventListener);//si queremos base de datos a tiempo real
       // dbRef.addListenerForSingleValueEvent(valueEventListener);//si queremos base de datos de 1 sola carga
        //dbRef.removeEventListener(valueEventListener);//destruye la conexion a tiempo real.

    }
}
