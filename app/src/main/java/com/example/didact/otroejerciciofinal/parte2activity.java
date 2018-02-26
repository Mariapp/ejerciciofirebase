package com.example.didact.otroejerciciofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class parte2activity extends AppCompatActivity {


    EditText etnombre,etdorsal,etposicion,etsueldo;

    Spinner spinerjugadores;

    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte2activity);



        etnombre=(EditText)findViewById(R.id.etnombre);
        etdorsal=(EditText)findViewById(R.id.etdorsal);
        etposicion=(EditText)findViewById(R.id.etposicion);
        etsueldo=(EditText)findViewById(R.id.etsueldo);

        spinerjugadores=(Spinner) findViewById(R.id.spinerjugadores);



            //crear array y adaptador
        String[] jugadores={"","j1","j2","j3","j4","nuevo jugador"};
        ArrayAdapter<String> Adaptador=new ArrayAdapter <String>(this,
                android.R.layout.simple_expandable_list_item_1,jugadores);
            //llamar al adaptador
        spinerjugadores.setAdapter(Adaptador);
            //nueva variable string con el item seleccionado
        String jugadorseleccionado= spinerjugadores.getSelectedItem().toString();




    }//fin del oncrate



    private void cargardatosFirebase (){
//referencia a la base de datos
        dbRef= FirebaseDatabase.getInstance().getReference().child("jugadores");
//añadimos el evento que nos devolvera los valores
        valueEventListener=new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){


                Cjugador jug=dataSnapshot.getValue(Cjugador.class);
                etnombre.setText("nombre "+jug.getNombre());
                etdorsal.setText("dorsal "+jug.getDorsal());
                etposicion.setText("posicion "+jug.getPosicion());
                etsueldo.setText("sueldo "+jug.getSueldo());
                }

            @Override
            public void onCancelled(DatabaseError databaseError){
                Log.e("segundaactivity", "Database ERROR");


            }

        }; dbRef.addValueEventListener(valueEventListener);
}

    public void seleccionar (View view){

        //ide del spinner

     String jugadorseleccionado= spinerjugadores.getSelectedItem().toString();


        //llamar a la base de datos y el item seleccionado jugador/
     dbRef= FirebaseDatabase.getInstance().getReference().child("jugadores/"+jugadorseleccionado);
     valueEventListener=new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {

             Cjugador jug=dataSnapshot.getValue(Cjugador.class);
             etnombre.setText("nombre "+jug.getNombre());
             etdorsal.setText("dorsal "+jug.getDorsal()+"");
             etposicion.setText("posicion "+jug.getPosicion());
             etsueldo.setText("sueldo "+jug.getSueldo()+"");


         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             Log.e("");

         }
     };

     // dbRef.addValueEventListener(valueEventListener);//si queremos base de datos a tiempo real
     dbRef.addListenerForSingleValueEvent(valueEventListener);//si queremos base de datos de 1 sola carga
     //dbRef.removeEventListener(valueEventListener);//destruye la conexion a tiempo real.

 }

    public void guardar (View v){

        final String cajanombre= etnombre.getText().toString();
        final String cajadorsal= etdorsal.getText().toString();
        final String cajaposicion= etposicion.getText().toString();
        final String cajasueldo= etsueldo.getText().toString();

        String jugadorseleccionado= spinerjugadores.getSelectedItem().toString();




        dbRef= FirebaseDatabase.getInstance().getReference().child("jugadores/"+jugadorseleccionado);
       // valueEventListener=new ValueEventListener() {






        //etsueldo.setText("nombre"+cajasueldo);
        //etnombre.setText("nombre"+cajanombre);
        //etposicion.setText("nombre"+cajaposicion);
        //etdorsal.setText("nombre"+cajadorsal);

      //  Cjugador nuevojugador =getText().toString(cajanombre,cajadorsal,cajaposicion,cajasueldo);
            //guardar valores en base de datos:

              //  String nueva_clave=dbRef.push().setValue(nuevoJugador, new DatabaseReference());
               // Cjugador nuevoJugador=new Cjugador(dorsal,nombre, posicion, sueldo);
            //dbRef= FirebaseDatabase.getInstance().getReference();
              //  dbRef.child("j7").setValue(nuevoJugador, (completionListener));


    }

            //@Override
            //public void onCancelled(DatabaseError databaseError) {

            }
        };
 }


   // public void eliminar (View v){

 }




}//Fin del main
