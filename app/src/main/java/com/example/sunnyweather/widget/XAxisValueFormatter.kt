package com.example.sunnyweather.widget

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
/*private val xStrs = arrayOf("春", "夏", "秋", "冬")：定义了一个包含季节字符串的数组 xStrs，分别代表了春、夏、秋、冬四个季节。

override fun getFormattedValue(value: Float, axis: AxisBase): String {...}：
重写了接口中的 getFormattedValue 方法，根据传入的 value（Float 类型）和 axis（AxisBase 类型）来返回相应的格式化后的字符串值。

var position = value.toInt()：将传入的 value 转换为整数，表示当前的位置。

if (position >= 4) { position = 0 }：如果位置超过了数组 xStrs 的长度（大于等于4），则将位置重置为0，以实现循环显示的效果。

return xStrs[position]：根据计算后的位置，从 xStrs 数组中取出对应位置的季节字符串，并作为最终的返回结果。

通过这段代码，可以实现根据传入的数值在 X 轴上显示不同的季节信息，从而实现定制化的 X 轴标签显示效果。*/
class XAxisValueFormatter : IAxisValueFormatter {
    private val xStrs = arrayOf("春", "夏", "秋", "冬")
    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        var position = value.toInt()
        if (position >= 4) {
            position = 0
        }
        return xStrs[position]
    }
}