package com.example.infinitygaming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.infinitygaming.adapter.BestSellerAdapter;
import com.example.infinitygaming.adapter.TrendyProductsAdapter;

import java.util.ArrayList;
import java.util.List;

public class Tienda extends AppCompatActivity {

    RecyclerView trendyRecyclerView,bestSellerRecyclerView;
    TrendyProductsAdapter trendyProductsAdapter;
    BestSellerAdapter bestSellerAdapter;
    List<TrendyProducts> trendyProductsList,bestSellerList;
    TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        trendyRecyclerView = findViewById(R.id.trendyRecycler);
        bestSellerRecyclerView = findViewById(R.id.bestSellerRecycler);
        this.nameText = findViewById(R.id.nameText);
        Intent i = getIntent();
        this.nameText.setText(i.getStringExtra("name"));

        trendyProductsList = new ArrayList<>();
        trendyProductsList.add(new TrendyProducts(1,"Elden Ring","Elden Ring para PC es un RPG de acción (ARPG) escrito por las superestrellas George RR Martin (el autor de la serie de libros Song of Ice and Fire que engendró la serie de televisión Game of Thrones) y Hidetaka Miyazaki (que es famoso por muchos videojuegos, como los de la serie Souls, Bloodborne o Sekiro, entre otros).","43,99€",R.drawable.eldenring));
        trendyProductsList.add(new TrendyProducts(2,"Dune", "Dune: Spice Wars es un juego de estrategia en tiempo real con elementos 4X de los creadores del aclamado Northgard. Ambientado en el revolucionario universo de Dune de Frank Herbert, deberás liderar a tu facción y luchar por el control y el dominio del inhóspito planeta desértico de Arrakis.","20,59€",R.drawable.dune));
        trendyProductsList.add(new TrendyProducts(3,"Nintendo Switch Sports","¡Logra la victoria con raquetazos, chutes y remates en Nintendo Switch Sports para Nintendo Switch!","37,99€",R.drawable.esports));
        trendyProductsList.add(new TrendyProducts(4,"LEGO Star Wars: The SkyWalker Saga","La galaxia es tuya con LEGO Star Wars: La Saga Skywalker. Vive los momentos inolvidables y llenos de acción de las nueve películas de la saga a través del humor característico que ofrece LEGO","36,59€",R.drawable.legostarwars));
        trendyProductsList.add(new TrendyProducts(5,"Wonderlands","¡Embárcate en una increíble aventura llena de extravagancias, asombro y armamento de alta potencia! Balas, magia y espadas tendrán cabida en este caótico mundo de fantasía que cobra vida gracias a la impredecible Tina Chiquitina.","44,99€",R.drawable.woderlands));
        trendyProductsList.add(new TrendyProducts(6,"Sifu","En Sifu, te pondrás en la piel de un joven estudiante de kung-fu que busca venganza.","28,99€",R.drawable.sifu));
        trendyProductsList.add(new TrendyProducts(7,"Warhammer 3","Total War: WARHAMMER III para PC es un videojuego de estrategia por turnos basado en el juego de mesa del mismo nombre y el tercero de la serie. Los jugadores se turnan para mover sus piezas / personajes por el mapa y aprender cómo administrar mejor sus asentamientos para lograr todos sus objetivos, derrotar a sus enemigos y llevarse bien con aquellos a quienes no pueden derrotar de inmediato.","28,99€",R.drawable.warhammer3));
        trendyProductsList.add(new TrendyProducts(8,"CoreKeeper","Atraído hacia una reliquia misteriosa, eres un explorador que despierta en una antigua caverna de criaturas, recursos y abalorios. Atrapado en las profundidades subterráneas, ¿estarán a la altura tus habilidades de supervivencia?","7,99€",R.drawable.corekeeper));

        bestSellerList = new ArrayList<>();
        bestSellerList.add(new TrendyProducts(1,"12 meses plus","Enfréntate a tus amigos y a jugadores como tú en partidas multijugador en línea repletas de acción en PS4. ¡No te pierdas ni una pizca de la acción! Te estamos esperando.","45,99€",R.drawable.doceplus));
        bestSellerList.add(new TrendyProducts(2,"10€ en play station store","Enfréntate a tus amigos y a jugadores como tú en partidas multijugador en línea repletas de acción en PS4. ¡No te pierdas ni una pizca de la acción! Te estamos esperando.","8,99€",R.drawable.diezeurosps));
        bestSellerList.add(new TrendyProducts(3,"GTA V: Premium Edition","Grand Theft Auto V para PC ofrece a los jugadores la opción de explorar el galardonado mundo de Los Santos y el condado de Blaine con una resolución de 4K y disfrutar del juego a 60 fotogramas por segundo.","9,99€",R.drawable.gtav));
        bestSellerList.add(new TrendyProducts(4,"20€ en play station store","Enfréntate a tus amigos y a jugadores como tú en partidas multijugador en línea repletas de acción en PS4. ¡No te pierdas ni una pizca de la acción! Te estamos esperando.","49,99€",R.drawable.veinteeurosps));
        bestSellerList.add(new TrendyProducts(5,"Elden Ring","Elden Ring para PC es un RPG de acción (ARPG) escrito por las superestrellas George RR Martin (el autor de la serie de libros Song of Ice and Fire que engendró la serie de televisión Game of Thrones) y Hidetaka Miyazaki (que es famoso por muchos videojuegos, como los de la serie Souls, Bloodborne o Sekiro, entre otros).","49,99€",R.drawable.eldenring));
        bestSellerList.add(new TrendyProducts(6,"50€ en play station store","Enfréntate a tus amigos y a jugadores como tú en partidas multijugador en línea repletas de acción en PS4. ¡No te pierdas ni una pizca de la acción! Te estamos esperando.","49,99€",R.drawable.cincuentaeurosps));
        bestSellerList.add(new TrendyProducts(7,"Sifu","En Sifu, te pondrás en la piel de un joven estudiante de kung-fu que busca venganza.","49,99€",R.drawable.sifu));
        bestSellerList.add(new TrendyProducts(8,"3 meses plus","Enfréntate a tus amigos y a jugadores como tú en partidas multijugador en línea repletas de acción en PS4. ¡No te pierdas ni una pizca de la acción! Te estamos esperando.","49,99€",R.drawable.tresplus));

        setTrendyRecycler(trendyProductsList);
        setBestSellerRecycler(bestSellerList);

    }

    private void setTrendyRecycler(List<TrendyProducts> dataList){
        trendyRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        trendyProductsAdapter = new TrendyProductsAdapter(this,dataList);
        trendyRecyclerView.setAdapter(trendyProductsAdapter);
    }

    private void setBestSellerRecycler(List<TrendyProducts> dataList){
        bestSellerRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        bestSellerAdapter = new BestSellerAdapter(this,dataList);
        bestSellerRecyclerView.setAdapter(bestSellerAdapter);
    }
}