package com.example.didact.otroejerciciofinal; /**
 * Created by DIDACT on 26/02/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.didact.otroejerciciofinal.Cjugador;
import com.example.didact.otroejerciciofinal.R;

import java.util.ArrayList;

public class AdaptadorJugadores extends ArrayAdapter<Cjugador> {
    ArrayList<Cjugador> jugadores;
    Context c;
    public AdaptadorJugadores(Context c, ArrayList<Cjugador> coches) {
        super(c, R.layout.item_jugadores, coches);
        this.jugadores = coches;
        this.c = c;
    }
    public View getView(int position, View view, ViewGroup
            viewGroup) {
        LayoutInflater inflater =
                LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_jugadores, null);

        TextView tv1 = (TextView)
                item.findViewById(R.id.tv1);
        tv1.setText(jugadores.get(position).getNombre() );

        TextView tv2 = (TextView)
                item.findViewById(R.id.tv2);
        tv2.setText(jugadores.get(position).getDorsal()+"" );


        TextView tv3 = (TextView)
                item.findViewById(R.id.tv3);
        tv3.setText(jugadores.get(position).getPosicion() );


        TextView tv4 = (TextView)
                item.findViewById(R.id.tv4);
        tv4.setText(jugadores.get(position).getSueldo() +"");



        return item;
    }
}