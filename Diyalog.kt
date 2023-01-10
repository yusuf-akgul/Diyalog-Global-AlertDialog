package com.aksoft.diyalog

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class Diyalog {
    lateinit var ctxt: Context
    val BEYAZ="#FFFFFF"
    val GRI1="#FAFAFA"
    val GRI2="#97E6E2E2"
    val GRI3="#ffcfd8dc"
    val GRI4="#ffb0bec5"
    val KIRMIZI="#FF0000"
    val YESIL="#098509"
    val SARI="#ffff9800"
    val MAVI="#0000FF"
    val FUSHIA="#FF4081"
    val MAGENTA="#ffba68c8"
    val SIYAH="#000000"

    private var mesaj:String=""
    private var iconn:Int=0
    private var okBtn:String=""
    private var noBtn:String=""
    private var anime:Int=0
    private var cancel:Boolean=true

    private var titTxt:String=""
    private var titTxtColor:String="#ffffff" // Beyaz
    private var titBckColor:String="#ffff9800" // Sarı

    private var adapter: ArrayAdapter<String>?=null
    lateinit var geriDonus: (dns:String)->Unit

    fun setIcon(icn:Int):Diyalog{
        iconn=icn
        return this
    }

    fun setTitle(basTxt:String):Diyalog{
        titTxt=basTxt
        return this
    }

    fun setColor(clrTitle:String, clrBack:String):Diyalog{
        titTxtColor=clrTitle
        titBckColor=clrBack
        return this
    }

    fun setMessage(msg:String):Diyalog{
        mesaj=msg
        return this
    }

    fun setAnime(anm:Int):Diyalog{
        anime=anm
        return this
    }

    fun setAdapter(adp:ArrayAdapter<String>):Diyalog{
        adapter=adp
        return this
    }

    fun setPozBtn(okbuton:String):Diyalog{
        okBtn=okbuton
        return this
    }

    fun setNegBtn(nobuton:String):Diyalog{
        noBtn=nobuton
        return this
    }

    fun setCancel(cnc:Boolean):Diyalog{
        cancel=cnc
        return this
    }

    fun Init(contxt: Context):Diyalog{
        ctxt=contxt
        return this
    }

    fun setResult(geriDonus:(dns:String)->Unit):Diyalog{
        this.geriDonus=geriDonus
        return this
    }

    fun customTitle(ctxt:Context, icon:Int, title:String): View {
        val baslik=if(title=="") "Bilgilendirme" else title
        val txt=TextView(ctxt)

        if (icon!=0){
            txt.setCompoundDrawablesWithIntrinsicBounds(icon,0,0,0)
            txt.setPadding(20,15,0,15)
        }else{
            txt.setPadding(0,15,0,15)
        }

        txt.text="  $baslik"
        txt.setTextSize(18f)
        txt.setTextColor(Color.parseColor(titTxtColor))
        txt.setBackgroundColor(Color.parseColor(titBckColor))

        txt.gravity=Gravity.CENTER_VERTICAL
        txt.typeface=Typeface.DEFAULT_BOLD

        return txt
    }

    fun Show() {
        val dlg:AlertDialog

        val ald=AlertDialog.Builder(ctxt, R.style.myDlgThemeNorm)
            .setCancelable(cancel)

        ald.setCustomTitle(customTitle(ctxt, iconn, titTxt))

        if (iconn!=0) ald.setIcon(iconn)

        // adapter kullanılmışsa setmessage çalışmaz
        // bu yüzden kontrol yapıldı
        if (adapter!=null){
            ald.setAdapter(adapter){ dlgx, sec ->
                dlgx.dismiss()
                geriDonus("$sec")
            }
        }else{
            // mesaj içinde html kodu kullanılmışsa
            if (Build.VERSION.SDK_INT>=24 && mesaj.contains("<font"))
                ald.setMessage(Html.fromHtml(mesaj, Html.FROM_HTML_MODE_LEGACY))
            else
                ald.setMessage(mesaj)
        }

        if (noBtn!="") {
            ald.setNegativeButton(noBtn) { dlgx, sec ->
                dlgx.dismiss()
                geriDonus("NO")
            }
        }

        if (okBtn!="") {
            ald.setPositiveButton(okBtn) { dlgx, sec ->
                dlgx.dismiss()
                geriDonus("OK")
            }
        }

        dlg=ald.create()

        if (anime!=0) dlg.window!!.attributes.windowAnimations = anime
        dlg.show()
    }
}