package com.example.rockflix

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.rockflix.databinding.ActivityVideoBinding

class Video : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Escondendo a actionbar
        supportActionBar!!.hide()

        //Processo para poder executar um video
        //pegue o token do video no storage do firebase e cole aqui
        val videoUrl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/rockflix-b475f.appspot.com/o/videos.mp4?alt=media&token=584f1a40-0bec-4c85-9039-3ae40f2bf2f1")

        val video = binding.video
        //setMediaController() = para colocar pausar, avançar
        //setVideoURI() = para pegar o video atraves da url
        //requestFocus() = Use esta função para solicitar foco.
        //video.start() = quando eu clicar no play automaticamente vai ser executado
        video.setMediaController(MediaController(this))
        video.setVideoURI(videoUrl)
        video.requestFocus()
        video.start()
    }
}