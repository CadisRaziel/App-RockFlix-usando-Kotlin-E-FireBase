package com.example.rockflix.Model

import com.example.rockflix.R


//coloque data antes da "class" e tire as {} pois sera ()
data class Shows (
    //como sao dados falsos nos colocamos Int
    //se os dados viessem de uma API seria String
    val capaShow: Int
)

class ShowsBuilder{
    var capaShow: Int = 0
    fun build(): Shows = Shows(capaShow)
}

//função para associar as duas classes acima
fun shows(block: ShowsBuilder.() -> Unit): Shows = ShowsBuilder().apply(block).build()

//função para adicionar os filmes de forma manual
fun addShows(): MutableList<Shows> = mutableListOf(
    //da para buscar com uma api !! ao invez de fazer manual
    //entao primeiro faça manualmente para ver se esta tudo funcionando depois crie a api para pegar de uma database
    shows {
        capaShow = R.drawable.capa1
    },
    shows {
        capaShow = R.drawable.capa2
    },
    shows {
        capaShow = R.drawable.capa3
    },
    shows {
        capaShow = R.drawable.capa4
    },
    shows {
        capaShow = R.drawable.capa5
    },
    shows {
        capaShow = R.drawable.capa6
    },
    shows {
        capaShow = R.drawable.capa7
    },
    shows {
        capaShow = R.drawable.capa8
    },
    shows {
        capaShow = R.drawable.capa9
    },
    shows {
        capaShow = R.drawable.capa10
    },
    shows {
        capaShow = R.drawable.capa11
    },
    shows {
        capaShow = R.drawable.capa12
    },
    shows {
        capaShow = R.drawable.capa13
    },
    shows {
        capaShow = R.drawable.capa14
    },
    shows {
        capaShow = R.drawable.capa15
    },
    shows {
        capaShow = R.drawable.capa16
    },
    shows {
        capaShow = R.drawable.capa17
    },
    shows {
        capaShow = R.drawable.capa18
    },
    shows {
        capaShow = R.drawable.capa19
    },
    shows {
        capaShow = R.drawable.capa20
    },
    shows {
        capaShow = R.drawable.capa21
    },

)

