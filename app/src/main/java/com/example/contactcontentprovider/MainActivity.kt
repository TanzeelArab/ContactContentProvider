package com.example.contactcontentprovider

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    /*    Toast.makeText(applicationContext,"TEST-1",Toast.LENGTH_LONG).show()

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_CONTACTS),1111)
*/
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),111)
        }
        else {
            readContact()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readContact()
        }else if (requestCode==111 && grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(applicationContext,"Denied!",Toast.LENGTH_LONG).show()
        }
    }
    private fun readContact() {

        var col = arrayOf(ContactsContract.CommonDataKinds.Phone._ID,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        var lv = findViewById<ListView>(R.id.listview)
        var sv = findViewById<SearchView>(R.id.searchView)
        var rs = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,col,null,null,null)
        var adapter = SimpleCursorAdapter(applicationContext,android.R.layout.simple_list_item_2,rs, arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER),
        intArrayOf(android.R.id.text1,android.R.id.text2),0)
        lv.adapter = adapter
        sv.queryHint="${rs?.count} Contacts"


    }
}