package com.example.rockflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rockflix.Adapter.ShowsAdapter
import com.example.rockflix.Model.addShows
import com.example.rockflix.databinding.ActivityDetalhesShowsBinding
import com.example.rockflix.databinding.ActivityListaDeShowsBinding
import com.squareup.picasso.Picasso

class DetalhesShows : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesShowsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesShowsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        //colocando a lista de shows
        val recycler_outrosShows = binding.RecyclerOutrosShows
        recycler_outrosShows.adapter = ShowsAdapter(addShows())
        recycler_outrosShows.layoutManager = GridLayoutManager(applicationContext, 3)

        //pegando imagem/video do servidor firebase
        //clique na imagem la no servidor e copie o token de acesso e cole aqui
        val capaLinkinPark = "https://firebasestorage.googleapis.com/v0/b/rockflix-b475f.appspot.com/o/chester.jpg?alt=media&token=189305bf-cc64-496a-94f8-f6adefe03785"

        //agora usando a biblioteca picasso para colocar dentro da capaShow (ela vai se redimensionar automaticamente)
        //load() = para ele pegar a variavel capaLinkinPark que contem o link(token) do servidor
        //fit() = para ajustar a imagem conforme definimos no layout do imageView
        //into() = dentro do imageView
        Picasso.get().load(capaLinkinPark).fit().into(binding.capa)

        //ao clicar no icone "play" vai renderizar o video e apresenta-lo então vamos por um evento de click nele
        binding.PlayVideo.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            startActivity(intent)

        }
    }

    //metodo para toolbar para colocarmos um icone de voltar
    private fun Toolbar(){
        val toolbar_detalhes = binding.toolbarDetalhes
        toolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar))
        toolbar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this, ListaDeShows::class.java)
            startActivity(intent)
            finish()
        }
    }
}