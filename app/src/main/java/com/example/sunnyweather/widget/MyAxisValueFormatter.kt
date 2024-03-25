package com.example.sunnyweather.widget

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.DecimalFormat
/*这段代码是一个自定义的 AxisValueFormatter 类，用于格式化图表中坐标轴上的数值显示。
在这个类中，实现了 IAxisValueFormatter 接口，该接口用于定义坐标轴上数值的格式化方式。

在类 MyAxisValueFormatter 中，首先定义了一个 DecimalFormat 对象 mFormat，用于指定数值的格式。
在 init 块中进行了初始化，采用"###,###,###,##0.000"格式，表示以千位分隔符形式显示数字，并保留三位小数。

在 getFormattedValue 方法中，根据传入的 value（Float 类型）和 axis（AxisBase 类型），
将 value 转换为 double 类型，并使用 mFormat 格式化为字符串。最后返回格式化后的字符串，附加上"$"符号，
用于在图表中显示经过格式化的数值。*/
class MyAxisValueFormatter : IAxisValueFormatter {
    private val mFormat: DecimalFormat

    init {
        mFormat = DecimalFormat("###,###,###,##0.000")
    }

    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        return mFormat.format(value.toDouble()) + " $"
    }
}