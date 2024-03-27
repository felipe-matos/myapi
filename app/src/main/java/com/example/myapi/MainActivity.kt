package com.example.myapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapi.api.FraseAPI
import com.example.myapi.api.RetrofitHelper
import com.example.myapi.databinding.ActivityMainBinding
import com.example.myapi.model.Frase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnIniciar.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                recuperarFrases()
            }

        }


    }

    private suspend fun recuperarFrases() {

        var retorno : Response<List<Frase>>? = null


        try {

            val fraseAPI = retrofit.create( FraseAPI::class.java )
                retorno = fraseAPI.recuperarFrases()
        } catch (e:Exception){
            e.printStackTrace()
            Log.i("info_api","Erro ao Recuperar")
        }

        if(retorno != null){

            if(retorno.isSuccessful){

                val listaFrases = retorno.body()

                withContext(Dispatchers.Main){
                    listaFrases?.forEach {frase->

                        binding.textResultado.text = "Resultado "

                        val author = frase.author
                        val quote = frase.quote

                        binding.textResultado.text = "\n Autor: $author \n Citacao: $quote  "

                        //Log.i("info_api","A: $author Q: $quote")

                    }


                }



            }

        }
    }

}