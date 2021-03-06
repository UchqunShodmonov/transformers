package jp.wasabeef.transformers.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.wasabeef.transformers.core.Blur
import jp.wasabeef.transformers.core.bitmapConfig

/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class BlurTransformation @JvmOverloads constructor(
  context: Context,
  radius: Int = 25,
  private val sampling: Int = 1,
  rs: Boolean = true
) : BaseTransformation(Blur(context, radius, sampling, rs)) {

  override fun transform(
    context: Context,
    pool: BitmapPool,
    source: Bitmap,
    outWidth: Int,
    outHeight: Int
  ): Bitmap {
    val scaledWidth: Int = source.width / sampling
    val scaledHeight: Int = source.height / sampling
    val output = pool.get(scaledWidth, scaledHeight, bitmapConfig(source))
    return transformer.transform(source, output)
  }
}