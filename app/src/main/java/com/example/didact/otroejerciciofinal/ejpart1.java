package com.example.didact.otroejerciciofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ejpart1 extends AppCompatActivity {

    TextView tvejp1nombre,tvejp1dorsal,tvejp1posicion,tvejp1sueldo;
    Spinner spinnerjugadores;

    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejpart1);

        tvejp1nombre=(TextView)findViewById(R.id.tvejp1nombre);
        tvejp1dorsal=(TextView)findViewById(R.id.tvejp1dorsal);
        tvejp1posicion=(TextView)findViewById(R.id.tvejp1posicion);
        tvejp1sueldo=(TextView)findViewById(R.id.tvejp1sueldo);


        spinnerjugadores=(Spinner)findViewById(R.id.spinnerjugadores);
        String[] jugadores={"","j1","j2","j3","j4"};
        ArrayAdapter<String> Adaptador=new ArrayAdapter <String>(this,
                android.R.layout.simple_expandable_list_item_1,jugadores);
        spinnerjugadores.setAdapter(Adaptador);
        String jugadorseleccionado= spinnerjugadores.getSelectedItem().toString();


        if(jugadorseleccionado.equals("")) {
            Toast.makeText(this, "Debes seleccionar un tipo de masa",
                    Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Has elegido "+jugadorseleccionado,
                    Toast.LENGTH_LONG).show();

        }





}


    public void btnver (View view){


        String jugadorseleccionado= spinnerjugadores.getSelectedItem().toString();



        dbRef= FirebaseDatabase.getInstance().getReference().child("jugadores/"+jugadorseleccionado);
        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Cjugador jug=dataSnapshot.getValue(Cjugador.class);
                tvejp1nombre.setText("nombre "+jug.getNombre());
                tvejp1dorsal.setText("dorsal "+jug.getDorsal());
                tvejp1posicion.setText("posicion "+jug.getPosicion());
                tvejp1sueldo.setText("sueldo "+jug.getSueldo());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

       // dbRef.addValueEventListener(valueEventListener);//si queremos base de datos a tiempo real
         dbRef.addListenerForSingleValueEvent(valueEventListener);//si queremos base de datos de 1 sola carga
        //dbRef.removeEventListener(valueEventListener);//destruye la conexion a tiempo real.

    }

    }

