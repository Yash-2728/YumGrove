package com.example.yumgrove

import android.app.ActionBar.LayoutParams
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.yumgrove.databinding.ActivityHomeBinding
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: LiveData<List<Recipe>>
    private var popupWindow: PopupWindow? = null
    private lateinit var database: AppDatabase
    private lateinit var dao: Dao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = AppDatabase.getDatabase(this)
        dao = database.getDao()
        dataList = dao.getAll()

        setUpRecyclerView()

        dataList.observe(this, Observer {
            rvAdapter.changeData(it)
        })

        binding.addbutton.setOnClickListener {
            Toast.makeText(this, "clicked:", Toast.LENGTH_LONG).show()
//            showPopup()
            deleteAll()



        }
        binding.search.setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

//    private fun update(){
//        val nr = Recipe(
//            uid = 1,
//            tittle =  "icecream",
//            des = "nahi pata",
//            category = "milk",
//            img = "https://www.google.co.in/url?sa=i&url=https%3A%2F%2Fwww.shutterstock.com%2Fsearch%2Fice-cream&psig=AOvVaw1f4_aXfgPmp43vMOrE-JHv&ust=1707735800571000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCODl3uKRo4QDFQAAAAAdAAAAABAE",
//            ing = "ice and cream"
//        )
//        dao.insert(nr)
//    }

    private fun deleteAll(){
        dao.deleteAll()
    }

    private fun showPopup(){
        val popupview = LayoutInflater.from(this@HomeActivity)
            .inflate(R.layout.add_recipe_layout, null)
        val name = popupview.findViewById<EditText>(R.id.name)
        val ing  = popupview.findViewById<EditText>(R.id.ing)
        val img = popupview.findViewById<EditText>(R.id.img_url)
        val desc = popupview.findViewById<EditText>(R.id.desc)
        val cat = popupview.findViewById<EditText>(R.id.category)

        val add = popupview.findViewById<MaterialButton>(R.id.addBtn)
        val cancel = popupview.findViewById<MaterialButton>(R.id.cancelBtn)

        cancel.setOnClickListener {
            popupWindow?.dismiss()
        }
        add.setOnClickListener {
            addNewRecipe(

                name.text.toString(),
                img.text.toString(),
                ing.text.toString(),
                desc.text.toString(),
                cat.text.toString()
            )
        }
        popupWindow = PopupWindow(
            popupview,
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        popupWindow?.isFocusable=true
        popupWindow?.showAtLocation(
            popupview,
            0,0 ,0
        )
    }

    private fun setUpRecyclerView() {
        binding.rvPopular.layoutManager=LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,false
        )
        rvAdapter= PopularAdapter(this)
        binding.rvPopular.adapter = rvAdapter


    }
    fun addNewRecipe(
        name: String,
        img:String,
        ing: String,
        desc: String,
        cat: String
    ) {
        dao.insert(
            Recipe(
                img = img,
                tittle = name,
                des = desc,
                ing = ing,
                category = cat,
                uid = 0
            )
        )
    }
}