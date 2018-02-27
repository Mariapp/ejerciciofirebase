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


    EditText etnombre, etdorsal, etposicion, etsueldo;

    Spinner spinerjugadores;

    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte2activity);


        etnombre = (EditText) findViewById(R.id.etnombre);
        etdorsal = (EditText) findViewById(R.id.etdorsal);
        etposicion = (EditText) findViewById(R.id.etposicion);
        etsueldo = (EditText) findViewById(R.id.etsueldo);

        spinerjugadores = (Spinner) findViewById(R.id.spinerjugadores);


        //crear array y adaptador
        String[] jugadores = {"", "j1", "j2", "j3", "j4", "j5", "j6", "j7", "j8"};
        ArrayAdapter <String> Adaptador = new ArrayAdapter <String>(this,
                android.R.layout.simple_expandable_list_item_1, jugadores);
        //llamar al adaptador
        spinerjugadores.setAdapter(Adaptador);
        //nueva variable string con el item seleccionado
        String jugadorseleccionado = spinerjugadores.getSelectedItem().toString();
        cargardatosFirebase();


    }//fin del oncrate


    private void cargardatosFirebase() {
//referencia a la base de datos
        dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores");
//añadimos el evento que nos devolvera los valores
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Cjugador jug = dataSnapshot.getValue(Cjugador.class);
                etnombre.setText(jug.getNombre());
                etdorsal.setText("" + jug.getDorsal());
                etposicion.setText(jug.getPosicion());
                etsueldo.setText("" + jug.getSueldo());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("segundaactivity", "Database ERROR");


            }

        };
        dbRef.addValueEventListener(valueEventListener);
    }

    public void seleccionar(View view) {

        //ide del spinner

        String jugadorseleccionado = spinerjugadores.getSelectedItem().toString();


        //llamar a la base de datos y el item seleccionado jugador/
        dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores/" + jugadorseleccionado);
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Cjugador jug = dataSnapshot.getValue(Cjugador.class);
                if(jug!=null) {
                    etnombre.setText(jug.getNombre());
                    etdorsal.setText(jug.getDorsal() + "");
                    etposicion.setText(jug.getPosicion());
                    etsueldo.setText(jug.getSueldo() + "");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("segundaactivity", "Database ERROR");

            }
        };

        // dbRef.addValueEventListener(valueEventListener);//si queremos base de datos a tiempo real
        dbRef.addListenerForSingleValueEvent(valueEventListener);//si queremos base de datos de 1 sola carga
        //dbRef.removeEventListener(valueEventListener);//destruye la conexion a tiempo real.

    }


    public void guardar(View view) {

        String nombre = etnombre.getText().toString();
        String strDorsal = etdorsal.getText().toString();
        String posicion = etposicion.getText().toString();
        String strSueldo = etsueldo.getText().toString();

        if (nombre.equals("") || strDorsal.equals("") || posicion.equals("") || strSueldo.equals("")) {
            Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
        } else {
            int dorsal = Integer.parseInt(strDorsal);
            int sueldo = Integer.parseInt(strSueldo);
            Cjugador nuevoJugador = new Cjugador(nombre, dorsal, posicion, sueldo);
            dbRef = FirebaseDatabase.getInstance().getReference()
                    .child("jugadores");

            //String nueva_clave = dbRef.push().setValue(nuevoJugador, new DatabaseReference.CompletionListener(){
            dbRef.child("j9").setValue(nuevoJugador, new DatabaseReference.CompletionListener() {

                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if (error == null) {

                        Toast.makeText(getApplicationContext(),
                                "INSERTADO CORRECTAMENTE",
                                Toast.LENGTH_LONG).show();

                        limpiarFormulario();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "NO SE PUEDE INSETAR EL JUGADOR",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    public void modificar(View v) {
        String nombre = etnombre.getText().toString();
        String strDorsal = etdorsal.getText().toString();
        String posicion = etposicion.getText().toString();
        String strSueldo = etsueldo.getText().toString();

        if (nombre.equals("") || strDorsal.equals("") || posicion.equals("") || strSueldo.equals("")) {
            Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
        } else {
            int dorsal = Integer.parseInt(strDorsal);
            int sueldo = Integer.parseInt(strSueldo);
            Cjugador nuevoJugador = new Cjugador(nombre, dorsal, posicion, sueldo);
            dbRef = FirebaseDatabase.getInstance().getReference()
                    .child("jugadores");


            //String nueva_clave = dbRef.push().setValue(nuevoJugador, new DatabaseReference.CompletionListener(){
            String idseleccionada = spinerjugadores.getSelectedItem().toString();
            dbRef.child(idseleccionada).setValue(nuevoJugador, new DatabaseReference.CompletionListener() {

                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if (error == null) {

                        Toast.makeText(getApplicationContext(),
                                "MODIFICADO CORRECTAMENTE",
                                Toast.LENGTH_LONG).show();

                        limpiarFormulario();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "NO SE PUEDE MODIFICAR EL JUGADOR",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        }


    }


    public void eliminar(View v) {

        dbRef = FirebaseDatabase.getInstance().getReference()
                .child("jugadores");

        String idseleccionada = spinerjugadores.getSelectedItem().toString();
        dbRef.child(idseleccionada).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference databaseReference) {
                if (error == null) {

                    Toast.makeText(getApplicationContext(),
                            "ELIMINADO CORRECTAMENTE",
                            Toast.LENGTH_LONG).show();

                    limpiarFormulario();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "NO SE PUEDE ELIMINAR EL JUGADOR",
                            Toast.LENGTH_LONG).show();
                }


            }


        });
    }


    private void limpiarFormulario() {
        etnombre.setText("");
        etdorsal.setText("");
        etposicion.setText("");
        etsueldo.setText("");
    }

}//Fin del main