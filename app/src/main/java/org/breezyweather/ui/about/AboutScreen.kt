package org.breezyweather.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.breezyweather.BuildConfig
import org.breezyweather.R
import org.breezyweather.common.extensions.plus
import org.breezyweather.ui.common.widgets.Material3ExpressiveCardListItem
import org.breezyweather.ui.common.widgets.Material3Scaffold
import org.breezyweather.ui.common.widgets.generateCollapsedScrollBehavior
import org.breezyweather.ui.common.widgets.getCardListItemMarginDp
import org.breezyweather.ui.common.widgets.insets.FitStatusBarTopAppBar
import org.breezyweather.ui.common.widgets.insets.bottomInsetItem
import org.breezyweather.ui.settings.preference.LargeSeparatorItem
import org.breezyweather.ui.settings.preference.SmallSeparatorItem
import androidx.compose.ui.input.nestedscroll.nestedScroll


// Simple inline contributor data class
data class SimpleContributor(val name: String, val photoResId: Int)

@Composable
internal fun AboutScreen(
    onBackPressed: () -> Unit,
) {
    val scrollBehavior = generateCollapsedScrollBehavior()
    val context = LocalContext.current

    // Our 3 contributors with their photos
    val simpleContributors = listOf(
        SimpleContributor("Akhil Menon", R.drawable.a),
        SimpleContributor("Sreelakshmi M O", R.drawable.a),
        SimpleContributor("Navaneeth N", R.drawable.a)
    )

    Material3Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FitStatusBarTopAppBar(
                title = stringResource(R.string.action_about),
                onBackPressed = onBackPressed,
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            contentPadding = paddingValues.plus(
                PaddingValues(horizontal = dimensionResource(R.dimen.normal_margin))
            )
        ) {
            item {
                Header()
                LargeSeparatorItem()
                SectionTitle(stringResource(R.string.about_contributors))
            }
            // Show only our 3 contributors with photos
            itemsIndexed(simpleContributors) { index, item ->
                ContributorViewWithPhoto(
                    name = item.name,
                    photoResId = item.photoResId,
                    isFirst = index == 0,
                    isLast = index == simpleContributors.lastIndex
                )
                if (index != simpleContributors.lastIndex) {
                    SmallSeparatorItem()
                }
            }
            bottomInsetItem(
                extraHeight = getCardListItemMarginDp(context).dp
            )
        }
    }
}

@Composable
private fun Header() {
    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_round),
            contentDescription = null,
            modifier = Modifier.size(72.dp)
        )
        Spacer(
            modifier = Modifier
                .height(dimensionResource(R.dimen.small_margin))
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.breezy_weather),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = versionFormatted,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(dimensionResource(R.dimen.normal_margin)),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.labelMedium
    )
}

private val versionFormatted: String
    get() = when {
        BuildConfig.DEBUG -> "Debug ${BuildConfig.COMMIT_SHA}"
        else -> "Release ${BuildConfig.VERSION_NAME}"
    }

@Composable
private fun ContributorViewWithPhoto(
    name: String,
    photoResId: Int,
    isFirst: Boolean = false,
    isLast: Boolean = false,
) {
    Material3ExpressiveCardListItem(isFirst = isFirst, isLast = isLast) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.normal_margin)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circular profile photo
            Image(
                painter = painterResource(photoResId),
                contentDescription = "$name profile photo",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.normal_margin)))
            Text(
                text = name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
