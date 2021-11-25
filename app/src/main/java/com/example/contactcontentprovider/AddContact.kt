package com.example.contactcontentprovider

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat

class AddContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

            if(ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_CONTACTS),111)
            }
            else {
                writeContacts()

            }
        }



    private fun writeContacts() {
        var cv = ContentValues()
        var rowuri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI,cv)
        var rowContactID = ContentUris.parseId(rowuri!!)

        cv.put(ContactsContract.Data.RAW_CONTACT_ID,rowContactID)
        cv.put(ContactsContract.Data.MIMETYPE,
            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
        cv.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,"tanzeel sheikh")
        contentResolver.insert(ContactsContract.Data.CONTENT_URI,cv)

        cv.put(ContactsContract.Data.RAW_CONTACT_ID,rowContactID)
        cv.put(ContactsContract.Data.MIMETYPE,
            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
        cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER,"8866592787")
        contentResolver.insert(ContactsContract.Data.CONTENT_URI,cv)


    }

}
