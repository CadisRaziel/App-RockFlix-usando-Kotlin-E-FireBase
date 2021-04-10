package com.example.rockflix.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockflix.Model.Shows
import com.example.rockflix.databinding.ListaShowsBinding


//esse Shows é o Show.kt dentro da pasta Model
//quando tiver terminando de criar o inner class aqui no "class ShowAdapter" vai dar um erro, clique sobre ele e na lampada vermelha clique no "Implemente members" e selecione os 3 que aparece e de Ok ele vai criar 3 metodos !!
class ShowsAdapter (val shows: MutableList<Shows>): RecyclerView.Adapter<ShowsAdapter.ShowsViewHolder>(){


    //parte onde vai passar para o adaptador qual é o layout que ele precisa renderizar na nossa lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsViewHolder {
        val binding = ListaShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowsViewHolder(binding) //aqui ele vai capturar o ListaDeShows.xml
    }


    //posição dos itens da nossa lista
    //position = caso queira colocar uma opção de clique nos shows eu vou conseguir baseado na position(posições) dos shows
    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        with(holder){
            with(shows[position]){
                bindind.capaShow.setImageResource(capaShow)
            }
        }
    }

    //quantidade de itens que tem na nossa lista
    //pegando a quantidade de itens de forma dinamica, para que conforme a aplicação for crescendo ela vai se adptando com a quantidade de itens
    override fun getItemCount() = shows.size



    //inner class é uma classe interna
    inner class ShowsViewHolder(val bindind: ListaShowsBinding): RecyclerView.ViewHolder(bindind.root){

    }
}