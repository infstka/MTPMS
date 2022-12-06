package com.mtpms.lr12

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtpms.lr12.Adapter.Adapter
import com.mtpms.lr12.Common.Common
import com.mtpms.lr12.Interface.RetrofitInterface
import com.mtpms.lr12.Model.Coin
import com.mtpms.lr12.Model.Movie
import com.mtpms.lr12.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    lateinit var mService: RetrofitInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: Adapter
    lateinit var dialog: AlertDialog
    lateinit var recyclerMovieList: RecyclerView;
    var Lise: List<Coin> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitService
        recyclerMovieList = findViewById(R.id.recyclerMovieList)
        layoutManager = LinearLayoutManager(this)
        recyclerMovieList.layoutManager = layoutManager



        launch(Dispatchers.Main) {
            mService = Common.retrofitService
            mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
                override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MutableList<Movie>>,
                    response: Response<MutableList<Movie>>
                ) {
                    adapter = Adapter(baseContext, response.body() as MutableList<Movie>)
                    adapter.notifyDataSetChanged()
                    recyclerMovieList.adapter = adapter

                }
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_recipe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.item1 -> {
                val intent = Intent(this, MainActivity::class.java).apply {
                }
                startActivity(intent)
                return true
            }
            R.id.item2 -> {
                val intent = Intent(this, CoinActivity::class.java).apply {
                }
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}


