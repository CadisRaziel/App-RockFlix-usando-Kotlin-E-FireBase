package com.example.rockflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rockflix.Adapter.ShowsAdapter
import com.example.rockflix.Model.Shows
import com.example.rockflix.Model.addShows
import com.example.rockflix.Onclick.OnItemClickListener
import com.example.rockflix.Onclick.addOnItemClickListener
import com.example.rockflix.databinding.ActivityListaDeShowsBinding
import com.google.firebase.auth.FirebaseAuth

class ListaDeShows : AppCompatActivity() {

    private lateinit var binding: ActivityListaDeShowsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaDeShowsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //criamos a pasta menu
        //com o botão direito clique em "app" > "new> "Android Resource Directory e colocamos o nome de "menu"
        //em seguida na pasta menu clique com o botao direito "new" > "Menu Resource File" e colocamos o nome de menu_principal
        //ele vai criar um xml para criarmos algum layout (que no caso um menu para a toolbar aonde vai ter o botao logout)


        //para adptarmos os nossos dados qeu estão no Model de dados para nosso adapter e vai passar pro RecycleView
        //layoutManager = para organizar os filmes como definimos ou na horizontal ou na vertical ou em coluna ou em grades etc..
        //GridLayoutManager(applicationContext, 3)  = isso significa que quero colocar 3 colunas !!
        val recycler_shows = binding.RecycleView
        recycler_shows.adapter = ShowsAdapter(addShows()) //clique sobre o metodo e aperte alt + enter que vai aparecer a opção de importar
        recycler_shows.layoutManager = GridLayoutManager(applicationContext, 3)

        //colocando evento de click na capa dos shows
        recycler_shows.addOnItemClickListener(object: OnItemClickListener{
            //sobrescrita de metodo que esta criada na RecylerItemClickListenet.kt (na pasta Onclick)
            override fun onItemClicked(position: Int, view: View) {
                when{
                    //o primeiro item la da lista sempre começa na posição 0
                    //como estou fazendo manual eu posso repetir esse codigo e colocar para todos as capas de show que eu coloquei e alterando o numero
                    //porem vamos fazer em um só pois teria que fazer para as 21 capas de albuns manualmente cada um com sua tela
                    position == 0 -> DetalhesShows()
                }
            }
        })
    }

    //metodo para ir para tela de detalhes
    private fun DetalhesShows(){
        val intent = Intent(this, DetalhesShows::class.java)
        startActivity(intent)
    }

    //para criar esse codigo abaixo clique em algum canto aqui vazio com o botão direito "Generate" > "override Methods" digite "onCreateOptionsMenu"
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    //para criar esse codigo abaixo clique em algum canto vazio com o botão direito "Generate" > "override Methods" digite "onOptionsItemSelected"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //item é o que vem do menu para poder pegar o id
        when(item.itemId){
            R.id.deslogar -> { //depois que deslogar o usuario vai voltar para tela de login
                FirebaseAuth.getInstance().signOut()
                VoltarTelaLogin()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    //metodo para voltar para tela de login
    private fun VoltarTelaLogin(){
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}