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

package com.example.sunnyweather.base

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

interface FragmentBinding<VB : ViewBinding> {
  val binding: VB
  fun Fragment.createViewWithBinding(inflater: LayoutInflater, container: ViewGroup?): View
}

class FragmentBindingDelegate<VB : ViewBinding> : FragmentBinding<VB> {
  private var _binding: VB? = null
  private val handler by lazy { Handler(Looper.getMainLooper()) }

  override val binding: VB get() = requireNotNull(_binding) { "The property of binding has been destroyed." }

  override fun Fragment.createViewWithBinding(inflater: LayoutInflater, container: ViewGroup?): View {
    if (_binding == null) {
      _binding = ViewBindingUtil.inflateWithGeneric(this, inflater, container, false)
      // 页面销毁可能导致空指针, 做好生命周期绑定或者上下文判空
      viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
          handler.post { _binding = null }
        }
      })
    }
    return _binding!!.root
  }
}