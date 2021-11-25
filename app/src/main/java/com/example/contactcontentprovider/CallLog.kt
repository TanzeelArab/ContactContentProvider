package com.example.contactcontentprovider

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat

class CallLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_log)

        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALL_LOG),111)
        }
        else {
            readCallLog()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readCallLog()
        }else if (requestCode==111 && grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(applicationContext,"Denied!", Toast.LENGTH_LONG).show()
        }
    }
    private fun readCallLog() {

        var col = arrayOf(
           CallLog.Calls._ID,
            CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE,
            CallLog.Calls.DURATION
        )
        var lv = findViewById<ListView>(R.id.listview2)
//        var sv = findViewById<SearchView>(R.id.searchView)
        var rs = contentResolver.query(CallLog.Calls.CONTENT_URI,col,null,null,null)
        var adapter = SimpleCursorAdapter(applicationContext,android.R.layout.simple_list_item_2,rs, arrayOf(CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE),
            intArrayOf(android.R.id.text1,android.R.id.text2),0)
        lv.adapter = adapter
//        sv.queryHint="${rs?.count} Contacts"


    }
}