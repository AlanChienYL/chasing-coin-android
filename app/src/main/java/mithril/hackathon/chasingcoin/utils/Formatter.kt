package mithril.hackathon.chasingcoin.utils

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by AlanChien on 20,April,2019.
 */
fun Double.format(): String = let {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.HALF_UP
    df.format(this)
}
