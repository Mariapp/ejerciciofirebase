package com.example.didact.otroejerciciofinal;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.AdapterView;
        import android.widget.ListView;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;

public class segundaactivity extends AppCompatActivity {

    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;


    ListView listviewjugadores;
    ArrayList<Cjugador> Lista_jugadores = new ArrayList<Cjugador>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundaactivity);


        listviewjugadores=(ListView)findViewById(R.id.listviewjugadores);
        cargardatosFirebase();




    }//Fin del Oncreate




    private void cargardatosFirebase (){
//referencia a la base de datos
        dbRef= FirebaseDatabase.getInstance().getReference().child("jugadores");
//añadimos el evento que nos devolvera los valores
        valueEventListener=new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                //
                Lista_jugadores.clear();
                for (DataSnapshot jugadoresDataSnapshot: dataSnapshot.getChildren()){
                    cargardatoslistview(jugadoresDataSnapshot);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                Log.e("segundaactivity", "Database ERROR");


            }

        };//asignamos el evento para que sea en tiempo real
        dbRef.addValueEventListener(valueEventListener);
    }



    private void cargardatoslistview (DataSnapshot dataSnapshot){

        Lista_jugadores.add(dataSnapshot.getValue(Cjugador.class));
        AdaptadorJugadores adapter=new AdaptadorJugadores(getApplicationContext(),Lista_jugadores);
        listviewjugadores.setAdapter(adapter);




    }


}



