package mithril.hackathon.chasingcoin.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by AlanChien on 20,April,2019.
 */
fun Double.format(): String = let {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.HALF_UP
    df.format(this)
}

fun Long.getDateMinOnly(): String = run {
    val c = Calendar.getInstance()
    c.timeInMillis = this
    val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
    sdf.format(c.time)
}