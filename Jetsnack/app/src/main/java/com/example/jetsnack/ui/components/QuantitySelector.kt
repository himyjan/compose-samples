/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetsnack.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetsnack.R
import com.example.jetsnack.ui.theme.JetsnackTheme

@Composable
fun QuantitySelector(count: Int, decreaseItemCount: () -> Unit, increaseItemCount: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(R.string.quantity),
            style = MaterialTheme.typography.titleMedium,
            color = JetsnackTheme.colors.textSecondary,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(end = 18.dp)
                .align(Alignment.CenterVertically),
        )
        JetsnackGradientTintedIconButton(
            iconResourceId = R.drawable.ic_remove,
            onClick = decreaseItemCount,
            contentDescription = stringResource(R.string.label_decrease),
            modifier = Modifier.align(Alignment.CenterVertically),
        )
        Crossfade(
            targetState = count,
            modifier = Modifier
                .align(Alignment.CenterVertically),
        ) {
            Text(
                text = "$it",
                style = MaterialTheme.typography.titleSmall,
                fontSize = 18.sp,
                color = JetsnackTheme.colors.textPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(min = 24.dp),
            )
        }
        JetsnackGradientTintedIconButton(
            iconResourceId = R.drawable.ic_add,
            onClick = increaseItemCount,
            contentDescription = stringResource(R.string.label_increase),
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun QuantitySelectorPreview() {
    JetsnackTheme {
        JetsnackSurface {
            QuantitySelector(1, {}, {})
        }
    }
}

@Preview("RTL")
@Composable
fun QuantitySelectorPreviewRtl() {
    JetsnackTheme {
        JetsnackSurface {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                QuantitySelector(1, {}, {})
            }
        }
    }
}
