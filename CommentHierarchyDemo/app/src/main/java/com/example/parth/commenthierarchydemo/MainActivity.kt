package com.example.parth.commenthierarchydemo

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.parth.commenthierarchydemo.comments_module.CommentType.COMMENT
import com.example.parth.commenthierarchydemo.databinding.ActivityMainBinding
import com.example.parth.kotlinpractice_2.support.CoreActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CoreActivity<MainActivity, ActivityMainBinding, MainViewModel>() {

    var type: Int = COMMENT
//    var itemPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaults(this, R.layout.activity_main)
    }

    override fun setVM(binding: ActivityMainBinding) {
        binding.vm = vm
    }

    override fun createViewModel(activity: MainActivity) = MainViewModel(this)

    override fun getActionBarTitle() = "MainActi"

    override fun workArea(vm: MainViewModel) {
        vm.createDRCList()
//        vm.callApi()
        img_btn_send.setOnClickListener {
            vm.addItemToList(ed_comment.text.toString(), type/*, itemPosition*/)
            ed_comment.text.clear()
            type = COMMENT
        }
    }

//    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//        if (v?.id == R.id.rec_view) {
//            menuInflater.inflate(R.menu.comment_context_menu, menu)
//        }
//    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val type = item?.groupId
        val position = item?.order
        val id = item?.itemId
        when (item?.title) {
            "Edit" -> {
                Toast.makeText(this@MainActivity, "Edit $position and $type comment", Toast.LENGTH_SHORT).show()
                return true
            }
            "Delete" -> {
                vm!!.deleteItemFromList(type!!)
                return true
            }
            else -> return super.onContextItemSelected(item)

        }
    }
}