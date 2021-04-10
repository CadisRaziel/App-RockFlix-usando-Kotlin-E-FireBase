package com.example.rockflix

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //escondendo a action bars
        supportActionBar!!.hide()

        //deixando a splash screen com um tempo de duração de 2 segundos
        //assim que bater 2 segundos ele vai para tela de login
        //a gente vai colocar uma animação para isso
        Handler(Looper.getMainLooper()).postDelayed({
            AbrirTelaLogin()
        },2000)
    }

    //metodo para ir para proxima tela
    //temos que importar o Intent(ou se ele ficar vermelhor clique nele que vai aparecer para importar)
    private fun AbrirTelaLogin(){
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}