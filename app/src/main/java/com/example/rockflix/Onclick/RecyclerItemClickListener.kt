package com.example.rockflix.Onclick

//criando um arquivo vazio
//para criar esse file com o botão direito sobre a pasta "Onclick" > "new" > "Kotlin class/file" coloque o nome e escolha "file"

//Criando o codigo para poder por evento de click na capa dos shows
import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object :
        RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }

        //altere o AdapterPosition para = bindingAdapterPosition
        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener({
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.bindingAdapterPosition, view)
            })
        }
    })
}