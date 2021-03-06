package com.andb.apps.dragdrop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andb.apps.dragdropper.dp
import com.andb.apps.dragdropper.dragDropWith
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.list_item.view.*

class MainActivity : AppCompatActivity() {

    private val itemsList = mutableListOf("Apple", "Banana", "Cranberry", "Fig", "Grape", "Honeydew", "Kiwi", "Lemon", "Mango", "Nectarine", "Orange", "Peach", "Raspberry", "Strawberry", "Tangerine", "Watermelon")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(itemsList)
        recyclerView.dragDropWith {
            onDropped { oldPos, newPos ->
                val moved: String = itemsList.removeAt(oldPos)
                itemsList.add(newPos, moved)
            }
            constrainDrag { vh ->
                val pos = vh.adapterPosition
                return@constrainDrag (pos - 3)..(pos + 3)
            }
            canDrag = { vh ->
                vh.itemView.listText.text != "Banana"
            }
            elevateBy = 4.dp //default 8
        }
    }
}
