/*
 * Copyright (c) 2020. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package com.example.sunnyweather.base.binding

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.sunnyweather.base.MyActivity

/**
 * activity基类 ViewBinding模式
 * @author Dylan Cai
 */
abstract class BaseBindingActivity<VB : ViewBinding> : MyActivity(),
  ActivityBinding<VB> by ActivityBindingDelegate() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentViewWithBinding()
  }
}